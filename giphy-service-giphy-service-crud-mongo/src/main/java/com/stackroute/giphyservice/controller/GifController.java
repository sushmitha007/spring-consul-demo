package com.stackroute.giphyservice.controller;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.service.GifService;
import com.stackroute.giphyservice.service.GifServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/giphyservice")
public class GifController {
    private ResponseEntity responseEntity;
    private GifService gifService;

    private static final Logger LOGGER = LoggerFactory.getLogger(GifController.class);


    @Autowired
    public GifController(final GifService gifService) {
        this.gifService = gifService;
    }

    @PostMapping("gif")
    public ResponseEntity<?> saveGifToWishList(@RequestBody Gif gif)throws GifAlreadyExistsException{
        try {
            gifService.saveGifToWishList(gif);
            responseEntity = new ResponseEntity(gif, HttpStatus.CREATED);
            LOGGER.info("The gif is saved to the database successfully!!!");

        } catch (GifAlreadyExistsException e) {
            LOGGER.error("This gif is already there in the database...Try another one!!");
            throw new GifAlreadyExistsException();
        } catch (Exception e) {
            LOGGER.error("Internal server error...Try after sometime!!"+e.getMessage());
            responseEntity = new ResponseEntity("Error while saving the gif!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("gif/{gifId}")
    public ResponseEntity<?> deleteGifFromWishList(@PathVariable("gifId") String gifId)throws GifNotFoundException {
        try {
            gifService.deleteGifFromWishList(gifId);
            responseEntity = new ResponseEntity("Successfully Deleted!!", HttpStatus.OK);
            LOGGER.info("The gif is deleted from the database successfully!!!");
        } catch (GifNotFoundException e) {
            LOGGER.error("This gif with the specified gifId is not found...Try another one!!");
            throw new GifNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error!!Try after sometime" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Internal server error...Try after sometime!!");
        }
        return responseEntity;
    }

    @PatchMapping("gif/{gifId}")
    public ResponseEntity<?> updateCaptionForGif(@RequestBody Gif gif, @PathVariable("gifId") String gifId)throws GifNotFoundException {
        try {
            Gif updatedGif = gifService.updateCaptionForGif(gif.getCaption(),gifId);
            responseEntity = new ResponseEntity(updatedGif, HttpStatus.OK);
            LOGGER.info("The gif is updated successfully!!!");
        } catch (GifNotFoundException e) {
            LOGGER.error("This gif with the specified gifId is not found...Try another one!!");
            throw new GifNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error while updating!!Try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Internal server error...Try after sometime!!"+e.getMessage());
        }
        return responseEntity;
    }

    @GetMapping("gifs")
    public ResponseEntity<?> getAllGifsFromWishList() {
        try {
            responseEntity = new ResponseEntity(gifService.getAllGifsFromWishList(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity("Error!!Try after sometime" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            LOGGER.error("Internal server error...Try after sometime!!");
        }
        return responseEntity;
    }

}
