package lol.turtlecoin.mobilewallet.crypto.keytypes;

import lol.turtlecoin.mobilewallet.utils.Hex;

import java.security.InvalidParameterException;

public class ThirtyTwoByteKey {
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ThirtyTwoByteKey(byte[] data) {
        if (data.length < 32) {
            throw new InvalidParameterException("Input array must be at least 32 bytes long!");
        }

        this.data = new byte[32];

        System.arraycopy(data, 0, this.data, 0, 32);
    }

    public ThirtyTwoByteKey(String input) {
        if (input.length() < 64) {
            throw new InvalidParameterException("Input string must be at least 64 chars long!");
        }

        byte[] data = Hex.decode(input);

        this.data = new byte[32];

        System.arraycopy(data, 0, this.data, 0, 32);
    }

    public ThirtyTwoByteKey() {
        data = new byte[32];
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ThirtyTwoByteKey)) {
            return false;
        }

        ThirtyTwoByteKey other = (ThirtyTwoByteKey) obj;

        for (int i = 0; i < 32; i++) {
            if (data[i] != other.data[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    @Override
    public String toString() {
        return Hex.encode(data);
    }


}
