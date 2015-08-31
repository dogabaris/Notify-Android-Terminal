package com.muzkabugu.shadyfade.terminal;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shadyfade on 28.08.2015.
 */
public class TagListActivity extends Activity {
    public ListView lv_notify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final String tagName;
        Bundle extras = getIntent().getExtras();
        tagName = extras.getString("tag");

        Global.service.GetNotify(tagName, new Callback<Posts>() {
            @Override
            public void success(Posts posts, Response response) {
                lv_notify = (ListView) findViewById(R.id.lv_notify);

                final CustomNotifyListAdapter adapter = new CustomNotifyListAdapter(TagListActivity.this, posts.Posts);
                lv_notify.setAdapter(adapter);

            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(TagListActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Thread t = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Global.service.GetNotify(tagName, new Callback<Posts>() {
                                    @Override
                                    public void success(Posts posts, Response response) {
                                        lv_notify = (ListView) findViewById(R.id.lv_notify);

                                        final CustomNotifyListAdapter adapter = new CustomNotifyListAdapter(TagListActivity.this, posts.Posts);
                                        lv_notify.setAdapter(adapter);
                                        Toast.makeText(TagListActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void failure(RetrofitError error) {
                                        Toast.makeText(TagListActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };

        t.start();
    }


}
