package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by franc on 14.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassifyDiscription {

    private List<Image> images;
    private int images_processed;



    public int getImages_processed() {
        return images_processed;
    }

    public void setImages_processed(int images_processed) {
        this.images_processed = images_processed;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
