import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:mobile_wallet/fork/config.dart';

class _Transaction{
  String amount;
  String incomingAddr;

  _Transaction(this.amount,this.incomingAddr);
}

class Dashboard extends StatefulWidget {
  final String address;
  Dashboard({@required this.address}):super();
  
  @override
  _DashboardState createState() => _DashboardState();
}

class _DashboardState extends State<Dashboard> {

  final list = List<_Transaction>.generate(
  10, (i) => _Transaction(i.toString(), "addr $i"));

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: new Container(
        child: Center(
          child: Column(
            children: <Widget>[
              Text("Wallet Balance"),
              Row(
                children: <Widget>[
                  Text("2000.00"),
                  Text("TRTL"),
                ],
              ),
              Text("Wallet Syncing... "),
              Text(
                "12.21%",
                textAlign: TextAlign.left,
              ),
              Text(
                "Previous Transactions",
                textAlign: TextAlign.start,
              ),
              
            ],
          ),
        ),
      ),
    );
  }
}