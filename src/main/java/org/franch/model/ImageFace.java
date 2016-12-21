package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Oleg Stradomski on 16.11.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageFace {

    private Integer height;

    private Integer width;

    private Integer positionX;

    private Integer positionY;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}
