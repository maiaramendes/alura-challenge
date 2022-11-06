package br.alura.service;

import br.alura.dto.CategoryDTO;
import br.alura.dto.CategoryRequestDTO;
import br.alura.entity.Category;
import br.alura.exception.EntityNotFoundException;
import br.alura.exception.RequiredFieldException;
import br.alura.mapper.CategoryMapperImpl;
import br.alura.mock.CategoryMock;
import br.alura.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryMapperImpl mapper;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private Validator validator;

    private final static String ID = "id";

    private static final Category category = CategoryMock.createCategory();

    private static final List<Category> categoryList = CategoryMock.createCategoryList();

    private CategoryRequestDTO dto = CategoryMock.createDTO();

    private CategoryDTO categoryDTO = CategoryMock.createCategoryDTO();

    @Test
    void findByIdOrThrow_sucess() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));

        //when
        final var result = categoryService.findByIdOrThrow(ID);

        //then
        assertNotNull(result);
    }

    @Test
    void findByIdOrThrow_empty() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> categoryService.findByIdOrThrow(ID));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void getAll_success() {
        //given
        when(categoryRepository.findAll()).thenReturn(categoryList);

        //when
        final var result = categoryService.getAll();

        assertNotNull(result);
        assertEquals(categoryList.size(), result.size());
    }

    @Test
    void getAll_empty() {
        //given
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        final var result = categoryService.getAll();

        assertEquals(0, result.size());
    }

    @Test
    void findById_success() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));
        when(mapper.toDTO(any())).thenCallRealMethod();

        //when
        final var result = categoryService.findById(ID);

        //then
        assertEquals(category.getId(), result.getId());
        assertEquals(category.getColor(), result.getColor());
        assertEquals(category.getTitle(), result.getTitle());
    }

    @Test
    void findById_empty() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> categoryService.findById(ID));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void create_success() {
        //given
        when(mapper.toEntity(dto)).thenCallRealMethod();

        //when
        categoryService.create(dto);

        //then
        verify(categoryRepository, times(1)).save(any());
        verify(mapper, times(1)).toDTO(any());
    }

    @Test
    void edit_success() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.of(category));
        when(mapper.toCategory(dto)).thenCallRealMethod();
        when(mapper.toDTO(any())).thenCallRealMethod();

        //when
        categoryService.edit(dto);

        //then
        verify(categoryRepository, times(1)).save(any());
        verify(mapper, times(1)).toDTO(any());
    }

    @Test
    void edit_empty() {
        //given
        when(categoryRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> categoryService.edit(dto));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void edit_idIsNull() {
        //given
        dto.setId(null);

        //when
        final var exception = assertThrows(RequiredFieldException.class,
                () -> categoryService.edit(dto));

        //then
        assertEquals("Field id is required", exception.getMessage());
    }

    @Test
    void delete_idIsNull() {
        //when
        final var exception = assertThrows(RequiredFieldException.class,
                () -> categoryService.delete(null));

        //then
        assertEquals("Field id is required", exception.getMessage());
    }

    @Test
    void delete_success() {
        //when
        categoryService.delete(ID);

        //then
        verify(categoryRepository, times(1)).deleteById(ID);
    }
}
