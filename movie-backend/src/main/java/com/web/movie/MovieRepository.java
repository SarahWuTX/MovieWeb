package com.web.movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MovieRepository extends MongoRepository<Movie, String> {

    @Override
    <S extends Movie> S save(S s);

    Page<Movie> findAllByTitleContainsOrSummaryContains(String key1, String key2, Pageable pageable);

    long countMoviesByTitleContainsOrSummaryContains(String key1, String key2);
}
