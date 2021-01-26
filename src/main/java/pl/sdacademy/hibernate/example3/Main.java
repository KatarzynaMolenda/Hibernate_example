package pl.sdacademy.hibernate.example3;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate_example_3.cfg.xml")
                .buildSessionFactory();
        prepareData(sessionFactory);
        Session session = sessionFactory.openSession();
        Movie movie = session.get(Movie.class, 1);
        Hibernate.initialize(movie.getDirector());
        session.close();

        System.out.println(movie.getDirector());

        session = sessionFactory.openSession();
        Actor actor = session.get(Actor.class, 1);
        Hibernate.initialize(actor.getMovies());
        session.close();

        System.out.println(actor.getMovies());

        sessionFactory.close();

    }

    private static void prepareData(SessionFactory sessionFactory) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Director director1 = new Director("Reżyser 1", "Reżyser 1");
        session.persist(director1);
        Director director2 = new Director("Reżyser 2", "Reżyser 2");
        session.persist(director2);

        Movie movie1 = new Movie("Film 1", 111, director1);
        session.persist(movie1);
        Movie movie2 = new Movie("Film 2", 222, director1);
        session.persist(movie2);
        Movie movie3 = new Movie("Film 3", 333, director2);
        session.persist(movie3);

        Actor actor1 = new Actor("Aktor 1", "Aktor 1");
        actor1.getMovies().add(movie1);
        actor1.getMovies().add(movie2);
        session.persist(actor1);

        Actor actor2 = new Actor("Aktor 2", "Aktor 2");
        actor2.getMovies().add(movie2);
        actor2.getMovies().add(movie3);
        session.persist(actor2);

        transaction.commit();
        session.close();
    }
}
