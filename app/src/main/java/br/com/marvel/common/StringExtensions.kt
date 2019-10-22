package br.com.marvel.common

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.md5() : String{
    try {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray()))
            .toString(16).padStart(32, '0')
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
}