package com.muzkabugu.shadyfade.terminal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shadyfade on 8/17/15.
 */

public class CustomTagListAdapter extends ArrayAdapter {
    protected List<ListRoles> RolesList;

    public CustomTagListAdapter(Context context, List<ListRoles> roles){
        super(context,R.layout.taglist_row, (List) roles);
        RolesList = roles;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View item = inflater.inflate(R.layout.taglist_row, null);

        TextView tag = (TextView) item.findViewById(R.id.tv_tag);

        tag.setText(RolesList.get(position).getName());


        return item;
    }

}
