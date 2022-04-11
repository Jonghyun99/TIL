import 'package:flutter/material.dart';
import 'package:unit_calculator/components/bottom_button.dart';
import 'package:unit_calculator/components/reuseable_card.dart';
import 'package:unit_calculator/constant.dart';


class ResultsPage extends StatelessWidget {

  ResultsPage({required this.weightResult});

  final String weightResult;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('BMI CALCULATOR'),
        centerTitle: true,
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: <Widget>[
          Expanded(
            child: Container(
              padding: EdgeInsets.all(15.0),
              alignment: Alignment.bottomLeft,
              child: Text(
                'Your Result',
                style: kTitleTextStyle,
              ),
            ),
          ),
          Expanded(
            flex: 5,
            child: ReusableCard(
              colour: kActiveCardColour,
              onPress: () {},
              cardChild: Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  Text(
                    weightResult,
                    style: kWeightTextStyle,
                  ),
                  Text(
                    "KG",
                    style: kWeightTextStyle,
                  ),
                ],
              ),
            ),
          ),
          BottomButton(
              onTap: () {
                Navigator.pop(context);
              },
              buttonTitle: 'RE-Convert'),
        ],
      ),
    );
  }
}
