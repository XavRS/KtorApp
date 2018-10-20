package com.xavrs.service

import com.xavrs.repository.HelloRepository
import org.koin.standalone.KoinComponent


interface HelloService {
    fun sayHelloWorld() : String
}

class HelloServiceImpl(private val helloRepository: HelloRepository) : HelloService, KoinComponent{
    override fun sayHelloWorld() : String{
        return "Hello ${helloRepository.getHello()}"
    }
}