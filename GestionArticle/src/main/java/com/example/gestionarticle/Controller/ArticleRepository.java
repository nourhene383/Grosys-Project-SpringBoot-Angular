package com.example.gestionarticle.Controller;

import com.example.gestionarticle.Entity.Article;
import com.example.gestionarticle.Service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleRepository {

    @Autowired
    IArticleService articleService;




    @PostMapping("/addA")
    @ResponseBody
    public void addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
    }

    
}
