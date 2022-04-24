import 'package:flutter/material.dart';
import 'screens/calculator_screen.dart';

void main() {
  runApp(Calculator());
}

class Calculator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home:CalculatorScreen(),
      );
  }
}
