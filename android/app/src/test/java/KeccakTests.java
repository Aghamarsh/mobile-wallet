import org.junit.Assert;
import org.junit.Test;
import org.spongycastle.crypto.digests.KeccakDigest;

import lol.turtlecoin.mobilewallet.utils.Hex;

public class KeccakTests {
    @Test
    public void TestKeccak() {
        String[] input = {
                "",
                "The quick brown fox jumps over the lazy dog",
                "The quick brown fox jumps over the lazy dog.",
                "I'd just like to interject for a moment. What you're referring to as Linux, is in fact, GNU/Linux, or as I've recently taken to calling it, GNU plus Linux. Linux is not an operating system unto itself"
        };

        String[] output = {
                "c5d2460186f7233c927e7db2dcc703c0e500b653ca82273b7bfad8045d85a470",
                "4d741b6f1eb29cb2a9b9911c82f56fa8d73b04959d3d9d222895df6c0b28aa15",
                "578951e24efd62a3d63a86f7cd19aaa53c898fe287d2552133220370240b572d",
                "d6a63dc2e3ab16360c1dd26fa4b343af9dde6b4ae275793b1d64eaffdc02f1d9"
        };

        KeccakDigest digest = new KeccakDigest(256);

        for (int i=0;i<input.length;i++) {
            digest.reset();
            digest.update(input[i].getBytes(),0,input[i].getBytes().length);
            byte[] out = new byte[32];
            digest.doFinal(out,0);

            Assert.assertEquals(output[i],Hex.encode(out));
        }
    }
}
