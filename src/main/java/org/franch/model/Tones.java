package org.franch.model;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;

import java.util.List;


public class Tones {

    private List<ToneCategory> toneCategories;

    public List<ToneCategory> getToneCategories() {
        return toneCategories;
    }

    public void setToneCategories(List<ToneCategory> toneCategories) {
        this.toneCategories = toneCategories;
    }

}
