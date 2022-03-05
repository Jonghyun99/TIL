import 'package:flutter/material.dart';

void main() {
  runApp(
    MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text("Poor App-Jonghyun"),
          backgroundColor: Colors.indigo,
          centerTitle: true,
        ),
        backgroundColor: Colors.indigoAccent,
        body: Center(
          child: Image.network('https://play-lh.googleusercontent.com/qjCVuw88gIRHM96nwL26wUR96Xg6i2wg0A6MfB5busOQH0-6Zolm8fDs1n6mg_Yn1lI',
              height:150,

              // image: NetworkImage(

              )
          ),
        ),
      ),
    ),
  );
}
