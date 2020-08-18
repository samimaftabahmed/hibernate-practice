package com.zaloni.samim.hibernatepractice.controller;

import com.zaloni.samim.hibernatepractice.model.AuthorEntity;
import com.zaloni.samim.hibernatepractice.service.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MyController {

    @Autowired
    private IAuthorService iAuthorService;

    @PostMapping("/add-data")
    public String addData(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName
    ) {

        iAuthorService.addData(firstName, lastName);
        return "data-entry-form";
    }

    @ResponseBody
    @GetMapping("add-data-manual")
    public String addDataManual() {

        iAuthorService.addDataManual();
        return "OK";
    }

    @GetMapping("/")
    public String home() {

        return "data-entry-form";
    }

    @ResponseBody
    @GetMapping("/update-name")
    public String updateName(
            @RequestParam("id") String id,
            @RequestParam("query") String query,
            @RequestParam("data") String data
    ) {

        if (query.equals("1"))
            iAuthorService.updateFirstName(Long.parseLong(id), data);
        else
            iAuthorService.updateLastName(Long.parseLong(id), data);

        return "OK";
    }

    @ResponseBody
    @GetMapping("/get-all")
    public List<AuthorEntity> viewAll() {

        return iAuthorService.getAllAuthor();
    }

    @ResponseBody
    @GetMapping("/delete")
    public String deleteAuthorById(@RequestParam String id) {

        iAuthorService.deleteAuthorById(Long.parseLong(id));
        return "OK";
    }

    @ResponseBody
    @GetMapping("delete-bulk-first-name")
    public String deleteBulkFirstName(@RequestParam("first-name") String firstName) {

        iAuthorService.deleteBulkByFirstName(firstName);
        return "OK";
    }

    @ResponseBody
    @GetMapping("delete-bulk-by-name")
    public String deleteBulkByName(
            @RequestParam("first-name") String firstName,
            @RequestParam("last-name") String lastName
    ) {

        iAuthorService.deleteBulkByName(firstName, lastName);
        return "OK";
    }

    @ResponseBody
    @GetMapping("/delete-by-id-native")
    public String deleteByIdNative(@RequestParam String id) {

        iAuthorService.deleteAuthorByNative(Long.parseLong(id));
        return "OK";
    }

    @ResponseBody
    @GetMapping("/get-author-like")
    public List<AuthorEntity> getAuthorByFirstNameLike(@RequestParam String like){
        return iAuthorService.getAuthorByFirstNameWithLike(like);
    }
}
