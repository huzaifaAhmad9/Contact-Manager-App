package com.example.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Contacts.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactsDAO getContactDAO();

    // singelton pattern ---> to provide one instance during its lifecycle
    public static ContactDatabase dbInstance;
    public static synchronized ContactDatabase getInstance(Context context){
        if (dbInstance == null){
            dbInstance = Room.databaseBuilder(context.getApplicationContext(),ContactDatabase.class,"contacts_db").fallbackToDestructiveMigration().build();
        }// fallback wla method islie use kia h k jb hm version wegara ko change krte h future nme to purana table khud drop ho k purani entities k sth hi new bn jata h
        return dbInstance;
    }


}
