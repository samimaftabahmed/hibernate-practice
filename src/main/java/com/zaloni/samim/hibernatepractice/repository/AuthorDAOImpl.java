package com.zaloni.samim.hibernatepractice.repository;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AuthorDAOImpl implements IAuthorDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public AuthorEntity selectAuthorById(long id) {

        Session session = em.unwrap(Session.class);
        Query<AuthorEntity> authorEntityQuery =
                session.createQuery("select a from AuthorEntity a where a.id=?1", AuthorEntity.class);
        authorEntityQuery.setParameter(1, id);

        return authorEntityQuery.getSingleResult();
    }

    @Override
    public List<AuthorEntity> selectAuthorByName(String name, int choice) {

        Session session = em.unwrap(Session.class);
        Query<AuthorEntity> query;

        if (choice == 1)
            query = session.createQuery("select a from AuthorEntity a where a.firstname=?1", AuthorEntity.class);
        else
            query = session.createQuery("select a from AuthorEntity a where a.lastname=?1", AuthorEntity.class);

        query.setParameter(1, name);
        return query.getResultList();
    }

    @Override
    public List<AuthorEntity> selectAllAuthor() {

        Session session = em.unwrap(Session.class);
        Query<AuthorEntity> authorEntityQuery =
                session.createQuery("select a from AuthorEntity a", AuthorEntity.class);

        return authorEntityQuery.getResultList();
    }

    @Override
    public void insertAuthor(AuthorEntity authorEntity) {

        this.persist(authorEntity);
    }

    @Override
    public void updateAuthor(AuthorEntity authorEntity) {

        this.persist(authorEntity);
    }

    @Override
    public void deleteAuthorByFirstName(String firstName) {


    }

    @Override
    public void deleteAuthorByLastName(String lastName) {

    }

    @Override
    public void deleteAuthorById(long id) {

//        Session session = em.unwrap(Session.class);
//        AuthorEntity authorEntity = session.get(AuthorEntity.class, id);
//        session.delete(authorEntity);

        AuthorEntity authorEntity = this.selectAuthorById(id);
        em.remove(authorEntity);
        this.flushAndClear();
    }

    /*
       PRIVATE METHODS FROM HERE
    */

    private void persist(AuthorEntity authorEntity) {
        em.persist(authorEntity);
//        em.close();
        System.out.println("Done");
        System.out.println(authorEntity.toString());
    }

    private void flushAndClear() {
        em.flush();
        em.clear();
    }

}
