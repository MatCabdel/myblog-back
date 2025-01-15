package org.wildcodeschool.myblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wildcodeschool.myblog.dto.AuthorDTO;
import org.wildcodeschool.myblog.model.Author;
import org.wildcodeschool.myblog.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AuthorDTO> authorDTOs = authors.stream()
                .map(AuthorDTO::mapFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AuthorDTO.mapFromEntity(author));
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        if (author.getFirstname() == null || author.getFirstname().isEmpty() ||
                author.getLastname() == null || author.getLastname().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Author savedAuthor = authorRepository.save(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(AuthorDTO.mapFromEntity(savedAuthor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        author.setFirstname(authorDetails.getFirstname());
        author.setLastname(authorDetails.getLastname());
        Author updatedAuthor = authorRepository.save(author);

        return ResponseEntity.ok(AuthorDTO.mapFromEntity(updatedAuthor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        Author author = authorRepository.findById(id).orElse(null);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }

        authorRepository.delete(author);
        return ResponseEntity.noContent().build();
    }
}
