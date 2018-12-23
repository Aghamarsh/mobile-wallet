package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class PublicKeys {
    public PublicKey spendKey;
    public PublicKey viewKey;

    public PublicKeys() {}

    public PublicKeys(PublicKey spendKey, PublicKey viewKey) {
        this.spendKey = spendKey;
        this.viewKey = viewKey;
    }
}
