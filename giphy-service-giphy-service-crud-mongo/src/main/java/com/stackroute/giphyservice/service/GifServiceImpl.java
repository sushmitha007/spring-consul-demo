package com.stackroute.giphyservice.service;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.repository.GifRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GifServiceImpl implements GifService {
    private GifRepository gifRepository;
    private Gif gif;
    private static final Logger LOGGER = LoggerFactory.getLogger(GifServiceImpl.class);

    @Autowired
    public GifServiceImpl(final GifRepository gifRepository) {
        this.gifRepository = gifRepository;
    }

    public GifServiceImpl() {
    }

    @Override
    public Gif saveGifToWishList(Gif gif) throws GifAlreadyExistsException {
        Gif newGif = gifRepository.findByGiphyId(gif.getGiphyId());
        if (newGif != null) {
            throw new GifAlreadyExistsException();
        }
        return gifRepository.save(gif);
    }

    @Override
    public boolean deleteGifFromWishList(String gifId) throws GifNotFoundException {
        boolean status = false;
        Optional optional = gifRepository.findById(gifId);
        if (optional.isPresent()) {
            gifRepository.deleteById(gifId);
            status = true;
        } else {
            LOGGER.warn("Gif not found exception");
            throw new GifNotFoundException();
        }
        return status;
    }

    @Override
    public Gif updateCaptionForGif(String caption, String gifId) throws GifNotFoundException {
        Optional optional = gifRepository.findById(gifId);
        if (optional.isPresent()) {
            gif = gifRepository.findById(gifId).get();
            gif.setCaption(caption);
            gifRepository.save(gif);
        } else {
            throw new GifNotFoundException();
        }
        return gif;
    }

    @Override
    public List<Gif> getAllGifsFromWishList() throws Exception {
        return gifRepository.findAll();
    }

    @Override
    public Gif getByGiphyId(String giphyId) {
        return gifRepository.findByGiphyId(giphyId);
    }
}