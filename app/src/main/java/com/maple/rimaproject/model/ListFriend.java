package com.maple.rimaproject.model;

import java.util.ArrayList;


public class ListFriend {
    private ArrayList<User> listFriend;

    public ArrayList<User> getListFriend() {
        return listFriend;
    }

    public ListFriend(){
        listFriend = new ArrayList<>();
    }

    public String getAvataById(String id){
        for(User user: listFriend){
            if(id.equals(user.id)){
                return user.avata;
            }
        }
        return "";
    }

    public void setListFriend(ArrayList<User> listFriend) {
        this.listFriend = listFriend;
    }
}
