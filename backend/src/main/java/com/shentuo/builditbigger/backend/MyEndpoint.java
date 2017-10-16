/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.shentuo.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.shentuo.jokes.Joker;

import java.util.ArrayList;
import java.util.List;

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
        List<MyJoke> myJokeList = new ArrayList<>();

        Joker joker = new Joker();
        for (String joke : joker.tellJokes()){
            MyJoke myJoke = new MyJoke();
            myJoke.setJokeContent(joke);
            myJokeList.add(myJoke);
        }
        return myJokeList;
    }

}
