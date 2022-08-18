package job4j_cars.job4j_cars;

import job4j_cars.job4j_cars.model.Advertisement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AdRepository {


    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();

        LocalDateTime beginDate = LocalDateTime.of(2022, 8, 15, 15, 05, 30);
        LocalDateTime endDate = LocalDateTime.of(2022, 8, 18, 15, 05, 30);
        try {
            SessionFactory sf = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            findAddWithDefiniteMarkCar(session, "BMW");
            findAddWithPhoto(session);
            findAdForLastDay(session, beginDate, endDate);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static List<AdRepository> findAdForLastDay(Session session, LocalDateTime beginDate, LocalDateTime endDate) {
        Timestamp bDateTime = Timestamp.valueOf(beginDate);
        Timestamp eDateTime = Timestamp.valueOf(endDate);
        return session
        .createQuery("select distinct ad FROM Advertisement ad join fetch ad.created "
                + "where ad.created between :adDateBegin and :adDateEnd")
                .setParameter("adDateBegin", bDateTime)
                .setParameter("adDateEnd", eDateTime)
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
