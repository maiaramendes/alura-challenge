package br.alura.service;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Category;
import br.alura.entity.Video;
import br.alura.exception.EntityNotFoundException;
import br.alura.exception.RequiredFieldException;
import br.alura.mapper.VideoMapperImpl;
import br.alura.mock.CategoryMock;
import br.alura.mock.VideoMock;
import br.alura.repository.VideoRepository;
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
class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoMapperImpl mapper;

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private Validator validator;

    private final static String ID = "id";

    private static final Video video = VideoMock.createVideo();

    private static final List<Video> videoList = VideoMock.createVideoList();

    private static final VideoRequestDTO dto = VideoMock.createDTO();

    private static final VideoDTO videoDTO = VideoMock.createVideoDTO();

    private static final Category category = CategoryMock.createCategory();

    @Test
    void findByIdOrThrow_sucess() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.of(video));

        //when
        final var result = videoService.findByIdOrThrow(ID);

        //then
        assertNotNull(result);
    }

    @Test
    void findByIdOrThrow_empty() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> videoService.findByIdOrThrow(ID));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void getAll_success() {
        //given
        when(videoRepository.findAll()).thenReturn(videoList);

        //when
        final var result = videoService.getAll();

        assertNotNull(result);
        assertEquals(videoList.size(), result.size());
    }

    @Test
    void getAll_empty() {
        //given
        when(videoRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        final var result = videoService.getAll();

        assertEquals(0, result.size());
    }

    @Test
    void findById_success() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.of(video));
        when(mapper.toDTO(any())).thenCallRealMethod();

        //when
        final var result = videoService.findById(ID);

        //then
        assertEquals(video.getId(), result.getId());
        assertEquals(video.getDescription(), result.getDescription());
        assertEquals(video.getUrl(), result.getUrl());
        assertEquals(video.getTitle(), result.getTitle());
    }

    @Test
    void findById_empty() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> videoService.findById(ID));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void createVideo_success() {
        //given
        when(mapper.toVideo(dto)).thenCallRealMethod();

        //when
        videoService.createVideo(dto);

        //then
        verify(videoRepository, times(1)).save(any());
        verify(mapper, times(1)).toDTO(any());
    }

    @Test
    void createVideo_whenCategoryNotExists_throw() {
        //given
        when(categoryService.findByIdOrThrow(any())).thenThrow(EntityNotFoundException.class);

        //when
        assertThrows(EntityNotFoundException.class,
                () -> videoService.createVideo(dto));

        //then
        verify(videoRepository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void editVideo_success() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.of(video));
        when(mapper.toVideo(videoDTO)).thenCallRealMethod();
        when(mapper.toDTO(any())).thenCallRealMethod();

        //when
        videoService.editVideo(videoDTO);

        //then
        verify(videoRepository, times(1)).save(any());
        verify(mapper, times(1)).toDTO(any());
    }

    @Test
    void editVideo_empty() {
        //given
        when(videoRepository.findById(ID)).thenReturn(Optional.empty());

        //when
        final var exception = assertThrows(EntityNotFoundException.class,
                () -> videoService.editVideo(videoDTO));

        //then
        assertEquals("Não encontrado", exception.getMessage());
    }

    @Test
    void editVideo_idIsNull() {
        //given
        videoDTO.setId(null);

        //when
        final var exception = assertThrows(RequiredFieldException.class,
                () -> videoService.editVideo(videoDTO));

        //then
        assertEquals("Field id is required", exception.getMessage());
    }

    @Test
    void editVideo_whenCategoryNotExists_throw() {
        //given
        videoDTO.setCategory(ID);
        when(categoryService.findByIdOrThrow(any())).thenThrow(EntityNotFoundException.class);

        //when
        assertThrows(EntityNotFoundException.class,
                () -> videoService.editVideo(videoDTO));

        //then
        verify(videoRepository, never()).save(any());
        verify(mapper, never()).toDTO(any());
    }

    @Test
    void deleteVideo_idIsNull() {
        //when
        final var exception = assertThrows(RequiredFieldException.class,
                () -> videoService.deleteVideo(null));

        //then
        assertEquals("Field id is required", exception.getMessage());
    }

    @Test
    void deleteVideo_success() {
        //when
        videoService.deleteVideo(ID);

        //then
        verify(videoRepository, times(1)).deleteById(ID);
    }

    @Test
    void findAllByCategory_success() {
        //given
        when(categoryService.findByTitle(any())).thenReturn(Optional.of(category));
        when(videoRepository.findByCategory_Id(any())).thenReturn(videoList);

        //when
        final var result = videoService.findAllByCategory(category.getTitle());

        //then
        assertNotEquals(0, result.size());
    }

    @Test
    void findAllByCategory_categoryNotExists_empty() {
        //given
        when(categoryService.findByTitle(any())).thenReturn(Optional.empty());

        //when
        final var result = videoService.findAllByCategory(category.getTitle());

        //then
        assertEquals(0, result.size());
    }

}
