///**
// * 
// */
//package in.tailor.digi.shop.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
///**
// * 
// */
//@Configuration
//public class DbConfig {
//
//	@Bean
//	public DataSource mySqlDataSource(@Value("${spring.datasource.url}") String url,
//			@Value("${spring.datasource.username}") String userName,
//			@Value("${spring.datasource.password}") String password) {
//		return DataSourceBuilder.create().url(url).username(userName).password(password).build();
//	}
//
//	@Bean
//	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
//		return new JdbcTemplate(dataSource);
//	}
//
//}
