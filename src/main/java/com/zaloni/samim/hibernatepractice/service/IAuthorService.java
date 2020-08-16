package com.zaloni.samim.hibernatepractice.service;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface IAuthorService {

    void addData(String firstName, String lastName);


    void addDataManual();

    @Transactional
    void updateFirstName(long id, String data);

    @Transactional
    void updateLastName(long id, String data);

    @Transactional
    void deleteAuthorById(long id);

    List<AuthorEntity> getAllAuthor();
}
