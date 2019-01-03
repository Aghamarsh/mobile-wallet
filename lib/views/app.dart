// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'create_new_address.dart';
import 'home_page.dart';
import 'password.dart';

class WalletApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomePage(),
      routes: {
        '/createAddress': (BuildContext context) => CreateNewAddressPage(),
        '/password': (BuildContext context) => Password(),
      },
    );
  }
}
