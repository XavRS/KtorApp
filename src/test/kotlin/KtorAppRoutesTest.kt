import com.google.gson.Gson
import org.junit.Test
import com.xavrs.ktor.routes
import com.xavrs.model.HelloMessage
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.GsonConverter
import io.ktor.http.withCharset
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication
import kotlin.test.assertEquals
import kotlin.test.assertNotNull


class KtorAppRoutesTest : KtorAppBaseTest(){

    @Test
    fun when_get_message_give_a_message() = withTestApplication(Application::routes){
        val gson = Gson()
        application.install(ContentNegotiation) {
            register(ContentType.Application.Json, GsonConverter())
        }
        with(handleRequest(HttpMethod.Get, "/message"){
            addHeader("Accept", "application/json")
        }){
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
            assertEquals("com.xavrs.model.HelloMessage", gson.fromJson(response.content!!, HelloMessage::class.java).javaClass.canonicalName)
            val contentTypeText = assertNotNull(response.headers[HttpHeaders.ContentType])
            assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), ContentType.parse(contentTypeText))
        }
    }

    @Test
    fun when_post_message_then_return_same_message() = withTestApplication(Application::routes) {
        application.install(ContentNegotiation) {
            register(ContentType.Application.Json, GsonConverter())
        }
        with(handleRequest(HttpMethod.Post, "/message") {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/json")
            setBody(getJson("json/hello.json"))
        }) {
            assertEquals(HttpStatusCode.OK, response.status())
            assertNotNull(response.content)
            assertEquals(listOf(getJson("json/hello.json")), response.content!!.lines())
            val contentTypeText = assertNotNull(response.headers[HttpHeaders.ContentType])
            assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), ContentType.parse(contentTypeText))
        }
    }

    @Test
    fun when_not_valid_route_then_null() = withTestApplication(Application::routes){
        with(handleRequest(HttpMethod.Get, "/not_valid_route")){
            assertEquals(null, response.status())
        }
    }
}
