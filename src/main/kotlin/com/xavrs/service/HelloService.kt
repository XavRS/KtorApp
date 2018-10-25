package com.xavrs.service

import com.xavrs.model.Message
import com.xavrs.repository.HelloRepository
import org.koin.standalone.KoinComponent


interface HelloService {
    fun sayHello(message: String  = "Hello", from: String = "Ktor and Koin") : Message
}

class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService, KoinComponent{
    override fun sayHello(message: String, from: String) : Message{
        return helloRepository.getHello(message, from)
    }
}
