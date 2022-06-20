package com.main

import KeyTable
import PlayfairAlgorithm

fun main() {
    var cipherKey:String = "TEST"
    var keyTable:KeyTable = KeyTable()
//    println(keyTable.makeKeyTable("ILIKEAPPLE").contentDeepToString())
    keyTable.makeKeyTable(cipherKey)
}

