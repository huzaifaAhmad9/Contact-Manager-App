package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.roomdatabase.databinding.ActivityAddNewContactBinding;
import com.example.roomdatabase.databinding.ActivityMainBinding;

public class AddNewContactActivity extends AppCompatActivity {
    private ActivityAddNewContactBinding activityAddNewContactBinding;
    private AddNewContactListHandler addNewContactListHandler;
    private Contacts contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);




        contacts = new Contacts();
        activityAddNewContactBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

        addNewContactListHandler = new AddNewContactListHandler (contacts, this, myViewModel);

        activityAddNewContactBinding.setContact(contacts);
        activityAddNewContactBinding.setClickHandler(addNewContactListHandler);


    }
}