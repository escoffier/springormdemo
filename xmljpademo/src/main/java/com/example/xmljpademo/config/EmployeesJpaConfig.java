package com.example.xmljpademo.config;

import com.example.xmljpademo.model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "EmployeesEntityManager",
        basePackages = {"com.example.xmljpademo.repository"},
        transactionManagerRef = "employeesTransactionManager")
public class EmployeesJpaConfig {

    @Bean(name = "EmployeesDataSource")
    @ConfigurationProperties(prefix = "app.employees.datasource")
    DataSource employeesDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "EmployeesEntityManager")
    LocalContainerEntityManagerFactoryBean employeesEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(employeesDataSource())
                .packages(Employee.class, Salary.class, Title.class, EmployeeDetail.class, Phone.class)
                .persistenceUnit("employee")
                .build();
    }

    @Bean(name = "employeesTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("EmployeesEntityManager") EntityManagerFactory serversEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(serversEntityManager);

        return transactionManager;
    }
}
