package br.alura.service;

import br.alura.dto.CategoryDTO;
import br.alura.dto.CategoryRequestDTO;
import br.alura.entity.Category;
import br.alura.exception.EntityNotFoundException;
import br.alura.mapper.CategoryMapper;
import br.alura.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

import static br.alura.utils.ValidateBlank.validateIfIdIsBlank;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final Validator validator;

    private final CategoryMapper mapper;

    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle(final String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category findByIdOrThrow(final String id) {
        return categoryRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public CategoryDTO findById(final String id) {
        log.info("Finding category with id #{}.", id);
        final var category = findByIdOrThrow(id);

        log.info("Category with id #{} found.", id);
        return mapper.toDTO(category);
    }

    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll().stream().map(mapper::toDTO).toList();
    }

    public CategoryDTO create(final CategoryRequestDTO dto) {
        log.info("Validating if any field is empty.");
        final var violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            log.error("Fields title and color are requireds.");
            throw new ConstraintViolationException(violations);
        }

        final var category = categoryRepository.save(mapper.toEntity(dto));

        log.info("Category was successfully saved!");
        return mapper.toDTO(category);
    }

    public CategoryDTO edit(final CategoryRequestDTO dto) {
        validateIfIdIsBlank(dto.getId());

        final var category = findByIdOrThrow(dto.getId());
        final var merged = categoryRepository.save(mapper.merge(mapper.toCategory(dto), category));

        log.info("Category with id #{} was successfully saved!", dto.getId());
        return mapper.toDTO(merged);
    }

    public void delete(final String id) {
        validateIfIdIsBlank(id);

        categoryRepository.deleteById(id);
        log.info("Category with id #{} was sucessfully removed.", id);
    }

}
