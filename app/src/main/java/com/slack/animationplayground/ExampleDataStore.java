package com.slack.animationplayground;

import com.slack.animationplayground.collapsingheader.CollapsingHeaderActivity;

import java.util.ArrayList;

public class ExampleDataStore {

    private static ArrayList<Example> exampleArrayList;

    public static ArrayList<Example> getExamples() {
        if (exampleArrayList != null) {
            return exampleArrayList;
        }

        exampleArrayList = new ArrayList<>();

        exampleArrayList.add(new Example("Collapsing header",
                "This animation will expand and collapse a header view and crossfade between two views " +
                        "in the header. The changing height of the header will push/pull views that " +
                        "are below it in the view hierarchy.",
                CollapsingHeaderActivity.class));

        exampleArrayList.add(new Example("More to come",
                "As we create new animations, we should port them into this project for re-use and" +
                        "public consumption!",
                null));

        return exampleArrayList;
    }
}
