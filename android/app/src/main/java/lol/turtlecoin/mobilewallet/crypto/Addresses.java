package lol.turtlecoin.mobilewallet.crypto;

import org.apache.commons.lang3.StringUtils;
import org.bitcoinj.core.Base58;
import org.spongycastle.crypto.digests.KeccakDigest;
import org.spongycastle.util.Strings;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Locale;

import lol.turtlecoin.mobilewallet.crypto.keytypes.PrivateKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PublicKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PublicKeys;

public class Addresses {

    public static String AddressFromKeys(PrivateKey privateSpendKey, PrivateKey privateViewKey, long addressPrefix) {
        PublicKey publicSpendKey = KeyOps.PrivateKeyToPublicKey(privateSpendKey);
        PublicKey publicViewKey = KeyOps.PrivateKeyToPublicKey(privateViewKey);

        return AddressFromKeys(publicSpendKey, publicViewKey, addressPrefix);
    }

    public static String AddressFromKeys(PublicKey publicSpendKey, PublicKey publicViewKey, long addressPrefix) {
        ArrayList<Byte> combined = new ArrayList<>();
        byte[] spend = publicSpendKey.getData();
        byte[] view = publicViewKey.getData();
        ArrayList<Byte> spendData = frombytearray(spend);
        ArrayList<Byte> viewData = frombytearray(view);

        combined.addAll(PackPrefixAsByteList(addressPrefix));
        combined.addAll(spendData);
        combined.addAll(viewData);

        byte[] checksum = GetAddressChecksum(combined);

        ArrayList<Byte> checksumData = frombytearray(checksum);

        combined.addAll(checksumData);

        ArrayList<List<Byte>> chunks = new ArrayList<>();
        for(int i = 0;i<combined.size();i+=8) {
            if (i+8 <= combined.size()) {
                chunks.add(combined.subList(i,i+8));
            }
        }

        StringBuilder addressBuilder = new StringBuilder();

        int lastBlockSize = combined.size() % 8;
        chunks.add(combined.subList((combined.size() / 8) * 8, combined.size()));

        for (int i=0;i<chunks.size();i++) {
            String tmp = Base58.encode(tobytearray(chunks.get(i)));

            if (i<chunks.size()-1) {
                tmp = StringUtils.leftPad(tmp, 11, '1');
            } else {
                tmp = StringUtils.leftPad(tmp, lastBlockSize, '1');
            }

            addressBuilder.append(tmp);
        }

        return addressBuilder.toString();

    }

    private static byte[] tobytearray(List<Byte> input) {
        byte[] output = new byte[input.size()];

        for (int i=0;i<input.size();i++) {
            output[i] = input.get(i);
        }

        return output;
    }

    private static ArrayList<Byte> frombytearray(byte[] input) {
        ArrayList<Byte> output = new ArrayList<>();

        for (byte i:input) {
            output.add(i);
        }

        return output;
    }

    private static char[] tochararray(List<Character> input) {
        char[] output = new char[input.size()];

        for (int i=0;i<input.size();i++) {
            output[i] = input.get(i);
        }

        return output;
    }

    public static PublicKeys KeysFromAddress(String address, long prefix) {
        char[] list = address.toCharArray();
        ArrayList<Character> addressData = new ArrayList<>();
        for (char i:list) {
            addressData.add(i);
        }

        ArrayList<List<Character>> chunks = new ArrayList<>();
        for (int i=0;i<addressData.size();i+=11) {
            if (i+11<=addressData.size()) {
                chunks.add(addressData.subList(i,i+11));
            }
        }
        chunks.add(addressData.subList((addressData.size() / 11) * 11, addressData.size()));

        ArrayList<Byte> decoded = new ArrayList<>();

        for (List<Character> chunk:
             chunks) {
            ArrayList<Byte> decodedChunk = new ArrayList<>(frombytearray(Base58.decode(String.copyValueOf(tochararray(chunk)))));

            if (decodedChunk.size() != 0) {
                if (decodedChunk.size() <= 8) {
                    decoded.addAll(decodedChunk);
                } else {
                    decoded.addAll(decodedChunk.subList(decodedChunk.size()-8,decodedChunk.size()));
                }
            }
        }
        System.out.println();

        ArrayList<Byte> expectedPrefixData = PackPrefixAsByteList(prefix);
        byte[] expectedPrefix = tobytearray(expectedPrefixData);

        int expectedLength = expectedPrefix.length+32+32+4;

        if (decoded.size() != expectedLength) {

        }

        byte[] actualPrefix = new byte[expectedPrefix.length];
        byte[] spend = new byte[32];
        byte[] view = new byte[32];
        byte[] actualChecksum = new byte[4];

        int i=0;

        for (int j=0;j<expectedPrefix.length;j++) {
            actualPrefix[j] = decoded.get(i++);
        }

        for (int j=0;j<spend.length;j++) {
            spend[j] = decoded.get(i++);
        }

        for (int j=0;j<view.length;j++) {
            view[j] = decoded.get(i++);
        }

        for (int j=0;j<actualChecksum.length;j++) {
            actualChecksum[j] = decoded.get(i++);
        }

        for (int j=0;j<expectedPrefix.length;j++) {
            if (actualPrefix[j] != expectedPrefix[j]) {

            }
        }

        ArrayList<Byte> addressNoChecksum = new ArrayList<>();

        ArrayList<Byte> actualPrefixData = frombytearray(actualPrefix);
        ArrayList<Byte> spendData = frombytearray(spend);
        ArrayList<Byte> viewData = frombytearray(view);

        addressNoChecksum.addAll(actualPrefixData);
        addressNoChecksum.addAll(spendData);
        addressNoChecksum.addAll(viewData);

        byte[] expectedChecksum = GetAddressChecksum(addressNoChecksum);

        for (int j=0;j<expectedChecksum.length;j++) {
            if (actualChecksum[j] != expectedChecksum[j]) {

            }
        }

        PublicKey spendKey = new PublicKey(spend);
        PublicKey viewKey = new PublicKey(view);

        if (!KeyOps.IsValidKey(spendKey) || !KeyOps.IsValidKey(viewKey)) {

        }

        return new PublicKeys(spendKey, viewKey);
    }

    private static byte[] GetAddressChecksum(ArrayList<Byte> addressInBytes) {
        KeccakDigest digest = new KeccakDigest(256);
        byte[] input = new byte[addressInBytes.size()];
        for (int i=0;i<addressInBytes.size();i++) {
            input[i] = addressInBytes.get(i);
        }
        digest.update(input,0,input.length);
        byte[] out = new byte[32];
        digest.doFinal(out,0);

        byte[] output = new byte[4];
        System.arraycopy(out,0,output,0,4);

        return output;
    }

    private static ArrayList<Byte> PackPrefixAsByteList(long prefix) {
        ArrayList<Byte> output = new ArrayList<>();

        while (prefix >= 0x80) {
            output.add((byte)(prefix & 0x7f | 0x80));
            prefix >>= 7;
        }

        output.add((byte)prefix);

        return output;
    }
}
