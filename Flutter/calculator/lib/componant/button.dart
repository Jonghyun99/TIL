import 'package:calculator/constant/constants.dart';
import 'package:flutter/material.dart';

class CalculatorButton extends StatelessWidget {
  final String type;
  final int flex;
  final String buttonText;
  final VoidCallback onPressed;
  const CalculatorButton({
    Key? key,
    required this.buttonText,
    required this.onPressed,
    this.flex = 1,
    this.type = "number",
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Expanded(
      flex: flex,
      child: Container(
        margin: const EdgeInsets.symmetric(
          vertical: 10,
          horizontal: 5,
        ),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(50),
        ),
        child: TextButton(
          child: Text(
            buttonText,
            style: const TextStyle(
              fontSize: 32,
              color: Constant.pointColor,
            ),
          ),
          onPressed: onPressed,
        ),
      ),
    );
  }

  Color bgColor() {
    switch (type) {
      case "number":
        return Constant.pointColor;
      case "operator":
        return Constant.pointColor;
      case "function":
        return Constant.pointColor;
      default:
        return Constant.pointColor;
    }
  }

  Color fgColor() {
    switch (type) {
      case "number":
        return Constant.pointColor;
      case "operator":
        return Constant.pointColor;
      case "function":
        return Constant.pointColor;
      default:
        return Constant.pointColor;
    }
  }
}