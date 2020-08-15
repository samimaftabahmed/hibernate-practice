package com.zaloni.samim.hibernatepractice.repository;

import com.zaloni.samim.hibernatepractice.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Repository
public class MyDAOImpl implements IMyDAO {

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void insertAuthor(Author author) {

        EntityManager em = emf.createEntityManager();
        em.persist(author);

        System.out.println("Done " + author.getId());

        em.close();
    }
}
