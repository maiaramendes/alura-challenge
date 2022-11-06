package br.alura.mapper;

import br.alura.dto.VideoDTO;
import br.alura.entity.Video;
import br.alura.mock.VideoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class VideoMapperTest {

    @InjectMocks
    private VideoMapperImpl videoMapper;

    private final static VideoDTO videoDTO = VideoMock.createVideoDTO();

    private final static Video video = VideoMock.createVideoWithCategory();

    @NullSource
    @ParameterizedTest
    void toVideo(final String param) {
        //when
        videoDTO.setCategory(param);
        final var result = videoMapper.toVideo(videoDTO);

        //then
        assertNull(result.getCategory());
    }

    @Test
    void merge() {
        //given
        final var toVideo = videoMapper.toVideo(videoDTO);
        toVideo.setDescription("teste");

        //when
        final var result = videoMapper.merge(toVideo, video);

        //then
        assertEquals(toVideo.getDescription(), result.getDescription());
        assertEquals(toVideo.getTitle(), result.getTitle());
        assertEquals(toVideo.getUrl(), result.getUrl());
        assertEquals(video.getCategory().getId(), result.getCategory().getId());
        assertEquals(video.getCategory().getTitle(), result.getCategory().getTitle());
        assertEquals(video.getCategory().getColor(), result.getCategory().getColor());
    }
}
