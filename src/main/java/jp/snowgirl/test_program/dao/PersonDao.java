package jp.snowgirl.test_program.dao;

import jp.snowgirl.test_program.config.AppConfig;
import org.seasar.doma.*;
import jp.snowgirl.test_program.entity.Person;

@Dao(config = AppConfig.class)
public interface PersonDao {

	@Select
	Person selectById(Integer id);
}