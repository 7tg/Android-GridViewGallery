package com.tayyipgoren.galleypage;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// For JSON
// For HTTP


public class ApiHandler extends AsyncTask<String, Void, Void>
{
    private OkHttpClient client;
    public static ArrayList<String> data = new ArrayList<String>();


    public ApiHandler()
    {
        this.client = new OkHttpClient();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        // Notify image_adapter adapter
        MainActivity.imageAdapter.notifyDataSetChanged();

    }

    @Override
    protected Void doInBackground(String... url) {
        // Building Request for json
        Request req = requestBuilder(url[0]);

        //Getting JSON object
        JSONObject json = getJSON(req);

//        Log.e(MainActivity.LOG_TAG,json.toString());
        try {
            // Getting image array from JSON
            JSONArray jsonArray = json.getJSONArray("images");
//            Log.w(MainActivity.LOG_TAG, jsonArray.getJSONObject(0).getString("url"));

            // Adding items to data ArrayList<String>
            for (int i = 0; i < jsonArray.length(); i++)
            {
                data.add(jsonArray.getJSONObject(i).getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Request requestBuilder(String url)
    {
        return new Request.Builder().url(url).build();
    }

    public String getRequest(Request req)
    {
        try {
            Response resp = client.newCall(req).execute();
            if (resp.isSuccessful())
            {
                return resp.body().string();
            }else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJSON(Request req)
    {
        try {
            return new JSONObject(getRequest(req));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
