package lol.turtlecoin.mobilewallet.crypto;

import org.spongycastle.crypto.digests.KeccakDigest;

import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;

import lol.turtlecoin.mobilewallet.crypto.ed25519.Ed25519;
import lol.turtlecoin.mobilewallet.crypto.ed25519.Ed25519.XYZT;
import lol.turtlecoin.mobilewallet.crypto.keytypes.EllipticCurveScalar;
import lol.turtlecoin.mobilewallet.crypto.keytypes.KeyPair;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PrivateKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PublicKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.WalletKeys;

import static lol.turtlecoin.mobilewallet.crypto.ed25519.Ed25519.reduce;
import static lol.turtlecoin.mobilewallet.crypto.ed25519.Ed25519.reduce32;
import static lol.turtlecoin.mobilewallet.crypto.ed25519.Ed25519.sc_check;

public class KeyOps {
    private static EllipticCurveScalar RandomScalar() {
        byte[] temp = new byte[64];
        SecureRandom rand = new SecureRandom();
        rand.nextBytes(temp);
        reduce(temp);
        return new EllipticCurveScalar(temp);
    }

    public static boolean IsValidKey(PublicKey key) {
        try {
            XYZT point = XYZT.fromBytesNegateVarTime(key.getData());
            return true;
        } catch (GeneralSecurityException e) {
            return false;
        }
    }

    public static boolean IsValidKey(PrivateKey key) {
        return sc_check(key.getData()) == 0;
    }

    public static WalletKeys GenerateWalletKeys() {
        KeyPair spendKeys = GenerateKeys();
        KeyPair viewKeys = GenerateDeterministicKeys(spendKeys.privateKey);
        return new WalletKeys(spendKeys, viewKeys);
    }

    public static KeyPair GenerateKeys() {
        EllipticCurveScalar s = RandomScalar();
        PrivateKey privateKey = new PrivateKey(s.getData());
        PublicKey publicKey = PrivateKeyToPublicKey(privateKey);
        return new KeyPair(publicKey, privateKey);
    }

    public static KeyPair GenerateDeterministicKeys(PrivateKey seed) {
        byte[] seedTmp = seed.getData().clone();
        KeccakDigest digest = new KeccakDigest(256);
        digest.update(seedTmp,0,seedTmp.length);
        byte[] hashed = new byte[32];
        digest.doFinal(hashed,0);
        reduce32(hashed);
        PrivateKey privateKey = new PrivateKey(hashed);
        PublicKey publicKey = PrivateKeyToPublicKey(privateKey);

        return new KeyPair(publicKey, privateKey);
    }

    public static boolean AreKeysDeterministic(PrivateKey privateSpendKey, PrivateKey privateViewKey) {
        PrivateKey derivedPrivateKey = GenerateDeterministicKeys(privateSpendKey).privateKey;
        return privateViewKey.equals(derivedPrivateKey);
    }

    public static PublicKey PrivateKeyToPublicKey(PrivateKey privateKey) {
        byte[] tmp = Ed25519.scalarMultWithBaseToBytes(privateKey.getData());
        return new PublicKey(tmp);
    }
}
