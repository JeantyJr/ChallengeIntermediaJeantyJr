package com.cursokotlinjun.challengemarvelappinter.domain.repository

import com.cursokotlinjun.challengemarvelappinter.BuildConfig
import com.cursokotlinjun.challengemarvelappinter.BuildConfig.PRIVATE_API_KEY
import com.cursokotlinjun.challengemarvelappinter.BuildConfig.PUBLIC_API_KEY
import java.security.MessageDigest


abstract class MarvelRepository {
    protected val authParams = AuthParams(PUBLIC_API_KEY, 1, generateHash())

        protected class AuthParams(
            private val apiKey: String,
            private val ts: Int,
            private val hash: String
        ){
            fun getMap(): HashMap<String, String>{
                val hashMap = HashMap<String, String>()
                hashMap["apikey"] = apiKey
                hashMap["ts"] = ts.toString()
                hashMap["hash"] = hash
                return hashMap
            }
        }

    private fun generateHash(): String = MessageDigest.getInstance("MD5")
        .digest(("1${PRIVATE_API_KEY}${PUBLIC_API_KEY}").toByteArray())
        .joinToString("") { "%02x".format(it)}
}