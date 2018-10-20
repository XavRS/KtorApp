package com.xavrs.ktor

import com.xavrs.di.myModule
import com.xavrs.service.HelloService
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.ktor.ext.inject

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
}

fun Application.routes(){
    val service: HelloService by inject()

    routing {
        get("/hello") {
            call.respondText(service.sayHelloWorld())
        }
    }
}

fun main(args: Array<String>) {
    startKoin(listOf(myModule))
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}