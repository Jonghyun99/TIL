import java.lang.Exception

class KeyTable() {

    var key = Array<Array<Char?>>(5) { Array<Char?>(5) { null } }

    fun makeKey(cipherKey: String): Array<Array<Char?>> {
        try {
            var rows = 0
            var columns = 0
            for (char in cipherKey) {
                key[rows][columns] = char
                columns++
                if (columns > 4) {
                    columns = 0
                    rows++
                }
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            println("배열 대입할 수 있는 값 초과: $e")
            return key
        } catch (e: Exception) {
            println("미지정 오류: $e")
        }


        combineAlphabetArrayAndKeyTable(key, makeAlphabetArray(cipherKey))
        return key
    }

    private fun makeAlphabetArray(cipherKey: String): CharArray {
        var alphabetArray = ArrayList<Char>()
        for (i in 'A'..'Z') {
            if(i!='J') {    // I랑 J는 같은 문자로 취급한다.
                alphabetArray.add(i)
            }
        }
        return alphabetArray.subtract(cipherKey.toCharArray().asIterable().toSet()).toCharArray()
    }

    private fun combineAlphabetArrayAndKeyTable(keyTable: Array<Array<Char?>>, alphabetArray: CharArray):Array<Array<Char?>> {
        var columns = 0
        var cnt = 0
        for (i in keyTable.indices) {
            for (j in keyTable[i]) {
                if (j == null) {
                    keyTable[i][columns] = alphabetArray[cnt]
                    cnt++
                }
                columns++
                if(columns>=5) columns=0
            }
        }
        return keyTable
    }
}