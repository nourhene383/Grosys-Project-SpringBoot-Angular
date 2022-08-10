package com.example.gestionarticle.Service;

import com.example.gestionarticle.Entity.Article;
import com.example.gestionarticle.Repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService{


    @Autowired
    ArticleRepository articleRepository;



    @Override
    public void addArticle(Article article) {
    articleRepository.save(article);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public List<Article> getAllArticles() {
        return (List<Article>) articleRepository.findAll();
    }

}
