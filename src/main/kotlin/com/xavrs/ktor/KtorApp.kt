package com.xavrs.ktor

import com.xavrs.di.myModule
import com.xavrs.model.Message
import com.xavrs.service.MessageService
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.application.log
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.commandLineEnvironment
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.ktor.ext.inject

fun Application.main() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
}

fun Application.routes(){
    val service: MessageService by inject()

    routing {
        route("/message"){
            get {
                log.info("GET Method")
                call.respond(HttpStatusCode.OK,service.getMessage())
            }
            post {
                log.info("POST Method")
                val message = call.receive(Message::class)
                call.respond(HttpStatusCode.OK, service.getMessage(message.message, message.from))
            }
            delete{
                log.info("DELETE Method")
                call.respond(HttpStatusCode.NoContent)
            }
        }
    }
}

fun main(args: Array<String>) {
    startKoin(listOf(myModule))
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}
