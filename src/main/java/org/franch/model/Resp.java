package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Resp {

    private List<ImageFace> imageFaces;

    public List<ImageFace> getImageFaces() {
        return imageFaces;
    }

    public void setImageFaces(List<ImageFace> imageFaces) {
        this.imageFaces = imageFaces;
    }
}