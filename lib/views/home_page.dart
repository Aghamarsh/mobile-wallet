// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'create_new_address.dart';
import 'package:mobile_wallet/fork/config.dart';

class HomePage extends StatefulWidget {
  @override
  HomePageState createState() => HomePageState();
}

class HomePageState extends State<HomePage> {
  final _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: FutureBuilder(
        future: checkLogin(),
        builder: (ctx, snapshot) {
          if (snapshot.connectionState != ConnectionState.done) {
            return Material(
              child: Center(
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Image.asset('assets/turtlecoin_stacked_color.png'),
                  ],
                ),
              ),
            );
          }

          if (snapshot.hasData) {
            if (snapshot.data) {
              return CreateNewAddressPage();
            } else {
              return Material(
                child: Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      Image.asset(
                        'assets/turtlecoin_symbol_color.png',
                        height: 300,
                        width: 300,
                      ),
                      RaisedButton(
                        color: brandColor,
                        textColor: Colors.white,
                        onPressed: () {
                          Navigator.of(context).pushNamed('/createAddress');
                        },
                        child: Text('   Create a New Wallet   '),
                      ),
                      RaisedButton(
                        color: brandColor,
                        textColor: Colors.white,
                        onPressed: () {
                            Navigator.of(context).pushNamed('/password');
                        },
                        child: Text('Import Wallet from keys'),
                      ),
                      RaisedButton(
                        color: brandColor,
                        textColor: Colors.white,
                        onPressed: () {
                          _scaffoldKey.currentState.showSnackBar(SnackBar(
                            content: Text(
                              'Feature Not Implemented yet. Please wait for next version',
                            ),
                          ));
                        },
                        child: Text(' Import Wallet from file '),
                      ),
                      RaisedButton(
                        color: brandColor,
                        textColor: Colors.white,
                        onPressed: () {
                          _scaffoldKey.currentState.showSnackBar(SnackBar(
                            content: Text(
                              'Feature Not Implemented yet. Please wait for next version',
                            ),
                          ));
                        },
                        child: Text('Import Wallet from seed'),
                      ),
                      Padding(
                        padding: EdgeInsets.symmetric(vertical: 30),
                      ),
                    ],
                  ),
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
        return false;
      }
    } else {
      prefs.setBool("login", false);
      return false;
    }
  }
}
