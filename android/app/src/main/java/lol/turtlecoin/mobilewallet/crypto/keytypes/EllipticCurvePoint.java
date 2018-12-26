// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class EllipticCurvePoint extends ThirtyTwoByteKey {
    public EllipticCurvePoint() {
        super();
    }

    public EllipticCurvePoint(byte[] data) {
        super(data);
    }

    public EllipticCurvePoint(String input) {
        super(input);
    }
}
