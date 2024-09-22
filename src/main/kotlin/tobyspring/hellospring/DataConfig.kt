package tobyspring.hellospring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class DataConfig {

    @Bean
    fun dataSource(): DataSource {
        return EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource()
        emf.setPackagesToScan("tobyspring.hellospring")
        emf.jpaVendorAdapter = HibernateJpaVendorAdapter().apply {
            setDatabase(Database.H2)
            setGenerateDdl(true)
            setShowSql(true)
        }

        return emf
    }

//    @Bean
//    fun persistenceAnnotationBeanPostProcessor(): BeanPostProcessor {
//        return PersistenceAnnotationBeanPostProcessor()
//    }

//    @Bean
//    fun transactionManager(emf: EntityManagerFactory): PlatformTransactionManager {
//        return JpaTransactionManager(emf)
//    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }
}