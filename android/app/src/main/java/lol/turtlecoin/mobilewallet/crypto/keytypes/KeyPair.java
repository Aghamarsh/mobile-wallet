// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

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
