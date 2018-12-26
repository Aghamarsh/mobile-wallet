// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'package:flutter/material.dart';
import 'views/create_new_address.dart';

void main() => runApp(
  MaterialApp(
    debugShowCheckedModeBanner: false,
    title: 'Turtle Wallet',
    home: CreateNewAddressPage(),
  )
);

