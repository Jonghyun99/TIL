class PlayfairAlgorithm {

    fun encrypt(plainText:String, key:Array<Array<Char?>>){
        var encryptText = arrayListOf<Char>() // 암호화 결과 담을 변수

        var plainTextList:ArrayList<ArrayList<Char>> = ArrayList<ArrayList<Char>>()

        var flag = 0 // for문 2개씩 끊기 위한 flag 변수
        var cnt = 0   //for 카운터 변수
        for(i in plainText.uppercase()){
            //TODO Index 5 out of bounds for length 5
            //배열 생성 패턴 수정 (필요한만큼 늘려야함)
            plainTextList.add(ArrayList())
            plainTextList[cnt].add(i)
            flag++
            println("planTextList = $plainTextList")
            if(flag>=2){
                flag=0
                cnt++
            }
        }

        // 마지막 자리 부족할 시 X 추가
        if(plainTextList[plainTextList.size].size<=1){
            plainTextList[plainTextList.size].add('X')
        }

        println(plainTextList.toString())
    }
}