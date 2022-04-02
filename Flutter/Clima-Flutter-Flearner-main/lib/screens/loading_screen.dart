import 'package:clima_flutter_flearner/screens/location_screen.dart';
import 'package:clima_flutter_flearner/services/networking.dart';
import 'package:flutter/material.dart';
import '../services/location.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';

const apiKey = '3ff7ca57ad855ecc8e9b61a1330adf99';

class LoadingScreen extends StatefulWidget {
  @override
  _LoadingScreenState createState() => _LoadingScreenState();
}

class _LoadingScreenState extends State<LoadingScreen> {

  double latitude = 0.0;
  double longitude = 0.0;


  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getLocationData();
    print('this line of code is triggered');
  }

  void getLocationData() async {
    Location location = Location();

    await location.getCurrentLocation();

    latitude = location.latitude;
    longitude = location.longitude;
    NetworkHelper networkHelper = NetworkHelper('https://api.openweathermap.org/data/2.5/weather?lat=$latitude&lon=$longitude&appid=$apiKey');

    var weatherData = await networkHelper.getData();

    Navigator.push(context, MaterialPageRoute(builder: (context) {
      return LocationScreen();
    }));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:Center(
        child:SpinKitDoubleBounce(
          color: Colors.white,
          size: 100.0,
        )
      )
    );
  }
}