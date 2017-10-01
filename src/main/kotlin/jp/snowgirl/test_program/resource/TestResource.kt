package jp.snowgirl.test_program.resource.TestResource

import jp.snowgirl.test_program.config.AppConfig
import jp.snowgirl.test_program.config.DaoFactory
import jp.snowgirl.test_program.dao.PersonDao
import jp.snowgirl.test_program.dto.Item
import jp.snowgirl.test_program.dto.Model
import org.jetbrains.ktor.locations.get
import org.jetbrains.ktor.locations.location
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.Route

@location("/app/test")
class Index

fun Route.test() {
    get<Index> {
        val dao = DaoFactory.create(PersonDao::class)
        val tm = AppConfig.transactionManager
        tm.required {
            val person = dao.selectById(1)
            println(person)
        }

        val model = Model("root", listOf(Item("A", "Apache"), Item("B", "Bing")))
        call.respond(model)
    }
}