package job4j_cars.job4j_cars;

import job4j_cars.job4j_cars.model.Advertisement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDateTime;
import java.util.List;

public class AdRepository {


    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            findAddWithDefiniteMarkCar(session, "BMW");
            findAddWithPhoto(session);
            findAdForLastDay(session);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static List<AdRepository> findAdForLastDay(Session session) {
        LocalDateTime minusDay = LocalDateTime.now().minusDays(1);
        LocalDateTime now = LocalDateTime.now();
        return session
        .createQuery("select distinct ad FROM Advertisement ad join fetch ad.created "
                + "where ad.created between :adDateBegin and :adDateEnd")
                .setParameter("adDateBegin", minusDay)
                .setParameter("adDateEnd", now)
                .list();
    }

    public static List<Advertisement> findAddWithPhoto(Session session) {
        return session.createQuery("select distinct ad from Advertisement ad join fetch ad.photo where ad.photo.size > 0").list();
    }

    public static List<Advertisement> findAddWithDefiniteMarkCar(Session session, String nameMark) {
        return session.createQuery("select distinct c from Car c join fetch c.mark where c.mark.name = :cMark")
                .setParameter("cMark", nameMark)
                .list();
    }
}
