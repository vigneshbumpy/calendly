package com.calendly.db

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

/**
 * Jpa config
 *
 * @constructor Create empty Jpa config
 */
@Configuration
@EnableTransactionManagement
class JpaConfig {

    /**
     * Data source
     *
     * @return
     */
    @Bean
    fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName("org.sqlite.JDBC")
        dataSource.url = "jdbc:sqlite:calendly.db"
        return dataSource
    }

    /**
     * Entity manager factory
     *
     * @return
     */
    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.calendly.model")

        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setDatabasePlatform(SQLiteDialect::class.java.name)
        vendorAdapter.setShowSql(true)
        em.jpaVendorAdapter = vendorAdapter

        val properties = HashMap<String, Any>()
        properties["hibernate.hbm2ddl.auto"] = "update"
        properties["hibernate.dialect"] = SQLiteDialect::class.java.name
        em.setJpaPropertyMap(properties)

        return em
    }

    /**
     * Transaction manager
     *
     * @return
     */
    @Bean
    fun transactionManager(): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory().`object`
        return transactionManager
    }
}