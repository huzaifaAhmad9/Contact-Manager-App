package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {
    // if u need to use context inside your viewmodel,
    // you should use android view model AVM bcz it contains the application context
    private Repostory repostory;

    // live data

    LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application) {
        super(application);
        this.repostory = new Repostory(application);
    }


    public LiveData<List<Contacts>> getAllContacts(){
        allContacts = repostory.getAllContacts();
        return allContacts;
    }

    public void addNewContacts(Contacts contacts){
        repostory.addContact(contacts);
    }

    public void deleteContact(Contacts contacts){
        repostory.deleteContact(contacts);
    }


    // AVM is a subclass of viewmodel nd similiar to them, they are designed to store and
    // manage UI related data and responsible to prepare and provide data for UI and
    // automatically allow data to survive confg change

}
