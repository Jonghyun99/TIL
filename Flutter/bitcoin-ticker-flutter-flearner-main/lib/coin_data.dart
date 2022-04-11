import 'package:bitcoin_ticker/Networking.dart';

const apiKey = '4904DF7F-713E-4629-A36C-5D4559775C1E';
const coinApiURL = 'https://rest.coinapi.io/v1/exchangerate';

const List<String> currenciesList = [
  'AUD',
  'BRL',
  'CAD',
  'CNY',
  'EUR',
  'GBP',
  'HKD',
  'IDR',
  'ILS',
  'INR',
  'JPY',
  'MXN',
  'NOK',
  'NZD',
  'PLN',
  'RON',
  'RUB',
  'SEK',
  'SGD',
  'USD',
  'ZAR'
];

const List<String> cryptoList = [
  'BTC',
  'ETH',
  'LTC',
];

class CoinData {
  // String coinName = "BTC";
  // String currency = "USD";
  // CoinData(this.coinName, this.currency);

  Future<dynamic> getCoinData(String currency, String coinName) async {
    NetworkHelper networkHelper = NetworkHelper('$coinApiURL/$currency/$coinName?apikey=$apiKey');
    var coinData = await networkHelper.getData();
    return coinData;
  }
}
