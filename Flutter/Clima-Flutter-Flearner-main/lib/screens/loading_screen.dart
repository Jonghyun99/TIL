import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

class LoadingScreen extends StatefulWidget {
  @override
  _LoadingScreenState createState() => _LoadingScreenState();
}

class _LoadingScreenState extends State<LoadingScreen> {
  void getLocation() async {
    try {
      LocationPermission permission = await Geolocator.requestPermission();
      Position position = await Geolocator.getCurrentPosition(
          desiredAccuracy: LocationAccuracy.high);
      print(position);
    } catch (e){
      print(e);
    }

  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getLocation();
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