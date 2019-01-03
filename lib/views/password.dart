import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:mobile_wallet/fork/config.dart';

class ImportFromKeys extends StatefulWidget {
  @override
  _ImportFromKeysState createState() => _ImportFromKeysState();
}

class _ImportFromKeysState extends State<ImportFromKeys> {
  String privSpendKey;
  String privViewKey;
  int blockid;

  void _import() {}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
          padding: const EdgeInsets.all(40.0),
          child:Form(
          autovalidate: true,
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              Text("Import From Keys"),
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              TextFormField(
                decoration: InputDecoration(
                  labelText: "Enter the Private Spend Key",
                ),
                keyboardType: TextInputType.text,
              ),
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              TextFormField(
                decoration: InputDecoration(
                  labelText: "Enter the Private View Key",
                ),
                keyboardType: TextInputType.text,
              ),
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              TextFormField(
                decoration: InputDecoration(
                  labelText: "Enter the block hash id to start sync",
                ),
                keyboardType: TextInputType.numberWithOptions(),
              ),
              Padding(
                padding: const EdgeInsets.all(20.0),
              ),
              RaisedButton(
                onPressed: _import,
                child: Text("Import"),
              ),
            ],
          )),
    ));
  }
}
