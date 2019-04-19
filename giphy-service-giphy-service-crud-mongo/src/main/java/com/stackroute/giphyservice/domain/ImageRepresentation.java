package com.stackroute.giphyservice.domain;

import org.springframework.data.annotation.Id;


public class ImageRepresentation {
    @Id
    private String representationId;
    private String url;
    private String width;
    private String height;
    private String size;

    public ImageRepresentation() {
    }

    public ImageRepresentation(String representationId, String url, String width, String height, String size) {
        this.representationId = representationId;
        this.url = url;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public String getRepresentationId() {
        return representationId;
    }

    public void setRepresentationId(String representationId) {
        this.representationId = representationId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
