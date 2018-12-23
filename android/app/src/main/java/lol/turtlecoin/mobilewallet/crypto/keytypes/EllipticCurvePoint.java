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
