package io.bootify.live_auction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.bootify.live_auction.domain.receipt.Receipt;
import io.bootify.live_auction.repos.receipt.ReceiptRepository;
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
        basePackageClasses = ReceiptRepository.class,
        entityManagerFactoryRef = "receiptEntityManager",
        transactionManagerRef = "receiptTransactionManager"
)
public class ReceiptDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("projects.receipt.hibernate.property")
    public HashMap<String, Object> receiptHibernateProperties() {
        return new HashMap<>();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean receiptEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(receiptDataSource());
        em.setPackagesToScan(Receipt.class.getPackageName());

        em.setPersistenceUnitName("receiptEntityManager");

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = receiptHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager receiptTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(receiptEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "projects.receipt.datasource.hikari")
    public HikariConfig receiptHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public DataSource receiptDataSource() {
        var config = receiptHikariConfig();
        return new HikariDataSource(config);
    }
}