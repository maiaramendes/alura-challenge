package br.alura.controller;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @GetMapping
    public List<VideoDTO> getAllVideos() {
        return videoService.getAll();
    }

    @GetMapping("/{id}")
    public VideoDTO findVideo(@PathVariable final String id) {
        return videoService.findById(id);
    }

    @PostMapping
    public VideoDTO createVideo(@RequestBody final VideoRequestDTO dto) {
        return videoService.createVideo(dto);
    }

    @PatchMapping
    public VideoDTO editVideo(@RequestBody final VideoDTO dto) {
        return videoService.editVideo(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable final String id) {
        videoService.deleteVideo(id);
    }

}
