import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:mobile_wallet/fork/config.dart';

class ImportFromKeys extends StatefulWidget {
  @override
  _ImportFromKeysState createState() => _ImportFromKeysState();
}

class _ImportFromKeysState extends State<ImportFromKeys> {

 static const address_platform =
      const MethodChannel('lol.turtlecoin.mobilewallet/address');

  final _spendKeyController = TextEditingController();
  final _viewKeyController = TextEditingController();
  final _blockidController = TextEditingController();

  String _privSpendKey="";
  String _privViewKey="";
  int blockid=0;
  String address="";

  Future<void> _import() async{
    _privSpendKey = _spendKeyController.text;
    _privViewKey = _viewKeyController.text;

    try{
      Map<String, dynamic> args = {"prefix": prefix , 
                                    "Spendkey" : _privSpendKey,
                                    "ViewKey": _privViewKey };

    address=await address_platform.invokeMethod('importFromKeys', args);
    }
    on PlatformException catch(e){
      address=e.message;
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
          padding: const EdgeInsets.all(30.0),
          child:Form(
          autovalidate: true,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisSize: MainAxisSize.max,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.all(20.0),
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
                controller: _spendKeyController,
              ),
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              TextFormField(
                decoration: InputDecoration(
                  labelText: "Enter the Private View Key",
                ),
                keyboardType: TextInputType.text,
                controller: _viewKeyController,
              ),
              Padding(
                padding: const EdgeInsets.all(25.0),
              ),
              TextFormField(
                decoration: InputDecoration(
                  labelText: "Enter the block hash id to start sync",
                ),
                keyboardType: TextInputType.numberWithOptions(),
                controller: _blockidController,
              ),
              Padding(
                padding: const EdgeInsets.all(20.0),
              ),
              RaisedButton(
                onPressed: _import,
                color: brandColor,
                textColor: Colors.white,
                child: Text("Import"),
              ),
              Text(address),
            ],
          )),
    ));
  }
}
