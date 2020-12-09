package dominik.iss.trajectory.project.database;

import dominik.iss.trajectory.project.httpclient.IssCurrentLocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class IssDaoHibernateImpl implements IssDao {

    private static final SessionFactory sessionFactory;

    static {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();
        final Metadata metadata = new MetadataSources(registry).buildMetadata();

        sessionFactory = metadata.buildSessionFactory();
    }

    public void save(IssCurrentLocationEntity issCurrentLocationEntity){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(issCurrentLocationEntity);
            transaction.commit();

        }catch (RuntimeException e){
            if (transaction != null && transaction.isActive()){
                transaction.rollback();
            }
        }
    }

}
