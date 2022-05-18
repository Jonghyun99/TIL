class PlayfairAlgorithm {

    fun encrypt(plainText:String, key:Array<Array<Char?>>){
        var encryptText = arrayListOf<Char>() // 암호화 결과 담을 변수

        makeTextArray(plainText)    // 텍스트 알고리즘 처리를 위한7 ㅡ, 배열화
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