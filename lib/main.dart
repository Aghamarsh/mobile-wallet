// Copyright (c) 2018, Rashed Mohammed, The TurtleCoin Developers.
//
// Please see the included LICENSE file for more information.

import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/foundation.dart';
import 'views/app.dart';

void main() {
  _setTargetPlatformForDesktop();
  runApp(WalletApp());
}

/// If the current platform is desktop, override the default platform to
/// a supported platform (iOS for macOS, Android for Linux and Windows).
/// Otherwise, do nothing.
void _setTargetPlatformForDesktop() {
  TargetPlatform targetPlatform;
  if (Platform.isMacOS) {
    targetPlatform = TargetPlatform.iOS;
  } else if (Platform.isLinux || Platform.isWindows) {
    targetPlatform = TargetPlatform.android;
  }
  if (targetPlatform != null) {
    debugDefaultTargetPlatformOverride = targetPlatform;
  }
}
