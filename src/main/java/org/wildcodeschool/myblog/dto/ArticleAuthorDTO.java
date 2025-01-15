package org.wildcodeschool.myblog.dto;

import org.wildcodeschool.myblog.model.ArticleAuthor;

public record ArticleAuthorDTO(
        String contribution
) {
    public static ArticleAuthorDTO mapFromEntity(ArticleAuthor articleAuthor) {
        return new ArticleAuthorDTO(
                articleAuthor.getContribution()
        );
    }
}
