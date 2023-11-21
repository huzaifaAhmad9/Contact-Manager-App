package com.example.roomdatabase;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repostory {

    // the avaialable data source: in our case only room database
    private final ContactsDAO contactsDAO;

    public Repostory(Application application) {
        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactsDAO = contactDatabase.getContactDAO();
    }

    // methods in DAO being executed from here
    public void addContact(Contacts contacts){
        // used for background operations
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // used for updating the UI
        Handler handler = new Handler(Looper.getMainLooper());
        // executing tasks on separate thread
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.insert(contacts);
            }
        });
    }
    public void deleteContact(Contacts contacts){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                contactsDAO.delete(contacts);
            }
        });

    }
    public LiveData<List<Contacts>> getAllContacts(){
        return contactsDAO.getAllContacts();
    }
}
