package com.main

import KeyTable
import PlayfairAlgorithm
import java.util.*

fun main() {

    //클래스선언
    var keyClass:KeyTable = KeyTable()
    var cipherClass:PlayfairAlgorithm = PlayfairAlgorithm()

    //변수선언
    var plainText:String="instruments" // 평문
    var cipherText:String="Monarchy" // 암호문자
    var cipherKey = cipherClass.makeCipherKey(cipherText.uppercase(Locale.getDefault()))  //암호문 변형 APPLE -> APLE

    var keyTable = keyClass.makeKey(cipherKey)   // 암호표

    var encString = cipherClass.encrypt(plainText,keyTable)
    println("암호화 값: $encString")

    var decString = cipherClass.decrypt(encString,keyTable)
    println("디코딩 값: $decString")
}




