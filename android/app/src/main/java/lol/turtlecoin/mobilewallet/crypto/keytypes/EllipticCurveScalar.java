package lol.turtlecoin.mobilewallet.crypto.keytypes;

public class EllipticCurveScalar extends ThirtyTwoByteKey {
    public EllipticCurveScalar() {
        super();
    }

    public EllipticCurveScalar(byte[] data) {
        super(data);
    }

    public EllipticCurveScalar(String input) {
        super(input);
    }
}
