package jp.snowgirl.test_program

import jp.snowgirl.test_program.dto.Item
import jp.snowgirl.test_program.dto.Model
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.content.*
import org.jetbrains.ktor.features.Compression
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.response.respond
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing
import java.io.File


fun Application.main() {
    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)
    install(GsonSupport) {
        setPrettyPrinting()
    }

    val model = Model("root", listOf(Item("A", "Apache"), Item("B", "Bing")))
    routing {
        static {
            staticRootFolder = File("/usr/local/payara41/glassfish/domains/domain1/applications/snowgirl")
            static("assets") {
                files("assets")
            }
            default("index.html")
        }
        get("/app/") {
            call.respond(model)
        }
    }
}