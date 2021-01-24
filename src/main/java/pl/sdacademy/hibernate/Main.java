package pl.sdacademy.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        int id = createExample();
        sessionFactory.close();
    }

    private static int createExample() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Song song = new Song("Anna", "Wiatr");
        session.save(song);
        transaction.commit();
        session.close();
        return song.getId();
    }
}
