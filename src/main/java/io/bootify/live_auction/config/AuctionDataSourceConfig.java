package io.bootify.live_auction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.bootify.live_auction.domain.auction.Auction;
import io.bootify.live_auction.repos.auction.AuctionRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = AuctionRepository.class,
        entityManagerFactoryRef = "auctionEntityManager",
        transactionManagerRef = "auctionTransactionManager"
)
public class AuctionDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("projects.auction.hibernate.property")
    public HashMap<String, Object> auctionHibernateProperties() {
        return new HashMap<>();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean auctionEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(auctionDataSource());
        em.setPackagesToScan(Auction.class.getPackageName());

        em.setPersistenceUnitName("auctionEntityManager");

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = auctionHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager auctionTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(auctionEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "projects.auction.datasource.hikari")
    public HikariConfig auctionHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public DataSource auctionDataSource() {
        var config = auctionHikariConfig();
        return new HikariDataSource(config);
    }
}