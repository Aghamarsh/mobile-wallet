// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'fork/config.dart';

void main() => runApp(
  MaterialApp(
    debugShowCheckedModeBanner: false,
    title: 'Turtle Wallet',
    home: CreateNewAddressPage(),
  )
);

class CreateNewAddressPage extends StatefulWidget {

  @override
  CreateNewAddressPageState createState() => CreateNewAddressPageState();
}

class CreateNewAddressPageState extends State<CreateNewAddressPage> {

  static const address_platform = const MethodChannel('lol.turtlecoin.mobilewallet/address');

  String address = "";
  String privateSpendKey = "";
  String privateViewKey = "";
  String publicViewKey = "";
  String publicSpendKey = "";

  Future<void> _createNewAddress() async {
    try {
      Map<String, dynamic> args = {
        "prefix" : prefix
      };
      Map<dynamic, dynamic> res = await address_platform.invokeMethod('createNewAddress', args);
      address = res["address"];
      privateSpendKey = res["privateSpendKey"];
      privateViewKey = res["privateViewKey"];
      publicSpendKey = res["publicSpendKey"];
      publicViewKey = res["publicViewKey"];
    } on PlatformException catch (e) {
      address = e.message;
    }

    setState(() {
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Padding(
            padding: EdgeInsets.symmetric(vertical: 5),
          ),
          RaisedButton(
            child: Text('Create New Address'),
            onPressed: _createNewAddress,
          ),
          Text('Address :'),
          Padding(
            child: Text(address),
            padding: EdgeInsets.symmetric(horizontal: 10),
          ),
          Text('Private Spend Key :'),
          Padding(
            child: Text(privateSpendKey),
            padding: EdgeInsets.symmetric(horizontal: 10),
          ),
          Text('Private View Key :'),
          Padding(
            child: Text(privateViewKey),
            padding: EdgeInsets.symmetric(horizontal: 10),
          ),
          Text('Public Spend Key :'),
          Padding(
            child: Text(publicSpendKey),
            padding: EdgeInsets.symmetric(horizontal: 10),
          ),
          Text('Public View Key :'),
          Padding(
            child: Text(publicViewKey),
            padding: EdgeInsets.symmetric(horizontal: 10),
          ),
        ],
      ),
    );
  }
}