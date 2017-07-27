package jp.snowgirl.test_program.resource

import jp.snowgirl.test_program.dto.TopInfo
import org.glassfish.jersey.server.mvc.Viewable
import java.time.LocalDateTime
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces


/**
 * Created by riku on 17/06/03.
 */
@Path("hello")
class TestResource {
	@GET
	@Produces()
	fun index() :Viewable {

		val topInfo = TopInfo(LocalDateTime.now(), "Taro", "Yamada")

		return Viewable("/top/top", topInfo)
	}
}