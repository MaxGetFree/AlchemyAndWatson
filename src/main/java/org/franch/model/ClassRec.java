package org.franch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by franc on 21.12.2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClassRec {
    private String classname;
    private Float score;



    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }


    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
