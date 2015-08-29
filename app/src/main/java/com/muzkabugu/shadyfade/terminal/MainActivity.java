package com.muzkabugu.shadyfade.terminal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends Activity {
    public Roles listroles;
    public ListView lv_tags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        lv_tags = (ListView) findViewById(R.id.lv_tags);

        Global.service.GetTags(new Callback<Roles>() {
            @Override
            public void success(Roles roles, Response response) {
                listroles = roles;
                Toast.makeText(MainActivity.this, "Success!", Toast.LENGTH_LONG).show();
                final CustomTagListAdapter adapter = new CustomTagListAdapter(MainActivity.this, listroles.listroles);

                lv_tags.setAdapter(adapter);

                lv_tags.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent i = new Intent(MainActivity.this, TagListActivity.class);
                        String tagName = null;
                        tagName = listroles.listroles.get(position).getName();
                        i.putExtra("tag", tagName);
                        startActivity(i);
                        MainActivity.this.finish();
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




    }
}
