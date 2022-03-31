import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

import '../services/location.dart';

class LoadingScreen extends StatefulWidget {
  @override
  _LoadingScreenState createState() => _LoadingScreenState();
}

class _LoadingScreenState extends State<LoadingScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getLocation();
    print('this line of code is triggered');
  }

  void getLocation() async {
    Location location = Location();
    await location.getCurrentLocation();
    print(location.latitude);

  }

  @override
  Widget build(BuildContext context) {
    String myMargin = '15';
    double myMarginAsADouble=0.0;
    try {
      myMarginAsADouble = double.parse(myMargin);
    } catch (e) {
      print(e);
      myMarginAsADouble = 30.0;
    }
    return Scaffold(
        body: Container(
          color: Colors.red,
          margin: EdgeInsets.all(myMarginAsADouble ?? 30.0 ),
        )
    );
  }
}