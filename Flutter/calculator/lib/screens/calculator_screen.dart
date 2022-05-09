import 'package:calculator/componant/keypad.dart';
import 'package:flutter/material.dart';
import 'package:calculator/constant/constants.dart';

class CalculatorScreen extends StatelessWidget {
  const CalculatorScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // backgroundColor: Color(0xff10403B),
      backgroundColor: Constant.basicColor,
      appBar: AppBar(
        title: const Text('MiniCalculator'),
        backgroundColor: Constant.pointColor,
      ),
      body: SafeArea(
        child: Container(
          child: Column(
            children: [
              Container(
                height: 200.0,
                decoration: const BoxDecoration(
                  color: Color(0xffBFBFBF),
                  border: Border(
                    bottom: BorderSide(
                      color: Constant.pointColor,
                      width: 2.0,
                    )
                  ),
                ),
              ),
              KeyPad(),
            ],
          ),
        ),
      ),
    );
  }
}
