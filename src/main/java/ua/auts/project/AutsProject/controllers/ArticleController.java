package ua.auts.project.AutsProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.auts.project.AutsProject.entities.Article;
import ua.auts.project.AutsProject.repo.ArticleRepository;


@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public String articlesHome(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles-home";
    }

    @GetMapping("/add")
    public String articlesAdd(Model model) {
        model.addAttribute("article", new Article());
        return "articles-add";
    }

    @PostMapping("/add")
    public String articlesAddArticle(@NonNull @ModelAttribute("article") Article article) {
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String articlesShowDetails(@NonNull @PathVariable(value = "id") Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);

        if (article == null) {
            return "redirect:/articles";
        }

        model.addAttribute("article", article);
        return "articles-details";
    }
}
