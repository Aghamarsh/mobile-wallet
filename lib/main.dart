import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
      Map<String, int> args = {
        "prefix" : 0x3bbb1d
      };
      Map<String, String> res = await address_platform.invokeMethod('createNewAddress', args);
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
          RaisedButton(
            child: Text('Create New Address'),
            onPressed: _createNewAddress,
          ),
          Text('Address :'),
          Text(address),
          Text('Private Spend Key :'),
          Text(privateSpendKey)
        ],
      ),
    );
  }
}