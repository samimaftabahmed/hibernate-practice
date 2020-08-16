package com.zaloni.samim.hibernatepractice.repository;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AuthorDAOImpl implements IAuthorDAO {

    @PersistenceContext
    private EntityManager em;

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager emBatch;

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

        flushAndClearBatch();
        emBatch.unwrap(Session.class).setJdbcBatchSize(10);
        CriteriaBuilder builder = emBatch.getCriteriaBuilder();
        CriteriaDelete<AuthorEntity> criteriaDelete = builder.createCriteriaDelete(AuthorEntity.class);
        Root<AuthorEntity> root = criteriaDelete.from(AuthorEntity.class);

        criteriaDelete.where(builder.equal(root.get("firstname"), firstName));

        try {
            int deleteCount = emBatch.createQuery(criteriaDelete).executeUpdate();
            flushAndClearBatch();
            System.out.println("count " + deleteCount);

        } finally {
            emBatch.unwrap(Session.class).setJdbcBatchSize(null);
        }
    }

    @Override
    public void deleteAuthorByFirstNameAndLastName(String firstname, String lastname) {

        flushAndClearBatch();
        emBatch.unwrap(Session.class).setJdbcBatchSize(10);
        CriteriaBuilder builder = emBatch.getCriteriaBuilder();
        CriteriaDelete<AuthorEntity> criteriaDelete = builder.createCriteriaDelete(AuthorEntity.class);
        Root<AuthorEntity> root = criteriaDelete.from(AuthorEntity.class);

        criteriaDelete.where(
                builder.and(
                        builder.equal(root.get("firstname"), firstname),
                        builder.equal(root.get("lastname"), lastname)
                ));

        try {
            int count = emBatch.createQuery(criteriaDelete).executeUpdate();
            flushAndClearBatch();
            System.out.println("count " + count);

        } finally {
            emBatch.unwrap(Session.class).setJdbcBatchSize(null);
        }
    }

    @Override
    public void deleteAuthorByLastName(String lastName) {

    }

    @Override
    public void deleteAuthorById(long id) {

        this.flushAndClear();
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

    private void flushAndClearBatch() {
        emBatch.flush();
        emBatch.clear();
    }

}
