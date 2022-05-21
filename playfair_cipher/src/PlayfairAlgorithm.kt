import java.lang.Exception

class PlayfairAlgorithm {

    fun encrypt(plainText:String, keyTable:Array<Array<Char?>>){

        try {

            // 암호화 결과 담을 변수
            var encryptText = StringBuilder()

            // 텍스트 알고리즘 처리를 위한 배열화
            var textArray = makeTextArray(plainText)

            println("key: ${keyTable.contentDeepToString()}")
            println("array: $textArray")


            // target의 쌍이 keyTable에서의 좌표값 구하기
            for (textCouple in textArray) {
                var compareArray = ArrayList<Pair<Int, Int>>()
                for (i in textCouple) {
                    for (keyArray in keyTable) {
                        for (j in keyArray) {
                            if (i == j) {
                                var row = keyTable.indexOf(keyArray)
                                var col = keyArray.indexOf(i)
                                compareArray.add(Pair(row, col))
                            }
                        }
                    }
                }
                println(compareArray.toString())
                var firstElement: Char
                var secondElement: Char

                // 규칙 1. 암호화 하려는 두 문자가 서로다른 행과 다른 열에 존재할 경우
                // - 사각형을 그려 서로 수평하는 위치의 반대쪽 문자로 치환
                if (compareArray[0].first != compareArray[1].first && compareArray[0].second != compareArray[1].second) {
                    firstElement = keyTable[compareArray[0].first][compareArray[1].second]!!
                    secondElement = keyTable[compareArray[1].first][compareArray[0].second]!!

                // 규칙 2. 두 문자가 같은 열에 있는 경우
                // - 한 칸 씩 밑으로 내려간다. 내려갈 자리가 없다면 맨 위로
                } else if (compareArray[0].first != compareArray[1].first && compareArray[0].second == compareArray[1].second) {
                    if (keyTable.size - 1 > compareArray[0].first) {
                        firstElement = keyTable[compareArray[0].first + 1][compareArray[0].second]!!
                    } else {
                        firstElement = keyTable[0][compareArray[0].second]!!
                    }

                    if (keyTable.size - 1 > compareArray[1].first) {
                        secondElement = keyTable[compareArray[1].first + 1][compareArray[1].second]!!
                    } else {
                        secondElement = keyTable[0][compareArray[1].second]!!
                    }

                // 규칙 3. 두 문자가 같은 행에 있는 경우
                // - 한 칸씩 으론쪽으로 이동한다. 이동할 자리가 없다면 맨 왼쪽으로
                } else if (compareArray[0].first == compareArray[1].first && compareArray[0].second != compareArray[1].second) {
                    if (keyTable.size - 1 > compareArray[0].second) {
                        firstElement = keyTable[compareArray[0].first][compareArray[0].second+1]!!
                    } else {
                        firstElement = keyTable[compareArray[0].first][0]!!
                    }

                    if (keyTable.size - 1 > compareArray[1].second) {
                        secondElement = keyTable[compareArray[1].first][compareArray[1].second+1]!!
                    } else {
                        secondElement = keyTable[compareArray[1].first][0]!!
                    }
                } else {
                    throw Exception("규칙에 없는 인자값")
                }
                println("fristElement: $firstElement")
                println("secondElement: $secondElement")
            }
        } catch (e:Exception){
            println("예외발생 ${e.printStackTrace()}")
        }
//        복호화 알고리즘
//            - 암호화 때와 같으나 규칙 2,3은 움직인 방향과 반대로 이동한다.

    }



    // HELLO -> [[H,E],[L,X],[O,X]] 암,복호화 알고리즘에 사용되는 텍스트 배열 생성
    private fun makeTextArray(plainText: String): ArrayList<ArrayList<Char>> {
        var plainTextList: ArrayList<ArrayList<Char>> = ArrayList<ArrayList<Char>>()
        plainTextList.add(ArrayList())

        var flag = 0 // for문 2개씩 끊기 위한 flag 변수
        var cnt = 0   //for 카운터 변수
        for (i in plainText.uppercase()) {
            if (flag >= 2) {
                flag = 0
                cnt++
                plainTextList.add(ArrayList())
            }
            if (!plainTextList[cnt].isEmpty() && plainTextList[cnt][0] == i) {   // 같은 문자일 시 X 삽입
                plainTextList[cnt].add('X')
            } else {
                plainTextList[cnt].add(i)
            }
            flag++
        }

        // 마지막 자리 부족할 시 X 추가
        if (plainTextList[plainTextList.size - 1].size <= 1) {
            plainTextList[plainTextList.size - 1].add('X')
        }
        return plainTextList
    }
}