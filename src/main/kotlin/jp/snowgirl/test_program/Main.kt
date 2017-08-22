package jp.snowgirl.test_program

import jp.snowgirl.test_program.resource.TestResource.test
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.content.*
import org.jetbrains.ktor.features.Compression
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.gson.GsonSupport
import org.jetbrains.ktor.locations.Locations
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.routing
import java.io.File


fun Application.main() {
    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)
    install(Locations)
    install(GsonSupport) {
        setPrettyPrinting()
    }

    routing {
        static {
            staticRootFolder = File("/usr/local/payara41/glassfish/domains/domain1/applications/snowgirl")
            static("assets") {
                files("assets")
            }
            default("index.html")
        }
        test()
    }
}