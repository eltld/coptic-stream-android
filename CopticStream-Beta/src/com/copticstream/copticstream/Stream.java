package com.copticstream.copticstream;

/**
 * Created by Mina on 10/4/2014.
 */

import com.google.gson.annotations.SerializedName;

/**
 * The stream class
 */
public class Stream {

    @SerializedName("streamID")
    private int privatestreamID;
    public final int getstreamID() {
        return privatestreamID;
    }
    public final void setstreamID(int value) {
        privatestreamID = value;
    }

    @SerializedName("streamName")
    private String privatestreamName;
    public final String getstreamName() {
        return privatestreamName;
    }
    public final void setstreamName(String value) {
        privatestreamName = value;
    }

    @SerializedName("streamDescription")
    private String privatestreamDescription;
    public final String getstreamDescription() {
        return privatestreamDescription;
    }
    public final void setstreamDescription(String value) {
        privatestreamDescription = value;
    }

    @SerializedName("streamURL")
    private String privatestreamURL;
    public final String getstreamURL() {
        return privatestreamURL;
    }
    public final void setstreamURL(String value) {
        privatestreamURL = value;
    }

    @SerializedName("streamEnabled")
    private Boolean privatestreamEnabled = null;
    public final Boolean getstreamEnabled() {
        return privatestreamEnabled;
    }
    public final void setstreamEnabled(Boolean value) {
        privatestreamEnabled = value;
    }

    @SerializedName("streamImagethumbnail")
    private String privatestreamImagethumbnail;
    public final String getstreamImagethumbnail() {
        return privatestreamImagethumbnail;
    }
    public final void setstreamImagethumbnail(String value) {
        privatestreamImagethumbnail = value;
    }

    @SerializedName("streamImage")
    private String privatestreamImage;
    public final String getstreamImage() {
        return privatestreamImage;
    }
    public final void setstreamImage(String value) {
        privatestreamImage = value;
    }

    @SerializedName("streamTypeID")
    private Integer privatestreamTypeID = null;
    public final Integer getstreamTypeID() {
        return privatestreamTypeID;
    }
    public final void setstreamTypeID(Integer value) {
        privatestreamTypeID = value;
    }
}