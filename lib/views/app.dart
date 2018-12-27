// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'create_new_address.dart';

class WalletApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: FutureBuilder(
        future: checkLogin(),
        builder: (ctx, snapshot) {
          if (snapshot.connectionState != ConnectionState.done) {
            return Material(
              child: Center(
                child: CircularProgressIndicator(),
              ),
            );
          }

          if (snapshot.hasData) {
            if (snapshot.data) {
              return CreateNewAddressPage();
            } else {
              return Material(
                child: Center(
                  child: Text('No login found'),
                ),
              );
            }
          } else {
            return Material(
              child: Center(
                child: Text('${snapshot.error}'),
              ),
            );
          }
        },
      ),
    );
  }

  Future<bool> checkLogin() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    await Future.delayed(const Duration(seconds: 2));
    if (prefs.getKeys().contains("login")) {
      if (prefs.getBool("login")) {
        return true;
      } else {
        prefs.setBool("login", true);
        return false;
      }
    } else {
      prefs.setBool("login", true);
      return false;
    }
  }
}
