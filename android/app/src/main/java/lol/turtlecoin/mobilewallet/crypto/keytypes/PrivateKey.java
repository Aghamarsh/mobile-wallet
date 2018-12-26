// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

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
