package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class KeyPair {
    public PrivateKey privateKey;
    public PublicKey publicKey;

    public KeyPair() {}

    public KeyPair(PublicKey publicKey, PrivateKey privateKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }
}
