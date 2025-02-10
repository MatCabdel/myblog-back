package org.wildcodeschool.myblog.service;

import org.springframework.stereotype.Service;
import org.wildcodeschool.myblog.dto.ImageDTO;
import org.wildcodeschool.myblog.mapper.ImageMapper;
import org.wildcodeschool.myblog.model.Image;
import org.wildcodeschool.myblog.repository.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(ImageRepository imageRepository, ImageMapper imageMapper) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll().stream()
                .map(imageMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public ImageDTO getImageById(Long id) {
        return imageRepository.findById(id)
                .map(imageMapper::convertToDTO)
                .orElse(null);
    }

    public ImageDTO createImage(Image image) {
        return imageMapper.convertToDTO(imageRepository.save(image));
    }

    public ImageDTO updateImage(Long id, Image imageDetails) {
        return imageRepository.findById(id).map(image -> {
            image.setUrl(imageDetails.getUrl());
            return imageMapper.convertToDTO(imageRepository.save(image));
        }).orElse(null);
    }

    public boolean deleteImage(Long id) {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
