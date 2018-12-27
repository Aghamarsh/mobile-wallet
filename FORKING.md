# Forking Guide

Thanks for coming here.

## Step 1 : License (You may be thinking let's skip this - but don't)

#### Note: Never ever do a Replace All while forking any Open Source Project. If you are found doing this, A DMCA takedown notice will be issued immediately.

Add the following line **below** the license header **only** for the files you
edit on your own without copying. If copied please add the source (URL,
reference books etc.) and the copyright holder name to the below line.

```
// Copyright (c) 2018, <Name of the Copyright Holder>.
```

## Step 2 : Changing Coin specific values

This project is designed to be very easy to fork. All you have to do is change
the values present in `lib/fork/` directory and that's it

## Step 3 : Changing Application Name

**For Android** - change the string value of `app_name` present in `android/app/src/main/res/values/strings.xml`

**For iOS** - change the string value of `CFBundleName` present in `ios/Runner/Info.plist`

## Step 4 : Changing Icons

**For Flutter - Compulsory** - replace the files present in `assets` directory
and edit the `pubspec.yaml` file to reflect those changes

**For Android** - replace the png and xml files present in `android/app/src/main/res/*`

**For iOS** - replace the png files present in `ios/Runner/Assets.xcassets/*` and
edit the `Contents.json` accordingly.

If you need more help, feel free to hop in to our [Discord Server](http://chat.turtlecoin.lol)
