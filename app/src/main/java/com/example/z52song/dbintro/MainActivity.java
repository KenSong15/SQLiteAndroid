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

        //read all elements on database
        Log.d("Reading", "Reading all contacts");
        List<Contact> contactList = dbHandler.getAllContacts();

        for(Contact c:contactList){
            String log = "ID: " + c.getId() + " , Name: "+ c.getName()+" , Phone: "+ c.getPhoneNumber();
            Log.d("Result", log);
        }

        //get one contact
        Contact oneContact = dbHandler.getContact(1);
        Log.d("One Contact", "ID: " + oneContact.getId() + " , Name: "+ oneContact.getName()+" , Phone: "+ oneContact.getPhoneNumber());

        //update contact
        oneContact.setName("pauloooooooooo");
        int newContact = dbHandler.updateContact(oneContact);
        Log.d("One Contact", "updated! ID: " + oneContact.getId() + " , Name: "+ oneContact.getName()+" , Phone: "+ oneContact.getPhoneNumber());


    }
}
