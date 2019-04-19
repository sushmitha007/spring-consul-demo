package com.stackroute.giphyservice.domain;


import org.springframework.data.annotation.Id;

import java.util.List;

public class Image {
    @Id
    private String imageId;
    private String defaultUrl;
    private String defaultWidth;
    private String defaultHeight;
    private String defaultSize;
    private List<ImageRepresentation> imageRepresentations;

    public Image() {
    }

    public Image(String imageId, String defaultUrl, String defaultWidth, String defaultHeight, String defaultSize, List<ImageRepresentation> imageRepresentations) {
        this.imageId = imageId;
        this.defaultUrl = defaultUrl;
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
        this.defaultSize = defaultSize;
        this.imageRepresentations = imageRepresentations;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(String defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public String getDefaultHeight() {
        return defaultHeight;
    }

    public void setDefaultHeight(String defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public String getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(String defaultSize) {
        this.defaultSize = defaultSize;
    }

    public List<ImageRepresentation> getImageRepresentations() {
        return imageRepresentations;
    }

    public void setImageRepresentations(List<ImageRepresentation> imageRepresentations) {
        this.imageRepresentations = imageRepresentations;
    }
}
