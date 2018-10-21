import com.xavrs.di.myModule
import org.junit.After
import org.junit.Before
import org.koin.core.Koin
import org.koin.log.PrintLogger
import org.koin.standalone.StandAloneContext

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
}