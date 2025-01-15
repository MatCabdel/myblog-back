package org.wildcodeschool.myblog.dto;

import org.wildcodeschool.myblog.model.Author;

public record AuthorDTO(long id, String firstName,
                        String lastName) {
    public static AuthorDTO mapFromEntity(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
        );
    }
}
