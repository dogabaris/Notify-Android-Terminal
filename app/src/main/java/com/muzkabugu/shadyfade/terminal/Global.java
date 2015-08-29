package com.muzkabugu.shadyfade.terminal;


import retrofit.RestAdapter;

/**
 * Created by shadyfade on 8/3/15.
 */
public class Global {

        public static RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint("http://178.62.207.239")
                        .build();
        public static Services service = restAdapter.create(Services.class);
}
