import 'dart:math';

import 'package:unit_calculator/screens/input_page.dart';

class CalculatorBrain {
  CalculatorBrain({required this.weight, required this.weightType});

  final int weight;
  final Unit weightType;

  double resultWeight = 0;

  String calculateUnit() {
      print(weightType);
      print(weight);
    if (weightType == Unit.lb) {
      resultWeight = weight * 2.205;
    } else {
      resultWeight = weight / 2.205;
    }
    return resultWeight.toStringAsFixed(1);
  }
}
