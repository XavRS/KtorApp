package com.xavrs.service

import com.xavrs.model.Message
import com.xavrs.repository.MessageRepository
import org.koin.standalone.KoinComponent


interface MessageService {
    fun getMessage(title: String  = "Hello", message: String = "Ktor and Koin") : Message
}

class MessageServiceImpl(private val messageRepository: MessageRepository) : MessageService, KoinComponent{
    override fun getMessage(message: String, from: String) : Message{
        return messageRepository.getMessage(message, from)
    }
}
