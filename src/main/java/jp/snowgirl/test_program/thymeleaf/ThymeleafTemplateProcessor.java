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
public class ThymeleafTemplateProcessor implements TemplateProcessor<String> {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	@Context
	private ServletContext servletContext;

	private final TemplateEngine templateEngine;

	public ThymeleafTemplateProcessor() {
		TemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheTTLMs(3600000L);

		templateEngine = new TemplateEngine();
		templateEngine.setTemplateResolver(resolver);
	}

	@Override
	public String resolve(String name, MediaType mediaType) {

		return name;
	}

	@Override
	public void writeTo(String templateReference, Viewable viewable, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out) throws IOException {

		WebContext context = new WebContext(request, response, servletContext);

		context.setVariable("item", viewable.getModel());

		Writer writer = new OutputStreamWriter(out);
		templateEngine.process(templateReference, context, writer);

		writer.flush();
	}
}