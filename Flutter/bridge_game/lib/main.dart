import 'package:bridge_game/game_brain.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:rflutter_alert/rflutter_alert.dart';


void main() {
  runApp(BridgeGame());
}

class BridgeGame extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        fontFamily: 'malgun',
      ),
      home: Scaffold(
          backgroundColor: Colors.blue,
          appBar: AppBar(
            title: Text('Test Your Luck'),
            titleTextStyle: TextStyle(
              fontFamily: 'malgun',
              fontSize: 35.0,
              color: Colors.white70,
              fontWeight: FontWeight.bold,
            ),
            centerTitle: true,
          ),
          body: BridgePage()),
    );
  }
}

class BridgePage extends StatefulWidget {
  const BridgePage({Key? key}) : super(key: key);

  @override
  State<BridgePage> createState() => _BridgePageState();
}

class _BridgePageState extends State<BridgePage> {

  GameBrain gameBrain= GameBrain();

  // void showAlert(point, context){
  //   Alert(context: context, title: "Fail", desc: "Your Luck is $point Point",
  //     buttons: [
  //       DialogButton(
  //         child: Text(
  //           "Retry",
  //           style: TextStyle(color: Colors.white, fontSize: 20),
  //         ),
  //         onPressed: () => Navigator.pop(context),
  //         width: 120,
  //       )
  //     ],).show();
  // }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Column(
        verticalDirection: VerticalDirection.down,
        children: <Widget>[
          Expanded(
            flex: 2,
            child: Row(
              mainAxisSize: MainAxisSize.max,
              children: [
                Expanded(
                  flex: 1,
                  child: Padding(
                    padding: const EdgeInsets.all(25.0),
                    child: TextButton(
                      onPressed: () {
                        setState(() {
                          gameBrain.checkAnswer(1, context);
                        });
                      },
                      style: TextButton.styleFrom(
                        backgroundColor: Colors.blueGrey,
                      ),
                      // margin: EdgeInsets.all(30.0),
                      // height: 100,
                      child: const Center(
                        child: Text(
                          'LEFT',
                          style: TextStyle(
                            fontSize: 45,
                            fontFamily: 'malgun',
                            color: Colors.black,
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                Expanded(
                  flex: 1,
                  child: Padding(
                    padding: const EdgeInsets.all(25.0),
                    child: TextButton(
                      onPressed: () {
                        setState(() {
                          gameBrain.checkAnswer(2, context);
                        });
                      },
                      style: TextButton.styleFrom(
                        backgroundColor: Colors.blueGrey,
                      ),
                      // margin: EdgeInsets.all(30.0),
                      // height: 100,
                      child: const Center(
                        child: Text(
                          'RIGHT',
                          style: TextStyle(fontSize: 45, fontFamily: 'malgun',color: Colors.black,),
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            ),
          ),
          Expanded(
            child: Container(
              // height: 100,
              color: Colors.blueGrey,
                child: Row(
                  children: gameBrain.scoreLst,
                )
            ),
          ),
        ],
      ),
    );
  }
}
