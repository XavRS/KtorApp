import com.xavrs.ktor.routes
import io.ktor.application.Application
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import org.junit.Test
import kotlin.test.assertEquals

class KtorAppRoutesTest : KtorAppBaseTest(){

    @Test
    fun when_route_hello_says_hello() = withTestApplication(Application::routes){
        with(handleRequest(HttpMethod.Get, "/hello")){
            assertEquals(HttpStatusCode.OK, response.status())
            assertEquals("Hello Ktor & Koin", response.content)
        }
    }

    @Test
    fun when_not_valid_route_then_not_found() = withTestApplication(Application::routes){
        with(handleRequest(HttpMethod.Get, "/not_valid_route")){
            assertEquals(null, response.status())
        }
    }
}