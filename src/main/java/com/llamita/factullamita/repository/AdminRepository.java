package com.llamita.factullamita.repository;

import com.llamita.factullamita.model.Administrator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by fabiosalasm on 16/03/14.
 */
@Repository
public class AdminRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Administrator getAdminByUser(String user) {

        Session session = sessionFactory.getCurrentSession();

        Query query = session.createQuery("from Administrator a where a.user = :user");
        query.setParameter("user", user);
        Administrator admin = (Administrator) query.uniqueResult();

        return admin;
    }
}
