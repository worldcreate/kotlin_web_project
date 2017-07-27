package jp.snowgirl.test_program.thymeleaf;

import org.glassfish.jersey.server.mvc.Viewable;
import org.glassfish.jersey.server.mvc.spi.TemplateProcessor;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Created by riku on 17/06/11.
 */
@Provider
class ThymeleafTemplateProcessor: TemplateProcessor<String> {

	@Context
	var request:  HttpServletRequest? = null

	@Context
	var response: HttpServletResponse? = null

	@Context
	var servletContext: ServletContext? = null

	private var templateEngine:TemplateEngine? = null

	constructor() {
		var resolver = ServletContextTemplateResolver()
		resolver.setPrefix("/WEB-INF/view/")
		resolver.setSuffix(".html")
		resolver.setTemplateMode("HTML5")
		resolver.setCacheTTLMs(3600000L)

		templateEngine = TemplateEngine()
		templateEngine?.setTemplateResolver(resolver)
	}

	override fun resolve(name:String , mediaType:MediaType ) : String {
		return name
	}

	@Throws(IOException::class)
	override fun writeTo(templateReference: String , viewable:Viewable , mediaType:MediaType , httpHeaders: MultivaluedMap<String, Any> , out: OutputStream ) {

		var context = WebContext(request, response, servletContext)

		context.setVariable("item", viewable.getModel())

		var writer = OutputStreamWriter(out)
		templateEngine?.process(templateReference, context, writer)

		writer.flush()
	}
}