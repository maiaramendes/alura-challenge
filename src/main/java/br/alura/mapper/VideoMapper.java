package br.alura.mapper;

import br.alura.dto.VideoDTO;
import br.alura.dto.VideoRequestDTO;
import br.alura.entity.Category;
import br.alura.entity.Video;
import org.mapstruct.*;

@Mapper
public interface VideoMapper {

    @Mapping(source = "category.id", target = "category")
    VideoDTO toDTO(Video video);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "category", target = "category", qualifiedByName = "checkIfIsNull")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Video toVideo(VideoDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "category", target = "category.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Video toVideo(VideoRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(source = "category", target = "category")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Video merge(Video source, @MappingTarget Video target);

    @Named(value = "checkIfIsNull")
    default Category checkIfIsNull(final String category) {
        if (category == null) {
            return null;
        }
        return new Category().builder().id(category).build();
    }

}
