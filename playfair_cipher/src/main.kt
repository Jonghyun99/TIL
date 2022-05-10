package com.main

import KeyTable
import java.util.*

fun main() {
    var plainText:String="" // 평문
    var cipherText:String="APPLE"
    var cipherKey = makeCipherKey(cipherText.uppercase(Locale.getDefault()))  //암호문
    var keyTable:KeyTable = KeyTable()

    var key = keyTable.makeKey(cipherKey)

    println("resultKey:${key.contentDeepToString()}")
}

fun makeCipherKey(plainText:String):String{
    var cipherKey:String=""
    for(i in plainText.indices){
        if(plainText.indexOf(plainText.elementAt(i))==i){
            cipherKey+=plainText.elementAt(i)
        }
    }
    return cipherKey
}

