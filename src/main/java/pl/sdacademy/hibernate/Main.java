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
        Song song = readExample(1);
        updateExample(song);
        song = readExample(1);
        deleteExample(song);
        sessionFactory.close();
    }

    private static void deleteExample(Song song) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(song);
        transaction.commit();
        session.close();
    }

    private static void updateExample(Song song) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        song.setArtist("Marta");
        session.update(song);
        transaction.commit();
        session.close();
    }

    private static Song readExample(int id) {
        Session session = sessionFactory.openSession();
        Song song = session.get(Song.class, id);
        System.out.println(song);
        session.close();
        return song;
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
