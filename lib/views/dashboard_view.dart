import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:mobile_wallet/fork/config.dart';

class _Transaction {
  String incomingAddr;
  String amount;

  _Transaction(this.amount, this.incomingAddr);
}

class Dashboard extends StatefulWidget {
  @override
  _DashboardState createState() => _DashboardState();
}

class _DashboardState extends State<Dashboard> {
  double balance = 0.0;
  int _selectedIndex = 1;
  String status = "Wallet Syncing ... ";
  int percentage = 12;
  final _widgetOptions = [
    Text('Index 0: sendTrtl'),
    Text('Index 1: AddTrtl'),
    Text('Index 2: RecieveTrtl'),
  ];
  final list = List<_Transaction>.generate(
      10, (i) => _Transaction(i.toString(), "addr $i"));

  void _getWalletDetails(String address) {}

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
                  Text(balance.toString()),
                  Text("TRTL"),
                ],
              ),
              Text(status),
              Text(
                percentage.toString() + "%",
                textAlign: TextAlign.left,
              ),
              Text(
                "Previous Transactions",
                textAlign: TextAlign.start,
              ),
              new ListView.builder(
                itemCount: 10,
                itemBuilder: (BuildContext context, int index) {
                  return ListTile(
                    title: Text('${list[index]}.amount'),
                    subtitle: Text('${list[index].incomingAddr}'),
                  );
                },
              ),
            ],
          ),
        ),
      ),
    );
  }

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }
}

class AddressDisp extends StatelessWidget {
  final String add;

  AddressDisp({@required this.add}) {
    //_DashboardState d;
    //d._getWalletDetails(add);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Container(
      child: new Dashboard(),
    ));
  }
}
