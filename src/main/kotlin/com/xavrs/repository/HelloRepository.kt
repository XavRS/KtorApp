package com.xavrs.repository

import com.xavrs.model.HelloMessage

class HelloRepository {

    fun getHello(message: String, from: String) = HelloMessage(message, from)

}
