package com.copticstream.copticstream;

/**
 * Created by Mina on 10/4/2014.
 */

/**
 * The stream class
 */
public class Stream {
    private int privatestreamID;
    public final int getstreamID() {
        return privatestreamID;
    }
    public final void setstreamID(int value) {
        privatestreamID = value;
    }
    private String privatestreamName;
    public final String getstreamName() {
        return privatestreamName;
    }
    public final void setstreamName(String value) {
        privatestreamName = value;
    }
    private String privatestreamDescription;
    public final String getstreamDescription() {
        return privatestreamDescription;
    }
    public final void setstreamDescription(String value) {
        privatestreamDescription = value;
    }
    private String privatestreamURL;
    public final String getstreamURL() {
        return privatestreamURL;
    }
    public final void setstreamURL(String value) {
        privatestreamURL = value;
    }
    private Boolean privatestreamEnabled = null;
    public final Boolean getstreamEnabled() {
        return privatestreamEnabled;
    }
    public final void setstreamEnabled(Boolean value) {
        privatestreamEnabled = value;
    }
    private String privatestreamImagethumbnail;
    public final String getstreamImagethumbnail() {
        return privatestreamImagethumbnail;
    }
    public final void setstreamImagethumbnail(String value) {
        privatestreamImagethumbnail = value;
    }
    private String privatestreamImage;
    public final String getstreamImage() {
        return privatestreamImage;
    }
    public final void setstreamImage(String value) {
        privatestreamImage = value;
    }
    private Integer privatestreamTypeID = null;
    public final Integer getstreamTypeID() {
        return privatestreamTypeID;
    }
    public final void setstreamTypeID(Integer value) {
        privatestreamTypeID = value;
    }
}