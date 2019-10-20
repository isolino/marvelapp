package br.com.marvel.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5() : String{
    try {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')

//        val no = BigInteger(1, messageDigest)

//        var hashtext = no.toString(16)
//        while (hashtext.length < 32) {
//            hashtext = "0$hashtext"
//        }
//        return hashtext
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}