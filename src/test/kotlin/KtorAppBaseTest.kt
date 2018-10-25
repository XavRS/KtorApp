import com.xavrs.di.myModule
import org.junit.After
import org.junit.Before
import org.koin.core.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext
import java.io.File

open class KtorAppBaseTest {

    @Before
    fun init(){
        Koin.logger = PrintLogger()
        StandAloneContext.startKoin(listOf(myModule))
    }

    @After
    fun finish(){
        StandAloneContext.stopKoin()
    }

    protected fun getJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}