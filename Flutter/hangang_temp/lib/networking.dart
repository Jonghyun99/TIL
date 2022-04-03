import 'package:http/http.dart' as http;
import 'dart:convert';

const apiURL = 'http://hangang.dkserver.wo.tc/';

class NetworkHelper {
  NetworkHelper(this.url);

  final String url;

  Future getData() async {
    Uri uri = Uri.parse(url);
    http.Response response = await http.get(uri);
    if (response.statusCode == 200) {
      String data = response.body;
      return jsonDecode(data);
    } else {
      print(response.statusCode);
    }
  }

  Future<dynamic> getHangangData () async {
    NetworkHelper networkHelper = NetworkHelper
      ('$apiURL');

    dynamic hanggangData = await networkHelper.getData();
    print(hanggangData['temp']);
    return hanggangData;
  }
}
