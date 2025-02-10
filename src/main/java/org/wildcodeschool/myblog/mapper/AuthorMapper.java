package org.wildcodeschool.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.model.Author;

@Component
public class AuthorMapper {
    public AuthorDTO convertToDTO(Author author) {
        return new AuthorDTO(
                author.getId(),
                author.getFirstname(),
                author.getLastname()
        );
    }
}
