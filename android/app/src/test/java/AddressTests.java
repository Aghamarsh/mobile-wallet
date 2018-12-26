// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import org.junit.Assert;
import org.junit.Test;

import lol.turtlecoin.mobilewallet.crypto.Addresses;
import lol.turtlecoin.mobilewallet.crypto.KeyOps;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PrivateKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PublicKey;
import lol.turtlecoin.mobilewallet.crypto.keytypes.PublicKeys;
import lol.turtlecoin.mobilewallet.crypto.keytypes.WalletKeys;

public class AddressTests {
    @Test
    public void TestAddressFromKeys() {
        PublicKey s1 = new PublicKey("452fc1d7637380b6f2615583173779a7c1a183f5454a2b1b7dd94d6762023714");
        PublicKey v1 = new PublicKey("b25532b855490516a1cb300cde3f0936cd84d01495e09adcb5a23cae6e6d302a");

        String derivedAddress1 = Addresses.AddressFromKeys(s1, v1, 0x3bbb1d);

        Assert.assertEquals("TRTLuyFCzMtHdoTjacqK6A4tEQL7yzPGGCbCRz9NRmyxHPonkiCGVNXFGNfo7bBZWBeB4sxABe3goS4ztq1UhVZFKUGytkv3ubJ", derivedAddress1);

        String derivedAddress2 = Addresses.AddressFromKeys(s1, v1, 0x12);

        Assert.assertEquals("44FBBvSSEohXbpAHmYjtNCV4SvVioYata5bhmenHbGLW4TnREnSMy1N4nZQrQxB7h2AAf3Px7JNk1dvACpuD652X5kea7nw", derivedAddress2);

        PrivateKey s2 = new PrivateKey("51ae544866f9a9187c6a81d2d6043848100d909ad9626effbdae336443438701");
        PrivateKey v2 = new PrivateKey("e4fc49878954ff6bfc0e2330ff562dd5aceca69e93d519f6d77153ccef09de0e");

        String derivedAddress3 = Addresses.AddressFromKeys(s2, v2, 0x3bbb1d);

        Assert.assertEquals("TRTLux5AuhnYj3xRZE6frnL4j5MLeLFgLcq9SAfJALtFN5iWbjBTPzRQBx6y1nVGXUdnJLZks5zJP1Ucfcaxbn3cDLKnx3Y7cTf", derivedAddress3);

        String derivedAddress4 = Addresses.AddressFromKeys(s2, v2, 0x12);

        Assert.assertEquals("42WJwsziXeM8kty8HnvgNnRMAykDcuzFJF78YG2UPfAGNURDzKsATFeL5asUs1jQidApTyLuFkGyKEw6HbraZKVgCxy89ge", derivedAddress4);
    }

    @Test
    public void TestKeysFromAddress() {
        String address1 = "TRTLuzcJgwAet7R1JZqbBkM9yGAw59VJKJKf2j4nuw3BFej9iZ4YiU1b6RwbL8mBZA2oKQxPfSJQN3mcSRKWLMTX6SVjNvwPptH";

        try {
            PublicKeys keys1 = Addresses.KeysFromAddress(address1, 0x3bbb1d);

            Assert.assertEquals(new PublicKey("7a8a21b9e27a1c968b679e57787f2f24059276dc678f335cc256f1e65796e1c5"), keys1.spendKey);
            Assert.assertEquals(new PublicKey("41239f2acbd51af091bf8d610ac0c3e7e6ae09f7108e42317c177d5a20856676"), keys1.viewKey);

            String address2 = "49Mimt7fmK1LJMbXfeByR37nKjGb4vijtRStXgX7wjt3ZQ9prGCH2mVG7JSn9aSQ7KCqHQGkV8UJgF6fpGuiEGiXD9nT7oS";

            PublicKeys keys2 = Addresses.KeysFromAddress(address2, 0x12);

            Assert.assertEquals(new PublicKey("cc1b1a68c856f47361ed9964f09e72288b23fc1561e7f39227ba933f847ac8c1"), keys2.spendKey);
            Assert.assertEquals(new PublicKey("b3b70ac5706b885a5420749acf226246bddddb76abb8c55448effd337ae9dc6b"), keys2.viewKey);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Test
    public void TestAddressesBothWays() {
        for (int i = 0; i < 10; i++) {
            WalletKeys w = KeyOps.GenerateWalletKeys();

            long[] prefixes = new long[]{0x3bbb1d, 0x12, 0x0, 0x11, 0x222, 0x3333, 0x44444, 0x555555, 0x6666666};

            for (long prefix : prefixes) {
                String derivedAddress1 = Addresses.AddressFromKeys(w.getPrivateSpendKey(), w.getPrivateViewKey(), prefix);

                try {
                    PublicKeys keys1 = Addresses.KeysFromAddress(derivedAddress1, prefix);

                    Assert.assertEquals(w.getPublicSpendKey(), keys1.spendKey);
                    Assert.assertEquals(w.getPublicViewKey(), keys1.viewKey);

                    String derivedAddress2 = Addresses.AddressFromKeys(w.getPublicSpendKey(), w.getPublicViewKey(), prefix);

                    PublicKeys keys2 = Addresses.KeysFromAddress(derivedAddress2, prefix);

                    Assert.assertEquals(w.getPublicSpendKey(), keys2.spendKey);
                    Assert.assertEquals(w.getPublicViewKey(), keys2.viewKey);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.exit(1);
                }
            }
        }
    }
}
