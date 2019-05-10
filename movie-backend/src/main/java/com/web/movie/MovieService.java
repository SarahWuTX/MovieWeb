package com.web.movie;

import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class MovieService {
    private final MovieRepository movieRepository;
    private static final int pageSize = 10;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
      this.movieRepository = movieRepository;
    }

    /**
     * 获取第n页的电影
     * @param page 页数
     * @return 该页的所有电影
     */
    public ResponseEntity<?> getAll(int page) {
        List<Movie> movies = movieRepository.findAll(PageRequest.of(page, pageSize)).getContent();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    /**
     * 获取电影总数量
     * @return 电影总数量
     */
    public ResponseEntity<?> getMovieCount() {
        long count = movieRepository.count();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * 获取电影详情
     * @param id 电影id
     * @return 电影详情
     */
    public ResponseEntity<?> getById(String id) {
        return movieRepository.findById(id)
                .map(movie -> new ResponseEntity<>(movie, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    public ResponseEntity<?> getByKeyword(String keyword, int page) {
        List<Movie> movies = movieRepository
                .findAllByTitleContainsOrSummaryContains(keyword, keyword, PageRequest.of(page, pageSize))
                .getContent();
        long count = movieRepository.countMoviesByTitleContainsOrSummaryContains(keyword, keyword);
        Map<String, Object> result = new HashMap<>();
        result.put("movies", movies);
        result.put("count", count);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
