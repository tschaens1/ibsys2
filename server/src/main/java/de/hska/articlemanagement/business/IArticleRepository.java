package de.hska.articlemanagement.business;

import de.hska.articlemanagement.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IArticleRepository extends JpaRepository<Article, Long> {
}
