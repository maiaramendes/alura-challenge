package br.alura.mock;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Video;

import java.util.Arrays;
import java.util.List;

public class VideoMock {

    public static Video createVideo() {
        return new Video().builder()
                .id("id")
                .url("url")
                .description("description")
                .build();
    }

    public static List<Video> createVideoList() {
        return Arrays.asList(createVideo(), createVideo());
    }

    public static VideoRequestDTO createDTO() {
        return new VideoRequestDTO()
                .builder()
                .description("descriptions")
                .title("titles")
                .url("urls")
                .build();
    }

    public static VideoDTO createVideoDTO() {
        return new VideoDTO()
                .builder()
                .id("id")
                .description("descriptions")
                .title("titles")
                .url("urls")
                .build();
    }
}
