package com.copticstream.copticstream;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Mina on 10/24/2014.
 */
public class StreamViews {
    private Context context;

    public StreamViews(Context context) {
        this.context = context;
    }

    public void IncrementStreamViews(int streamID){

        HashMap<String, Integer> params = new HashMap<String, Integer>();
        params.put("streamID", streamID);

        JsonObjectRequest req = new JsonObjectRequest("http://copticstream.com/streams.asmx/IncrementStreamViews", new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
        VolleySingleton.getInstance(context).addToRequestQueue(req);
        req.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }
}
