package org.wildcodeschool.myblog.repository;

import org.springframework.data.repository.CrudRepository;
import org.wildcodeschool.myblog.model.Image;

public interface ImageRepositoy extends CrudRepository<Image, Long> {
}
