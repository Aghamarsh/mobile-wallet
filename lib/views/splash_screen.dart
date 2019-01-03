// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'package:mobile_wallet/fork/config.dart';

class SplashScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Material(
      child: FutureBuilder<bool>(
        future: checkLogin().then((data) {
          if (data) {
            Navigator.of(context).pushReplacementNamed('/dashboard');
          } else {
            Navigator.of(context).pushReplacementNamed('/homePage');
          }
        }),
        builder: (ctx, snapshot) {
          if (snapshot.connectionState != ConnectionState.done) {
            return Center(
              child: Image.asset(splashLogo),
            );
          }

          if (snapshot.hasError) {
            print('Error while checking for login: ' + snapshot.error);
            return Center(
              child: AlertDialog(
                title: Text('Error'),
                content: Text(
                    'Couldn\'t get login info.\nPlease restart the application'),
              ),
            );
          }

          return Center(
            child: Image.asset('assets/turtlecoin_stacked_color.png'),
          );
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
        return false;
      }
    } else {
      prefs.setBool("login", false);
      return false;
    }
  }
}
