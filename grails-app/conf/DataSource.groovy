dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "igdefault"
    dialect = 'org.hibernate.dialect.MySQL5InnoDBDialect'
    configClass = HibernateFilterDomainConfiguration.class
    properties {
        maxActive = -1
        minEvictableIdleTimeMillis=1800000
        timeBetweenEvictionRunsMillis=1800000
        numTestsPerEvictionRun=3
        testOnBorrow=true
        testWhileIdle=true
        testOnReturn=true
        validationQuery="SELECT 1"
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            //url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            url="jdbc:mysql://localhost:3306/ninja?autoReconnect=true&zeroDateTimeBehavior=convertToNull"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            String databaseUrl = System.getenv("CLEARDB_DATABASE_URL")
            if (databaseUrl) {
                println "Applying MySql settings"
                println "Url : " + databaseUrl
                URI dbUri = new URI(databaseUrl);
                username = dbUri.userInfo.split(":")[0]
                password = dbUri.userInfo.split(":")[1]
                url = "jdbc:mysql://" + dbUri.host + dbUri.path
                driverClassName = "com.mysql.jdbc.Driver"
            }
            /*url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }*/
        }
    }
}
