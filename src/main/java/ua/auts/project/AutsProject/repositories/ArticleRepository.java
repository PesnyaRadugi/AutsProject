package ua.auts.project.AutsProject.repositories;

import org.springframework.data.repository.CrudRepository;

import ua.auts.project.AutsProject.entities.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
