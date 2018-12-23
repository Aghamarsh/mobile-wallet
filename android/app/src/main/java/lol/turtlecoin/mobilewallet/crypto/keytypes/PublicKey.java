package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class PublicKey extends ThirtyTwoByteKey {
    public PublicKey() {
        super();
    }

    public PublicKey(byte[] data) {
        super(data);
    }

    public PublicKey(String input) {
        super(input);
    }
}
