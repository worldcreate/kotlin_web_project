package jp.snowgirl.test_program

import jp.snowgirl.test_program.thymeleaf.ThymeleafTemplateProcessor
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.server.mvc.MvcFeature
import javax.ws.rs.ApplicationPath


/**
 * Created by riku on 17/04/08.
 */
@ApplicationPath("/")
class Main: ResourceConfig() {
	init {

		packages(this.javaClass.getPackage().getName())

		register(ThymeleafTemplateProcessor::class.java)

		register(MvcFeature::class.java)

	}
}