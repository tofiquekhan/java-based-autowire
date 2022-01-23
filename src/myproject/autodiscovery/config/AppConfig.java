package myproject.autodiscovery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

import myproject.autodiscovery.controller.StudentController;
import myproject.autodiscovery.controller.StudentControllerImpl;
import myproject.autodiscovery.dao.StudentDao;
import myproject.autodiscovery.dao.StudentDeoImpl;
import myproject.autodiscovery.service.StudentService;
import myproject.autodiscovery.service.StudentServiceImpl;

@Configuration
public class AppConfig {

	@Bean
	public MysqlDataSource dataSource() {
		MysqlDataSource mysqlDataSource =  null;
		try {
			mysqlDataSource = new MysqlDataSource();
			mysqlDataSource.setUrl("jdbc:mysql://localhost:3306/springpractise");
			mysqlDataSource.setUser("root");
			mysqlDataSource.setPassword("root");
		}catch (Exception e) {
			// TODO: handle exception
		}
		return mysqlDataSource;
	}
	
	@Bean
	public StudentDao studentDao() {
		StudentDao studentDao = new StudentDeoImpl();
		return studentDao;
	}
	

	@Bean
	public StudentService studentService() {
		StudentService studentService = new StudentServiceImpl();
		return studentService;
	}
	
	@Bean 
	public StudentController studentController() {
		StudentController studentController = new StudentControllerImpl();
		return studentController;
	}
}
