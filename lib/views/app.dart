// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'create_new_address.dart';
import 'splash_screen.dart';
import 'home_page.dart';
import 'import_from_keys.dart';

class WalletApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: SplashScreen(),
      routes: {
        '/createAddress': (BuildContext context) => CreateNewAddressPage(),
        '/impkeys': (BuildContext context) => ImportFromKeys(),
        '/homePage': (BuildContext context) => HomePage(),
      },
    );
  }
}
