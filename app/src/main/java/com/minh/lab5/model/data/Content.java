
package com.minh.lab5.model.data;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("rendered")
    @Expose
    private String rendered;

    public String getRendered() {
        if(rendered.equalsIgnoreCase("<p>")){
            rendered.replaceAll("<p>","");
        }
        Log.d("////", rendered);
        return rendered;
    }

    public void setRendered(String rendered) {

        this.rendered = rendered;
    }



}
