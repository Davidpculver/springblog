package com.codeup.springblog.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findById(long id);

    void deleteById(long id);

//    Post editById(@PathVariable long id, @RequestParam(name = "title") String title, @RequestParam(name = "body") String body);
}
