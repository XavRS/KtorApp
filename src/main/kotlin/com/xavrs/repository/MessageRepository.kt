package com.xavrs.repository

import com.xavrs.model.Message

class MessageRepository {

    fun getMessage(title: String, message: String) = Message(title, message)

}
