package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class PrivateKey extends ThirtyTwoByteKey {
    public PrivateKey() {
        super();
    }

    public PrivateKey(byte[] data) {
        super(data);
    }

    public PrivateKey(String input) {
        super(input);
    }
}
