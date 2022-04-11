import 'dart:math';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:rflutter_alert/rflutter_alert.dart';


class GameBrain{
  int _levelNumber = 0;
  int randomAnswer= Random().nextInt(2)+1;
  List<Widget> scoreLst = [];


  void sufflexAnswer(){
    randomAnswer= Random().nextInt(2)+1;
  }

  bool checkAnswer(int choiceAnswer, context){
    sufflexAnswer();
    if(choiceAnswer==randomAnswer){
      _levelNumber++;
      scoreLst.add( Icon(
        Icons.check,
        color: Colors.green,
      ));
      return true;
    }
    Alert(context: context, title: "GameOver", desc: "Your Luck is $_levelNumber Point").show();
    _levelNumber=0;
    scoreLst = [];
    return false;
  }
}