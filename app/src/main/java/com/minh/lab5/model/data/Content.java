
package com.minh.lab5.model.data;

import android.text.Html;
import android.text.Spanned;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Content {

    @SerializedName("rendered")
    @Expose
    private String rendered;

    public Spanned getRendered() {
        return Html.fromHtml(rendered);
    }

    public void setRendered(String rendered) {

        this.rendered = rendered;
    }



}
