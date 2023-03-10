import java.lang.Exception

class KeyTable() {

    var keyTable = Array<Array<Char>>(5) { Array<Char>(5) { ' ' } }

    fun makeKeyTable(cipherKey: String): Array<Array<Char>> {
        try {
            var rows = 0
            var columns = 0
            for (char in cipherKey) {
                keyTable[rows][columns] = char
                columns++
                if (columns > 4) {
                    columns = 0
                    rows++
                }
            }
        } catch (e: ArrayIndexOutOfBoundsException) {
            println("배열 대입할 수 있는 값 초과: $e")
            return keyTable
        } catch (e: Exception) {
            println("미지정 오류: $e")
        }


//        println("keyTable: ${keyTable.contentDeepToString()}")
//        println("alphabetTable: ${makeAlphabetArray(cipherKey)}")
        combineAlphabetArrayAndKeyTable(keyTable,makeAlphabetArray(cipherKey))

        return keyTable
    }

    private fun makeAlphabetArray(cipherKey: String): CharArray {
        var alphabetArray = ArrayList<Char>()
        for (i in 'A'..'Z') {
            alphabetArray.add(i)
        }
        return alphabetArray.subtract(cipherKey.toCharArray().asIterable().toSet()).toCharArray()
    }

    private fun combineAlphabetArrayAndKeyTable(keyTable: Array<Array<Char>>, alphabetArray:CharArray){
        println("keyTable = ${keyTable.contentDeepToString()}")
        println("alphabetArray = ${alphabetArray.contentToString()}")
        var rows = 0
        var columns = 0
        for(i in keyTable.indices) {
            for (j in keyTable[columns]) {
                println("j: $j")
            }
        }
    }
}