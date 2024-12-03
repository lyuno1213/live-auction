package io.bootify.live_auction.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.live_auction.domain")
@EnableJpaRepositories("io.bootify.live_auction.repos")
@EnableTransactionManagement
public class DomainConfig {
}
