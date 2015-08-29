package com.muzkabugu.shadyfade.terminal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by shadyfade on 27.08.2015.
 */
public class Roles {
    @SerializedName("Roles")
    @Expose
    public List<ListRoles> listroles;

    public List<ListRoles> getRoles() {
        return listroles;
    }

    public void setRoles(List<ListRoles> roles) {
        this.listroles = roles;
    }
}
