package com.web.movie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@Api(tags = "Movie")
@RequestMapping(value = "/movie")
public class MovieController {
    private final MovieService movieService;
    private static final int minPage = 0;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @ApiOperation(value = "获取第n页所有电影")
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam @Range(min = minPage) int page) {
        return movieService.getAll(page);
    }


    @ApiOperation(value = "获取电影总数量")
    @RequestMapping(value = "/getMovieCount",method = RequestMethod.GET)
    public ResponseEntity<?> getMovieCount() {
        return movieService.getMovieCount();
    }


    @ApiOperation(value = "获取电影详情")
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@RequestParam String id) {
        return movieService.getById(id);
    }

    @ApiOperation(value = "搜索电影")
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ResponseEntity<?> getByKeyword(@RequestParam @NotEmpty String keyword,
                                          @RequestParam @Range(min = minPage) int page){
        return movieService.getByKeyword(keyword, page);
    }
}
