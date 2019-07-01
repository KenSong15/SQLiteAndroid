package com.example.z52song.dbintro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Contact;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler dbHandler = new DatabaseHandler(this);

        //Insert contact
        Log.d("Insert", "incert in...");
        dbHandler.addContact(new Contact("Ken", "1234567890"));
        dbHandler.addContact(new Contact("Alice", "1111111111"));
        dbHandler.addContact(new Contact("Bob", "22222222222"));
        dbHandler.addContact(new Contact("Cindy", "333333333333"));

        Log.d("Reading", "Reading all contacts");
        List<Contact> contactList = dbHandler.getAllContacts();

        for(Contact c:contactList){
            String log = "ID: " + c.getId() + " , Name: "+ c.getName()+" , Phone: "+ c.getPhoneNumber();
            Log.d("Result", log);
        }

    }
}
