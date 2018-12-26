// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

package lol.turtlecoin.mobilewallet;

import android.os.Bundle;

import java.util.HashMap;

import io.flutter.app.FlutterActivity;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugins.GeneratedPluginRegistrant;
import lol.turtlecoin.mobilewallet.crypto.Addresses;
import lol.turtlecoin.mobilewallet.crypto.KeyOps;
import lol.turtlecoin.mobilewallet.crypto.keytypes.WalletKeys;

public class MainActivity extends FlutterActivity {

    private static final String ADDRESS_CHANNEL = "lol.turtlecoin.mobilewallet/address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);

        new MethodChannel(getFlutterView(), ADDRESS_CHANNEL).setMethodCallHandler(
                new MethodCallHandler() {
                    @Override
                    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
                        if (methodCall.method.equals("createNewAddress")) {
                            WalletKeys w = KeyOps.GenerateWalletKeys();
                            String address = "";
                            if (methodCall.argument("prefix") instanceof Integer) {
                                Integer prefix = methodCall.argument("prefix");
                                address = Addresses.AddressFromKeys(w.getPublicSpendKey(), w.getPublicViewKey(), prefix);
                            } else if (methodCall.argument("prefix") instanceof Long) {
                                Long prefix = methodCall.argument("prefix");
                                address = Addresses.AddressFromKeys(w.getPublicSpendKey(), w.getPublicViewKey(), prefix);
                            }

                            HashMap<String, String> res = new HashMap<>();
                            res.put("address",address);
                            res.put("privateSpendKey", w.getPrivateSpendKey().toString());
                            res.put("privateViewKey", w.getPrivateViewKey().toString());
                            res.put("publicSpendKey", w.getPublicSpendKey().toString());
                            res.put("publicViewKey", w.getPublicViewKey().toString());

                            result.success(res);
                        } else {
                            result.notImplemented();
                        }
                    }
                }
        );
    }
}
