package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class WalletKeys {
    private PublicKey publicSpendKey;
    private PublicKey publicViewKey;
    private PrivateKey privateSpendKey;
    private PrivateKey privateViewKey;

    public WalletKeys() {
    }

    public WalletKeys(KeyPair spendKeys, KeyPair viewKeys) {
        publicSpendKey = spendKeys.publicKey;
        publicViewKey = viewKeys.publicKey;
        privateSpendKey = spendKeys.privateKey;
        privateViewKey = viewKeys.privateKey;
    }

    public WalletKeys(PublicKey publicSpendKey, PublicKey publicViewKey, PrivateKey privateSpendKey, PrivateKey privateViewKey) {
        this.publicSpendKey = publicSpendKey;
        this.publicViewKey = publicViewKey;
        this.privateSpendKey = privateSpendKey;
        this.privateViewKey = privateViewKey;
    }

    public PublicKeys GetPublicKeys() {
        return new PublicKeys(publicSpendKey, publicViewKey);
    }

    public PrivateKeys GetPrivateKeys() {
        return new PrivateKeys(privateSpendKey, privateViewKey);
    }

    public KeyPair GetSpendKeys() {
        return new KeyPair(publicSpendKey, privateSpendKey);
    }

    public KeyPair GetViewKeys() {
        return new KeyPair(publicViewKey, privateViewKey);
    }

    public PublicKey getPublicSpendKey() {
        return publicSpendKey;
    }

    public void setPublicSpendKey(PublicKey publicSpendKey) {
        this.publicSpendKey = publicSpendKey;
    }

    public PublicKey getPublicViewKey() {
        return publicViewKey;
    }

    public void setPublicViewKey(PublicKey publicViewKey) {
        this.publicViewKey = publicViewKey;
    }

    public PrivateKey getPrivateSpendKey() {
        return privateSpendKey;
    }

    public void setPrivateSpendKey(PrivateKey privateSpendKey) {
        this.privateSpendKey = privateSpendKey;
    }

    public PrivateKey getPrivateViewKey() {
        return privateViewKey;
    }

    public void setPrivateViewKey(PrivateKey privateViewKey) {
        this.privateViewKey = privateViewKey;
    }
}
