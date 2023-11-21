package com.example.roomdatabase;
// DAO is used to perfom operations on entity class like CRUD operation

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactsDAO {

    @Insert
    void insert(Contacts contacts);
    @Delete
    void delete(Contacts contacts);


    // we can also write here sql query by using annotaion Query like

    @Query("SELECT * FROM contacts_table")
    LiveData<List<Contacts>> getAllContacts();
}
