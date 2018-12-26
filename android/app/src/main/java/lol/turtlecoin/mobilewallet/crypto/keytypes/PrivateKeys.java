// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class PrivateKeys {
    public PrivateKey spendKey;
    public PrivateKey viewKey;

    public PrivateKeys() {}

    public PrivateKeys(PrivateKey spendKey, PrivateKey viewKey) {
        this.spendKey = spendKey;
        this.viewKey = viewKey;
    }
}
