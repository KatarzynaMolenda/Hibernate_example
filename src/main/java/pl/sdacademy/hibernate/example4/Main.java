package pl.sdacademy.hibernate.example4;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate_example_4.cfg.xml")
                .buildSessionFactory();
        prepareData(sessionFactory);
        Session session = sessionFactory.openSession();

        System.out.println("\n\n----- Przykład 1 -------");
        System.out.println("SELECT m FROM Movie m WHERE m.id = 2");

        Query<Movie> query1 = session.createQuery("SELECT m FROM Movie m WHERE m.id = 2", Movie.class);
        Movie result1 = query1.getSingleResult();
        System.out.println("\nWynik zapytania:\n" + result1);

        System.out.println("\n\n----- Przykład 2 -------");
        System.out.println("SELECT m FROM Movie m WHERE m.length > 2000");
        Query<Movie> query2 = session.createQuery("SELECT m FROM Movie m WHERE m.length > 2000", Movie.class);
        List<Movie> result2 = query2.getResultList();
        System.out.println("\nWynik zapytania:\n" + result2);

        System.out.println("\n\n----- Przykład 3 -------");
        System.out.println("SELECT m.length FROM Movie m WHERE m.length > 2000");
        Query<Integer> query3 = session.createQuery("SELECT m.length FROM Movie m WHERE m.length > 2000",
                Integer.class);
        List<Integer> result3 = query3.getResultList();
        System.out.println("\nWynik zapytania:\n" + result3);

        System.out.println("\n\n----- Przykład 4 -------");
        System.out.println("SELECT m FROM Movie m WHERE m.length > ?1");
        Query<Movie> query4 = session.createQuery("SELECT m FROM Movie m WHERE m.length > ?1", Movie.class);
        query4.setParameter(1, 2000);
        List<Movie> result4 = query4.getResultList();
        System.out.println("\nWynik zapytania:\n" + result4);

        System.out.println("\n\n----- Przykład 5 -------");
        System.out.println("SELECT m FROM Movie m WHERE m.length > :minLength");
        Query<Movie> query5 = session.createQuery("SELECT m FROM Movie m WHERE m.length > :minLength",
                Movie.class);
        query5.setParameter("minLength", 2000);
        List<Movie> result5 = query5.getResultList();
        System.out.println("\nWynik zapytania:\n" + result5);

        System.out.println("\n\n----- Przykład 6 -------");
        System.out.println("SELECT m FROM Movie m WHERE m.director.firstName = 'Reżyser 1'");
        Query<Movie> query6 = session.createQuery("SELECT m FROM Movie m WHERE m.director.firstName = 'Reżyser 1'",
                Movie.class);
        List<Movie> result6 = query6.getResultList();
        System.out.println("\nWynik zapytania:\n" + result6);

        System.out.println("\n\n----- Przykład 7 -------");
        System.out.println("SELECT m FROM Movie m INNER JOIN m.director");
        Query<Movie> query7 = session.createQuery("SELECT m FROM Movie m INNER JOIN m.director",
                Movie.class);
        List<Movie> result7 = query7.getResultList();
        System.out.println("\nWynik zapytania:\n" + result7);

        System.out.println("\n\n----- Przykład 8 -------");
        System.out.println("SELECT m FROM Movie m INNER JOIN FETCH m.director");
        Query<Movie> query8 = session.createQuery("SELECT m FROM Movie m INNER JOIN FETCH m.director",
                Movie.class);
        List<Movie> result8 = query8.getResultList();
        System.out.println("\nWynik zapytania:\n" + result8);


        session.close();
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
