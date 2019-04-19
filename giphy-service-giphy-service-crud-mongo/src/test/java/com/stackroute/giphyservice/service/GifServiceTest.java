package com.stackroute.giphyservice.service;

import com.stackroute.giphyservice.domain.Gif;
import com.stackroute.giphyservice.domain.Image;
import com.stackroute.giphyservice.domain.ImageRepresentation;
import com.stackroute.giphyservice.exception.GifAlreadyExistsException;
import com.stackroute.giphyservice.exception.GifNotFoundException;
import com.stackroute.giphyservice.repository.GifRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GifServiceTest {

    @Mock
    private GifRepository gifRepository;
    @InjectMocks
    private GifServiceImpl gifService;
    private Gif gif;
    private List<Gif> gifList;
    private Optional optional;
    private Image image;
    private ImageRepresentation imageRepresentation;
    private List<ImageRepresentation> imageRepresentationList;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gifList = new ArrayList<>();
        imageRepresentationList = new ArrayList<>();
        imageRepresentation = new ImageRepresentation("rep123","http;//imagerepr","56","98","345");
        imageRepresentationList.add(imageRepresentation);
        image = new Image("image12","http://image","50","50","300",imageRepresentationList);
        gif = new Gif("giphy1","gif123","gif","http:url","http:details","cheeseburger","awesome","user1","g","2018-10-11",image);
        gifList.add(gif);
        gif = new Gif("giphy2","gif125","gif","http:url","http:details","burgerking","awesome","user2","g","2017-03-11",image);
        gifList.add(gif);
        optional = Optional.of(gif);

    }

    @After
    public void tearDown() {
        gif = null;
    }
    @Test
    public void testSaveGifSuccess() throws GifAlreadyExistsException {
        when(gifRepository.save(gif)).thenReturn(gif);
        Gif fetchGif = gifService.saveGifToWishList(gif);
        Assert.assertEquals(gif,fetchGif);
        verify(gifRepository,times(1)).save(gif);
        verify(gifRepository,times(1)).findByGiphyId(gif.getGiphyId());
    }

    @Test(expected = GifAlreadyExistsException.class)
    public void testSaveGifFailure() throws GifAlreadyExistsException {
        when(gifRepository.save(gif)).thenReturn(gif);
        when(gifRepository.findByGiphyId(gif.getGiphyId())).thenReturn(gif);
        Gif fetchGif = gifService.saveGifToWishList(gif);
        Assert.assertEquals(gif,fetchGif);
        verify(gifRepository,times(1)).save(gif);
        verify(gifRepository,times(1)).findByGiphyId(gif.getGiphyId());


    }
    @Test
    public void testDeleteGifSuccess() throws GifNotFoundException {
        when(gifRepository.existsById(gif.getGifId())).thenReturn(true);
        when(gifRepository.findById(gif.getGifId())).thenReturn(optional);
        boolean fetchGif = gifService.deleteGifFromWishList(gif.getGifId());
        Assert.assertEquals(true,fetchGif);
        verify(gifRepository,times(1)).findById(gif.getGifId());
        verify(gifRepository,times(1)).deleteById(gif.getGifId());
    }
    @Test(expected = GifNotFoundException.class)
    public void testDeleteGifFailure() throws GifNotFoundException {
        when(gifRepository.existsById(gif.getGifId())).thenReturn(false);
        boolean fetchGif = gifService.deleteGifFromWishList(gif.getGifId());
        Assert.assertEquals(false,fetchGif);
        verify(gifRepository,times(1)).findById(gif.getGifId());
        verify(gifRepository,times(1)).deleteById(gif.getGifId());
    }
    @Test
    public void testUpdateCaptionForGifSuccess() throws GifNotFoundException {
        when(gifRepository.existsById(gif.getGifId())).thenReturn(true);
        when(gifRepository.findById(gif.getGifId())).thenReturn(optional);
        gif.setCaption("caption updated");
        Gif fetchGif = gifService.updateCaptionForGif(gif.getCaption(),gif.getGifId());
        Assert.assertEquals(fetchGif.getCaption(),"caption updated");
        verify(gifRepository,times(2)).findById(gif.getGifId());
        verify(gifRepository,times(1)).save(gif);
    }

    @Test(expected = GifNotFoundException.class)
    public void testUpdateCaptionForGifFailure() throws GifNotFoundException {
        when(gifRepository.existsById(gif.getGifId())).thenReturn(false);
        Gif fetchGif = gifService.updateCaptionForGif(gif.getCaption(),gif.getGifId());
        Assert.assertEquals(false,fetchGif);
        verify(gifRepository,times(2)).findById(gif.getGifId());
        verify(gifRepository,times(1)).save(gif);
    }
    @Test
    public void testGetAllGifsSuccess() throws Exception {
        when(gifRepository.findAll()).thenReturn(gifList);
        List<Gif> list = gifService.getAllGifsFromWishList();
        Assert.assertEquals(list,gifList);
        verify(gifRepository,times(1)).findAll();
    }
    @Test(expected = Exception.class)
    public void testGetAllGifsFailure() throws Exception {
        when(gifRepository.findAll()).thenThrow(Exception.class);
        verify(gifRepository, times(1)).findAll();
    }
    @Test
    public void testFindByGiphyId() {
        when(gifRepository.findByGiphyId(gif.getGiphyId())).thenReturn(gif);
        Gif fetchGif = gifService.getByGiphyId(gif.getGiphyId());
        Assert.assertEquals(fetchGif,gif);
        verify(gifRepository,times(1)).findByGiphyId(gif.getGiphyId());
    }

}
