package com.b2w.tax.service;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.b2w.tax.exception.TaxException;
import com.fasterxml.jackson.core.JsonProcessingException;

@EnableCaching
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.b2w.tax.service", "com.b2w.tax.web.controller", "com.b2winc.corpserv.health",
		"com.b2w.labs.core.service" })
@PropertySource("classpath:application-test.properties")
@EnableJpaRepositories(basePackages = { "com.b2w.tax.dao", "com.b2w.labs.core.dao" })
@EntityScan(basePackages = { "com.b2w.tax.model.database", "com.b2w.labs.core.model.database" })
public class ApplicationTest extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationTest.class);
	}

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(net.sf.ehcache.CacheManager.create());
	}

	@Bean
	public EhCacheManagerFactoryBean ehCacheCacheManager() {
		EhCacheManagerFactoryBean cmfb = new EhCacheManagerFactoryBean();
		cmfb.setConfigLocation(new ClassPathResource("ehcache.xml"));
		cmfb.setShared(true);
		cmfb.setCacheManagerName("cacheManager");
		return cmfb;
	}

	@Bean
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl(System.getProperty("jdbc.url"));
		ds.setUsername(System.getProperty("db.user"));
		ds.setPassword((System.getProperty("db.pass")));
		ds.setTestOnBorrow(true);
		ds.setValidationQuery("SELECT * FROM DUAL");
		ds.setMaxWait(5000);
		ds.setTestWhileIdle(true);
		return ds;
	}

	public static void main(String[] args) throws TaxException, JsonProcessingException {
		// ApplicationContext ctx = new
		// AnnotationConfigApplicationContext(Application.class);
		// ImpostosController cont = (ImpostosController)
		// ctx.getBean("impostosController");

	}

}
