import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart';

import 'networking.dart';

const apiURL = 'http://hangang.dkserver.wo.tc/';

class MainScreen extends StatefulWidget {
  @override
  State<MainScreen> createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {

  dynamic hanggangData =0;
  var hanggangTemp ='';

  Future<dynamic> getHangangData () async {
    NetworkHelper networkHelper = NetworkHelper
      ('$apiURL');

    hanggangData = await networkHelper.getData();
    print('Test: $hanggangData\[\'temp\'\]');
    return hanggangData;
  }

  void updateUI(dynamic hangangData){
    setState(() {
      print(hangangData);
      hanggangTemp = hanggangData['temp'];
      print(hanggangTemp);
    });
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        image:DecorationImage(
          image:AssetImage('images/hangang.jpg'),
          fit:BoxFit.cover,
          colorFilter: ColorFilter.mode(
            Colors.white.withOpacity(0.8),BlendMode.dstATop)
        ),
      ),
      child:SafeArea(
        child:TextButton(onPressed: () async{
            await getHangangData();
            updateUI(hanggangData);
        },
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.,
            children: [
              Padding(
                padding: const EdgeInsets.only(top:150.0,left:30.0,right:30.0),
                child: FittedBox(
                  fit:BoxFit.cover,
                  child: Text(
                    '지금 한강 온도는... \n$hanggangTemp도\n\n',
                    style:TextStyle(
                      decoration: TextDecoration.none,
                      fontSize: 50,
                      fontFamily:'sdchild',
                      color:Colors.black87,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
              ),
              Text(
                '한강 물이 제 곬으로 흐른다',
                style:TextStyle(
                  decoration: TextDecoration.none,
                  fontSize: 30,
                  fontFamily:'sdchild',
                  color:Colors.white,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ],
          ),
        )
      ),
    );
  }
}