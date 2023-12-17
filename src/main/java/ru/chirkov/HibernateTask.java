package ru.chirkov;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.sql.internal.SQLQueryParser;

import javax.management.Query;
import java.sql.ResultSet;
import java.util.List;

public class HibernateTask {


    public static void toConnection() {
        try (final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
             Session session = sf.openSession()) {
            session.beginTransaction();
            session.persist(new Book("Герой нашего времени", "Лермонтов М.Ю."));
            session.persist(new Book("Мастер и Маргарита", "Булгаков М.А."));
            session.persist(new Book("Горе от ума", "Грибоедов А.С."));
            session.persist(new Book("Идиот", "Достоевский Ф."));
            session.persist(new Book("Мцыри", "Лермонтов М.Ю."));
            session.persist(new Book("Демон", "Лермонтов М.Ю."));
            session.persist(new Book("Преступление и наказание", "Достоевский Ф."));
            session.persist(new Book("Атеизм", "Достоевский Ф."));
            session.persist(new Book("Евгений Онегин", "Пушкин А.С."));
            session.persist(new Book("Моцарт и Сальери", "Пушкин А.С."));
            session.getTransaction().commit();
            List<Book> res = session.createSelectionQuery("From Book WHERE author='Пушкин А.С.'", Book.class).getResultList();

            res.forEach(System.out::println);
        }
    }
}
