// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
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
      body: Material(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Image.asset(
                homePageLogo,
                height: 300,
                width: 300,
              ),
              Padding(
                padding: EdgeInsets.only(bottom: 8),
                child: RaisedButton(
                  color: brandColor,
                  textColor: Colors.white,
                  onPressed: () {
                    Navigator.of(context).pushNamed('/createAddress');
                  },
                  child: Text('   Create a New Wallet   '),
                ),
              ),
              Padding(
                padding: EdgeInsets.only(bottom: 8),
                child: RaisedButton(
                  color: brandColor,
                  textColor: Colors.white,
                  onPressed: () {
                    _scaffoldKey.currentState.showSnackBar(SnackBar(
                      content: Text(
                        'Feature Not Implemented yet. Please wait for next version',
                      ),
                      duration: Duration(seconds: 2),
                    ));
                  },
                  child: Text('Import Wallet from keys'),
                ),
              ),
              Padding(
                padding: EdgeInsets.only(bottom: 8),
                child: RaisedButton(
                  color: brandColor,
                  textColor: Colors.white,
                  onPressed: () {
                    _scaffoldKey.currentState.showSnackBar(SnackBar(
                      content: Text(
                        'Feature Not Implemented yet. Please wait for next version',
                      ),
                      duration: Duration(seconds: 2),
                    ));
                  },
                  child: Text(' Import Wallet from file '),
                ),
              ),
              RaisedButton(
                color: brandColor,
                textColor: Colors.white,
                onPressed: () {
                  _scaffoldKey.currentState.showSnackBar(SnackBar(
                    content: Text(
                      'Feature Not Implemented yet. Please wait for next version',
                    ),
                    duration: Duration(seconds: 2),
                  ));
                },
                child: Text('Import Wallet from seed'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
