package de.hska.articlemanagement.business;

import de.hska.articlemanagement.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by andre on 26.10.2016.
 */
public interface IArticleRepository extends JpaRepository<Article, Long> {
}
