package makarchuk.test.config;


import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import makarchuk.test.DemoApplication;
import makarchuk.test.filter.JwtFilter;

@EnableWebMvc
@Configuration
@EnableJpaRepositories(basePackageClasses = DemoApplication.class)
public class JpaConfig  {

	@Value("${dataSource.driverClassName}")
    private String driver;
    @Value("${dataSource.url}")
    private String url;    
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;
	
    @Bean
    public DataSource configureDataSource() {    	
    	HikariConfig config = new HikariConfig();
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);
        return new HikariDataSource(config);
    }

	
    @Bean
    public LocalContainerEntityManagerFactoryBean configureEntityManagerFactory() {       
    	
    	LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(configureDataSource());
        entityManagerFactoryBean.setPackagesToScan("makarchuk");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put(org.hibernate.cfg.Environment.DIALECT, dialect);
        jpaProperties.put(org.hibernate.cfg.Environment.HBM2DDL_AUTO, hbm2ddlAuto);        
        jpaProperties.put(org.hibernate.cfg.Environment.SHOW_SQL, true);        
        entityManagerFactoryBean.setJpaProperties(jpaProperties);

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
                
        return entityManagerFactoryBean;
    } 
    
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());        
        filterRegistrationBean.addUrlPatterns("/api/messages/new"); 

        return filterRegistrationBean;
    }    
        

}
