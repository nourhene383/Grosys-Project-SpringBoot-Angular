package com.example.gestionarticle.Service;

import com.example.gestionarticle.Entity.Article;

import java.util.List;

public interface IArticleService {

    public void addArticle(Article article);
    public void deleteArticle(Article article);
    public void updateArticle(Article article);

    public List<Article> getAllArticles();
}
