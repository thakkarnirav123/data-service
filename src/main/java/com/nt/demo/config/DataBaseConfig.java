package com.nt.demo.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Enables JPA repository by scanning the declared package
@EnableJpaRepositories(basePackages = "com.nt.demo.repositories")

// Enables transaction management by using annotation.
@EnableTransactionManagement

// Enables support for ConfigurationProperties annotated beans.
@EnableConfigurationProperties

// Annotating a class with the @Configuration indicates that the class can be
// used by the Spring IoC container as a source of bean definitions.
@Configuration
public class DataBaseConfig
{
    /**
     * Gets the entity manager factory.
     *
     * @param dataSource
     * @return
     */

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(DataSource dataSource)
    {

        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);
        
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.nt.demo.entity");
        localContainerEntityManagerFactoryBean.setDataSource(dataSource);
        localContainerEntityManagerFactoryBean.afterPropertiesSet();



        return localContainerEntityManagerFactoryBean.getObject();
    }

    /**
     * Gets the transaction manager
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    @DependsOn({ "entityManagerFactory" })
    public PlatformTransactionManager transactionManager(DataSource dataSource)
    {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory(dataSource));
        return jpaTransactionManager;
    }
}
