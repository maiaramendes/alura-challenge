package br.alura.repository;

import br.alura.entity.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends MongoRepository<Video, String> {

    List<Video> findByCategory_Id(final String categoryId);

}
