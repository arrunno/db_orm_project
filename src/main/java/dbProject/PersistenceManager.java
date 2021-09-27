package dbProject;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public enum PersistenceManager {
    instance;

    private EntityManagerFactory emfactory;

    private PersistenceManager() {
        // "jpa-example" was the value of the name attribute of the
        // persistence-unit element.

//        Configuration configuration = new Configuration();
        Properties properties = createProperties();
//        configuration.setProperties(properties);

        emfactory = Persistence.createEntityManagerFactory("jpa-project", properties);
    }

    public EntityManager getentitymanager() {
        return emfactory.createEntityManager();
    }

    public void close() {
        emfactory.close();
    }

    private Properties createProperties(){
        Properties properties = new Properties();
//        properties.put(Environment.);
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost/assignment2_db");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "arunas");
        properties.put(Environment.SHOW_SQL, "true");
//        properties.put(Environment.HBM2DDL_AUTO, "update");

        properties.put(Environment.HBM2DDL_AUTO, "create-drop");

        return properties;
    }
}