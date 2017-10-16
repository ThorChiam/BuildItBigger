package com.shentuo.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.shentuo.builditbigger.backend.myApi.MyApi;
import com.shentuo.builditbigger.backend.myApi.model.MyJoke;
import com.shentuo.builditbigger.backend.myApi.model.MyJokeCollection;

import java.io.IOException;
import java.util.List;

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
        List<MyJoke> myJokes = result.getItems();
        Toast.makeText(context, myJokes.get(0).getJokeContent(), Toast.LENGTH_LONG).show();
    }
}
