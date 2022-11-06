package br.alura.service;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Video;
import br.alura.exception.EntityNotFoundException;
import br.alura.mapper.VideoMapper;
import br.alura.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;

import static br.alura.utils.ValidateBlank.validateIfIdIsBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoMapper mapper;

    private final Validator validator;

    private final VideoRepository videoRepository;

    private final CategoryService categoryService;

    public Video findByIdOrThrow(final String id) {
        return videoRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<VideoDTO> getAll() {
        log.info("Retrieving all videos saved.");
        final var videoList = videoRepository.findAll();

        log.info("All vides were successfully retrieved, returning request.");
        return videoList.stream().map(mapper::toDTO).toList();
    }

    public VideoDTO findById(final String id) {
        log.info("Finding video with id #{}.", id);
        final var video = findByIdOrThrow(id);

        log.info("Video with id #{} found.", id);
        return mapper.toDTO(video);
    }

    public VideoDTO createVideo(final VideoRequestDTO dto) {
        log.info("Validating if any field is empty.");
        final var violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            log.error("Fields title, description and url must not be null.");
            throw new ConstraintViolationException(violations);
        }

        categoryService.findByIdOrThrow(dto.getCategory());
        final var video = videoRepository.save(mapper.toVideo(dto));

        log.info("Video was successfully saved!");
        return mapper.toDTO(video);
    }

    public VideoDTO editVideo(final VideoDTO dto) {
        validateIfIdIsBlank(dto.getId());
        if (null != dto.getCategory()) {
            categoryService.findByIdOrThrow(dto.getCategory());
        }

        final var video = findByIdOrThrow(dto.getId());
        final var merged = videoRepository.save(mapper.merge(mapper.toVideo(dto), video));

        log.info("Video with id #{} was successfully saved!", dto.getId());
        return mapper.toDTO(merged);
    }

    public void deleteVideo(final String id) {
        validateIfIdIsBlank(id);

        videoRepository.deleteById(id);
        log.info("Video with id #{} was sucessfully removed.", id);
    }

    public List<VideoDTO> findAllByCategory(final String search) {
        final var category = categoryService.findByTitle(search);

        if (category.isPresent()) {
            return videoRepository.findByCategory_Id(category.get().getId())
                    .stream()
                    .map(mapper::toDTO)
                    .toList();
        }

        return new ArrayList<>();
    }

    public void deleteAll() {
        videoRepository.deleteAll();
    }

}
