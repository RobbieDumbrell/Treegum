package db;

import models.Advert;
import models.Rating;
import models.User;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static db.DBHelper.getAll;

public class DBUser {

    private static Session session;

    public static List<Advert> getAllAdverts(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try {
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("user", user));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllArchivedAdverts(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try {
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("user", user));
            cr.add(Restrictions.eq("archived", true));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<Advert> getAllActiveAdverts(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<Advert> results = null;
        try {
            Criteria cr = session.createCriteria(Advert.class);
            cr.add(Restrictions.eq("user", user));
            cr.add(Restrictions.eq("archived", false));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static List<User> getAllSearchedActiveUsers(String searchEntry) {
        session = HibernateUtil.getSessionFactory().openSession();
        List<User> results = null;
        try {
            Criteria cr = session.createCriteria(User.class);
            cr.add(Restrictions.ilike("username", searchEntry, MatchMode.ANYWHERE));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

    public static Double calculateAverageRatingForUser(User user) {
        List<Rating> userRatingsReceived = DBRating.getAllRatingsReceivedByUser(user);
        user.setRatingsReceived(userRatingsReceived);
        return user.calculateAverageRatingReceived();
    }

    public static Boolean doesUserNameExist(String username) {
        List<User> allUsers = DBHelper.getAll(User.class);
        for (User user : allUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }


//    public static List<User> getAllSortedByAverageRatingAscending(){
//
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<User> results = null;
//        try {
//            Criteria cr = session.createCriteria(User.class);
//            cr.createAlias("ratingsReceived", "rating");
//
//            results = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return results;
//    }
//
//    public static List<User> getAllSortedByAverageRatingDescending(){
//
//        session = HibernateUtil.getSessionFactory().openSession();
//        List<User> results = null;
//        try {
//            Criteria cr = session.createCriteria(User.class);
//            cr.createAlias("ratingsReceived", "rating");
//            cr.addOrder(Order.desc("rating.value"));
//            results = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return results;
//    }

    public static List<User> getAllSortedByUserNameAtoZ(){

        session = HibernateUtil.getSessionFactory().openSession();
        List<User> results = null;
        try {
            Criteria cr = session.createCriteria(User.class);
            cr.addOrder(Order.asc("username"));
            results = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }



}



