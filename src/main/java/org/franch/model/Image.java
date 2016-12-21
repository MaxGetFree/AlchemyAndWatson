package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by franc on 21.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {
    private List<Classifier> classifiers;

    public List<Classifier> getClassifiers() {
        return classifiers;
    }

    public void setClassifiers(List<Classifier> classifiers) {
        this.classifiers = classifiers;
    }
}
