package br.alura.mapper;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Video;
import org.mapstruct.*;

@Mapper
public interface VideoMapper {

    VideoDTO toDTO(Video video);

    Video toVideo(VideoDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Video toVideo(VideoRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Video merge(Video source, @MappingTarget Video target);
}
