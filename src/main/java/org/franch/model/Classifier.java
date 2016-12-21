package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by franc on 21.12.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Classifier {
    private String classifier_id;
    private String name;
    private List<ClassRec> classes;

    public String getClassifier_id() {
        return classifier_id;
    }

    public void setClassifier_id(String classifier_id) {
        this.classifier_id = classifier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ClassRec> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassRec> classes) {
        this.classes = classes;
    }
}
