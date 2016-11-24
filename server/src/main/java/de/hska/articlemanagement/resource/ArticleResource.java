package de.hska.articlemanagement.resource;

import de.hska.articlemanagement.business.IArticleRepository;
import de.hska.articlemanagement.domain.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController(value = "Article Resource")
@RequestMapping(value = "/api/rest/articles", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ArticleResource {

    @Autowired
    private IArticleRepository articleRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public Article save(@RequestBody @Valid final Article article) {
        return articleRepository.save(article);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Article getById(@PathVariable final Long id) {
        return articleRepository.findOne(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, value = "")
    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable final Long id) {
        articleRepository.delete(id);
    }
}
