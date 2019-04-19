package com.stackroute.giphyservice.repository;

import com.stackroute.giphyservice.domain.Gif;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GifRepository extends MongoRepository<Gif, String> {
    Gif findByGiphyId(String GiphyId);
}
