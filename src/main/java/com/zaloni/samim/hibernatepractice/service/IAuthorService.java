package com.zaloni.samim.hibernatepractice.service;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;

import java.util.List;

public interface IAuthorService {

    void addData(String firstName, String lastName);

    void addDataManual();

    void updateFirstName(long id, String data);

    void updateLastName(long id, String data);

    void deleteAuthorById(long id);

    void deleteBulkByFirstName(String name);

    void deleteBulkByName(String firstName, String lastName);

    void deleteAuthorByNative(long id);

    List<AuthorEntity> getAllAuthor();

    List<AuthorEntity> getAuthorByFirstNameWithLike(String like);
}
