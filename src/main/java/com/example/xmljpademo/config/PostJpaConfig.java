package com.example.xmljpademo.config;

import com.example.xmljpademo.model.post.Post;
import com.example.xmljpademo.model.post.PostComment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static org.springframework.context.annotation.AdviceMode.ASPECTJ;
import static org.springframework.context.annotation.AdviceMode.PROXY;

//@Configuration
//@EnableTransactionManagement
//public class PostJpaConfig  implements TransactionManagementConfigurer {
//
//    @Override
//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return txManager();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "app.posts.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public PlatformTransactionManager txManager() {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setDataSource(dataSource());
//        return transactionManager;
//    }
//
//    @Bean
//    PostsService postsService() {
//        return new PostsService();
//    }
//
//}

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "postsEntityManager",
        basePackages = {"com.example.xmljpademo.repository.postsrepository"},
        transactionManagerRef = "postsTransactionManager")
@EnableTransactionManagement(mode = PROXY, proxyTargetClass = true)
//@EnableTransactionManagement(mode = ASPECTJ)
public class PostJpaConfig {

    @Primary
    @Bean(name = "postsEntityManager")
    LocalContainerEntityManagerFactoryBean postsEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(postsDataSource())
                .packages(Post.class, PostComment.class)
                .persistenceUnit("posts")
                .build();
    }

    @Primary
    @Bean(name = "postsTransactionManager")
    public JpaTransactionManager transactionManager(@Qualifier("postsEntityManager") EntityManagerFactory serversEntityManager){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(serversEntityManager);

        return transactionManager;
    }

    @Primary
    @Bean(name = "postsDataSource")
    @ConfigurationProperties(prefix = "app.posts.datasource")
    DataSource postsDataSource() {
        return DataSourceBuilder.create().build();
    }
}