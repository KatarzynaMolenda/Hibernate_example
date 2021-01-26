package pl.sdacademy.hibernate.example2;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    private static SessionFactory sessionFactory;
    public static void main(String[] args) {
        sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();



    }


}
