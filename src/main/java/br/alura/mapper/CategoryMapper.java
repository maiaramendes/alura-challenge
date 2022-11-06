package br.alura.mapper;

import br.alura.dto.CategoryDTO;
import br.alura.dto.CategoryRequestDTO;
import br.alura.entity.Category;
import org.mapstruct.*;

@Mapper
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category toEntity(CategoryRequestDTO source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category toCategory(CategoryRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category merge(Category source, @MappingTarget Category target);
}
