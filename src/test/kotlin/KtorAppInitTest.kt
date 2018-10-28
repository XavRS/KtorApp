import com.xavrs.ktor.main
import io.ktor.application.Application
import io.ktor.server.testing.withTestApplication
import org.junit.Test

class KtorAppInitTest : KtorAppBaseTest() {

    @Test
    fun init_ktorapp()  = withTestApplication(Application::main){

    }
}