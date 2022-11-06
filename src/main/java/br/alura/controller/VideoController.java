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
    public List<VideoDTO> getAll() {
        return videoService.getAll();
    }

    @GetMapping("/category")
    public List<VideoDTO> getAllByCategory(@RequestParam final String search) {
        return videoService.findAllByCategory(search);
    }

    @GetMapping("/{id}")
    public VideoDTO find(@PathVariable final String id) {
        return videoService.findById(id);
    }

    @PostMapping
    public VideoDTO create(@RequestBody final VideoRequestDTO dto) {
        return videoService.createVideo(dto);
    }

    @PatchMapping
    public VideoDTO edit(@RequestBody final VideoDTO dto) {
        return videoService.editVideo(dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable final String id) {
        videoService.deleteVideo(id);
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        videoService.deleteAll();
    }
}
