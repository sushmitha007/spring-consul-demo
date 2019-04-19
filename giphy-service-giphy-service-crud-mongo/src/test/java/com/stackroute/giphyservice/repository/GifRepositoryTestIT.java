package com.stackroute.giphyservice.repository;


import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.domain.Image;
import com.stackroute.giphyservice.domain.ImageRepresentation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataMongoTest
public class GifRepositoryTestIT {

    @Autowired
    GifRepository gifRepository;

    private Gif gif;
    private Image image;
    private ImageRepresentation imageRepresentation;
    private List<ImageRepresentation> imageRepresentationList;

    @Before
    public void setUp() {
        imageRepresentationList = new ArrayList<>();
        imageRepresentation = new ImageRepresentation("rep123","http;//imagerepr","56","98","345");
        imageRepresentationList.add(imageRepresentation);
        image = new Image("image12","http://image","50","50","300",imageRepresentationList);
        gif = new Gif("giphy1","gif123","gif","http:url","http:details","cheeseburger","awesome","user1","g","2018-10-11",image);

    }

    @After
    public void tearDown() {
        gif = null;
        gifRepository.deleteAll();
    }
    @Test
    public void testSaveGifSuccess() {
        gifRepository.save(gif);
        Gif fetchGif =gifRepository.findById(gif.getGifId()).get();
        Assert.assertEquals(fetchGif.getTitle(),gif.getTitle());
    }
    @Test
    public void testSaveGifFailure() {
       Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
       gifRepository.save(newGif);
       gifRepository.save(gif);
       Gif fetchGif = gifRepository.findById(gif.getGifId()).get();
       Assert.assertNotEquals(fetchGif,gif);

    }
    @Test
    public void testUpdateCaptionSuccess(){
        gifRepository.save(gif);
        Gif fetchGif = gifRepository.findById(gif.getGifId()).get();
        fetchGif.setCaption("updating the caption");
        gifRepository.save(fetchGif);
        Gif newGif = gifRepository.findByGiphyId(gif.getGiphyId());
        Assert.assertEquals("updating the caption" , newGif.getCaption());
    }
    @Test
    public void testUpdateCaptionFailure() {
        gifRepository.save(gif);
        Gif fetchGif = gifRepository.findById(gif.getGifId()).get();
        fetchGif.setCaption("updating the caption");
        gifRepository.save(fetchGif);
        Assert.assertNotEquals(gif,fetchGif);
    }
    @Test
    public void testDeleteGifSuccess() {
        gifRepository.save(gif);
        Gif fetchGif = gifRepository.findById(gif.getGifId()).get();
        gifRepository.delete(fetchGif);
        Assert.assertEquals(Optional.empty(), gifRepository.findById(gif.getGifId()));
    }
    @Test
    public void testDeleteGifFailure() {
        Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
        gifRepository.save(newGif);
        gifRepository.save(gif);
        Gif fetchGif = gifRepository.findById(newGif.getGifId()).get();
        gifRepository.delete(fetchGif);
        Assert.assertNotEquals(Optional.empty(),gifRepository.findById(gif.getGifId()));
    }
    @Test
    public void testGetAllGifs() {
        Gif newGif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
        gifRepository.save(newGif);
        gifRepository.save(gif);
        List<Gif> gifList = gifRepository.findAll();
        Assert.assertEquals(2,gifList.size());
        Assert.assertEquals("gif125",gifList.get(0).getGiphyId());

    }


}
