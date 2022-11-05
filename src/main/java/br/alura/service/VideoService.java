package br.alura.service;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Video;
import br.alura.exception.RequiredFieldException;
import br.alura.exception.VideoNotFoundException;
import br.alura.mapper.VideoMapper;
import br.alura.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoMapper mapper;

    private final VideoRepository videoRepository;

    private final Validator validator;

    private Video findByIdOrThrow(final String id) {
        return videoRepository
                .findById(id)
                .orElseThrow(VideoNotFoundException::new);
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

        final var video = videoRepository.save(mapper.toVideo(dto));

        log.info("Video with id #{} was successfully saved!", video.getId());
        return mapper.toDTO(video);
    }

    public VideoDTO editVideo(final VideoDTO dto) {
        validateIfIdIsNull(dto.getId());

        final var video = findByIdOrThrow(dto.getId());
        final var merged = videoRepository.save(mapper.merge(mapper.toVideo(dto), video));

        log.info("Video with id #{} was successfully saved!", dto.getId());
        return mapper.toDTO(merged);
    }

    public void deleteVideo(final String id) {
        validateIfIdIsNull(id);

        videoRepository.deleteById(id);
        log.info("Video with id #{} was sucessfully removed.", id);
    }

    private void validateIfIdIsNull(final String id) {
        log.info("Validating if id field is empty.");

        if (Strings.isEmpty(id)) {
            log.error("Field Id must not be null.");
            throw new RequiredFieldException("Id must not be null");
        }
    }
}
