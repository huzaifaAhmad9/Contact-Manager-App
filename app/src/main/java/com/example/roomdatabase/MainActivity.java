package com.example.roomdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.roomdatabase.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contacts = new ArrayList<>();
    // adapter
    private MyAdapter myAdapter;
    // binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         // data binding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        handler = new MainActivityClickHandler(this);
        mainBinding.setClickHandler(handler);

        // recycler view
        RecyclerView recyclerView = mainBinding.rec;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        // database
        contactDatabase = ContactDatabase.getInstance(this);

        // view model
        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // inserting a new contact (just for testing)
        Contacts c1 = new Contacts("Jack","abc@gmail.com");
        viewModel.addNewContacts(c1);

        // loading data from room db
        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {
                contacts.clear(); // without this there is duplication of items
                for (Contacts c:contacts){
                    Log.v("TAGY", c.getName());
                    contacts.add(c);
                }
                myAdapter.notifyDataSetChanged();
            }
        });

        //adapter
        myAdapter = new MyAdapter(contacts);

        // linking hte recycler view with adapter
        recyclerView.setAdapter(myAdapter);

        // swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {// 0 means no drag and drop suppoerted
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                // if swipe item to left
                Contacts c =  contacts.get(viewHolder.getAdapterPosition());
                // for deleting
                viewModel.deleteContact(c);

            }
        }).attachToRecyclerView(recyclerView);







    }
}