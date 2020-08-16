package com.zaloni.samim.hibernatepractice.repository;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;

import java.util.List;

public interface IAuthorDAO {

    AuthorEntity selectAuthorById(long id);

    List<AuthorEntity> selectAuthorByName(String name, int choice);

    List<AuthorEntity> selectAllAuthor();

    void insertAuthor(AuthorEntity authorEntity);

    void updateAuthor(AuthorEntity authorEntity);

    void deleteAuthorByFirstName(String firstName);

    void deleteAuthorByFirstNameAndLastName(String firstname, String lastname);

    void deleteAuthorByLastName(String lastName);

    void deleteAuthorById(long id);
}
