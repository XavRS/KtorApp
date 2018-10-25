package com.xavrs.model

abstract class Message

data class HelloMessage(
    val message: String,
    val from: String
) : Message()