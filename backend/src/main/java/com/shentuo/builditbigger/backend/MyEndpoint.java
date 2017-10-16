/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.shentuo.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.shentuo.com",
                ownerName = "backend.builditbigger.shentuo.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /**
     * A simple endpoint method that retrieves list of jokes and send back
     */
    @ApiMethod(name = "retrieveJokes")
    public List<MyJoke> retrieveJokes() {
        MyJoke joke = new MyJoke();
        joke.setJokeContent("This is the first joke!");
        MyJoke joke2 = new MyJoke();
        joke2.setJokeContent("This is the second joke!");
        List<MyJoke> myJokes = new ArrayList<>();
        myJokes.add(joke);
        myJokes.add(joke2);
        return myJokes;
    }

}
