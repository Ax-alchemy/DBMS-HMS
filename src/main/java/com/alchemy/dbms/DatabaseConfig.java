package com.alchemy.dbms;

import com.zaxxer.hikari.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

  @Value("${spring.datasource.url}")
  private String dbUrl;
  
  @Value("${spring.datasource.username}")
  private String dbUser;
  
  @Value("${spring.datasource.password}")
  private String dbpass;

  @Bean
  public DataSource dataSource() {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      config.setUsername(dbUser);
      config.setPassword(dbpass);
      return new HikariDataSource(config);
  }
}