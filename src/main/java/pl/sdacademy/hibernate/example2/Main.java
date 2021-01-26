package pl.sdacademy.hibernate.example2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate_example_2.cfg.xml")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Director director = new Director("Adam", "Adamski");
        session.save(director);
        Movie movie = new Movie("Wiatr", 126, director);
        session.save(movie);
        transaction.commit();
        System.out.println(session.get(Movie.class, movie.getId()));

        transaction = session.beginTransaction();
        Actor actor = new Actor("Michał", "Michalski");
        actor.getMovies()
                .add(movie);
        session.save(actor);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }


}
