package com.copticstream.copticstream;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mina on 10/9/2014.
 */
public class HelloWorld {
    @SerializedName("Message")
    private String message;
    public final String getmessage() {
        return message;
    }
    public final void setmessage(String value) {
        message = value;
    }
}
