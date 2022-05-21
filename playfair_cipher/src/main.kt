package com.main

import KeyTable
import PlayfairAlgorithm
import java.util.*

fun main() {

    //클래스선언
    var keyClass:KeyTable = KeyTable()
    var cipherClass:PlayfairAlgorithm = PlayfairAlgorithm()

    //변수선언
    var plainText:String="Hello" // 평문
    var cipherText:String="APPLE" // 암호문자
    var cipherKey = makeCipherKey(cipherText.uppercase(Locale.getDefault()))  //암호문 변형 APPLE -> APLE


    var keyTable = keyClass.makeKey(cipherKey)   // 암호표

    cipherClass.encrypt(plainText,keyTable)
}


//암호키 만드는 함수 ex) APPLE -> APLE
fun makeCipherKey(plainText:String):String{
    var cipherKey:String=""
    for(i in plainText.indices){
        if(plainText.indexOf(plainText.elementAt(i))==i){
            cipherKey+=plainText.elementAt(i)
        }
    }
    return cipherKey
}

