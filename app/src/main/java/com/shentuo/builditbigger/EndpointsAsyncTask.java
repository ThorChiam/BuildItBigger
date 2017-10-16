package com.shentuo.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.shentuo.builditbigger.backend.myApi.MyApi;
import com.shentuo.builditbigger.backend.myApi.model.MyJokeCollection;
import com.shentuo.mylibrary.DisplayActivity;

import java.io.IOException;

/**
 * Created by ShentuoZhan on 13/6/17.
 */

public class EndpointsAsyncTask extends AsyncTask<Context, Void, MyJokeCollection> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected MyJokeCollection doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.retrieveJokes().execute();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(MyJokeCollection result) {
//        Toast.makeText(context, result.size() + ":" + result.getItems().get(0).getJokeContent(), Toast.LENGTH_LONG);
        try {
            Intent intent = new Intent(context, DisplayActivity.class);
            intent.putExtra("MY_JOKES", result.toPrettyString());
            context.startActivity(intent);
//            Toast.makeText(context, result.toPrettyString(), Toast.LENGTH_LONG).show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
