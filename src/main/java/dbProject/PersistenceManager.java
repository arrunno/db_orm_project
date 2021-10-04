package dbProject;

import org.hibernate.cfg.Environment;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public enum PersistenceManager {
    instance;

    private EntityManagerFactory emfactory;

    private PersistenceManager() {

        Properties properties = createProperties();
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
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL94Dialect");
        properties.put(Environment.DRIVER, "org.postgresql.Driver");
        properties.put(Environment.URL, "jdbc:postgresql://localhost/assignment2_db");
        properties.put(Environment.USER, "postgres");
        properties.put(Environment.PASS, "arunas");
        properties.put(Environment.SHOW_SQL, "false");
//        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.HBM2DDL_AUTO, "create-drop");

        return properties;
    }
}