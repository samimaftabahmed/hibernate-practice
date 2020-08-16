package com.zaloni.samim.hibernatepractice.service;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;
import com.zaloni.samim.hibernatepractice.repository.IAuthorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private IAuthorDAO iAuthorDAO;

    @Transactional
    @Override
    public void addData(String firstName, String lastName) {

        AuthorEntity authorEntity = new AuthorEntity(firstName, lastName);
        iAuthorDAO.insertAuthor(authorEntity);
    }

    @Transactional
    @Override
    public void addDataManual() {
        AuthorEntity authorEntity1 = new AuthorEntity("Adam", "Gilchrist");
        AuthorEntity authorEntity2 = new AuthorEntity("M.S.", "Dhoni");
        AuthorEntity authorEntity3 = new AuthorEntity("Zaheer", "Khan");

        iAuthorDAO.insertAuthor(authorEntity1);
        iAuthorDAO.insertAuthor(authorEntity2);
        iAuthorDAO.insertAuthor(authorEntity3);
    }

    @Transactional
    @Override
    public void updateFirstName(long id, String data) {

        AuthorEntity authorEntity = iAuthorDAO.selectAuthorById(id);
        authorEntity.setFirstname(data);
        iAuthorDAO.updateAuthor(authorEntity);
    }

    @Transactional
    @Override
    public void updateLastName(long id, String data) {

        AuthorEntity authorEntity = iAuthorDAO.selectAuthorById(id);
        authorEntity.setLastname(data);
        iAuthorDAO.updateAuthor(authorEntity);
    }

    @Transactional
    @Override
    public void deleteAuthorById(long id) {
        iAuthorDAO.deleteAuthorById(id);
    }


    @Override
    public List<AuthorEntity> getAllAuthor() {

        return iAuthorDAO.selectAllAuthor();
    }


}