package com.github.mvp.data;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/27.
 */
public class RootEntity {
    private ArrayList<StoriesEntity> stories ;
    public void setStories(ArrayList<StoriesEntity> stories){
        this.stories = stories;
    }
    public ArrayList<StoriesEntity> getStories(){
        return this.stories;
    }
}
