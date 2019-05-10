package com.web.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.TextScore;

import java.util.Map;


@Document(value = "movie")
@Data
@AllArgsConstructor
public class Movie {
    @Id
    private String id;

    @Field("title")
    private String title;

    @Field("directors")
    private Map<String, String>[] directors;

    @Field("genres")
    private String[] genres;

    @Field("rating")
    private Map<String, Object> rating;

    @Field("pubdate")
    private String[] pubdate;

    @Field("countries")
    private String[] countries;

    @Field("casts")
    private Map<String, String>[] casts;

    @Field("languages")
    private String[] languages;

    @Field("writers")
    private Map<String, String>[] writers;

    @Field("poster")
    private String poster;

    @Field("summary")
    private String summary;

    @Field("imdb")
    private String imdb;

    @Field("year")
    private String year;

    @Field("duration")
    private String duration;

}
