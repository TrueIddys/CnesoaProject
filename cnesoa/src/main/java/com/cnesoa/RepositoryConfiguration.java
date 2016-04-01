package com.cnesoa;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by Maxime on 30/03/2016.
 */

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.cnesoa.domain"})
@EnableJpaRepositories(basePackages =  {"com.cnesoa.repository"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}
