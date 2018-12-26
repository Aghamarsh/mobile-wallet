// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

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
