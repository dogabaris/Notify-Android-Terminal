package com.muzkabugu.shadyfade.terminal;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by shadyfade on 7/31/15.
 */
public interface Services {

    @GET("/tags")
        void GetTags(Callback<Roles> cb);

    @GET("/posts/tag/{tag}/published")
       void GetNotify(@Path("tag") String tag, Callback<Posts> cb);

}
