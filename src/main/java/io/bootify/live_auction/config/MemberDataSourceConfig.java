package io.bootify.live_auction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.bootify.live_auction.domain.member.Member;
import io.bootify.live_auction.repos.member.MemberRepository;
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
        basePackageClasses = MemberRepository.class,
        entityManagerFactoryRef = "memberEntityManager",
        transactionManagerRef = "memberTransactionManager"
)
public class MemberDataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("projects.member.hibernate.property")
    public HashMap<String, Object> memberHibernateProperties() {
        return new HashMap<>();
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean memberEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(memberDataSource());
        em.setPackagesToScan(Member.class.getPackageName());

        em.setPersistenceUnitName("memberEntityManager");

        final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = memberHibernateProperties();
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    @Primary
    public PlatformTransactionManager memberTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(memberEntityManager().getObject());
        return transactionManager;
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "projects.member.datasource.hikari")
    public HikariConfig memberHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    public DataSource memberDataSource() {
        var config = memberHikariConfig();
        return new HikariDataSource(config);
    }
}