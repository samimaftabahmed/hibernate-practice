package com.zaloni.samim.hibernatepractice.service;

import com.zaloni.samim.hibernatepractice.model.Author;
import com.zaloni.samim.hibernatepractice.repository.IMyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MyDataServiceImpl implements IMyDataService {

    @Autowired
    private IMyDAO iMyDAO;

    @Transactional
    @Override
    public void addData() {

        Author author = new Author("Killer", "Drummer");
//        Author author2 = new Author("M.S.", "Dhoni");
//        Author author3 = new Author("Zaheer", "Khan");

        iMyDAO.insertAuthor(author);
//        iMyDAO.insertAuthor(author2);
//        iMyDAO.insertAuthor(author3);
    }
}
