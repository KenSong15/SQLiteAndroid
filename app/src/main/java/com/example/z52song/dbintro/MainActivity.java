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
        Log.d("Insert||", "incert in...");
        dbHandler.addContact(new Contact("Ken", "123"));
        dbHandler.addContact(new Contact("Ariel", "456"));

        //read all elements on database
        Log.d("Read||", "Reading all contacts...");
        List<Contact> contactList = dbHandler.getAllContacts();



        for(Contact c:contactList){
            String log = "ID: " + c.getId() + " , Name: "+ c.getName()+" , Phone: "+ c.getPhoneNumber();
            Log.d("Result||   ", log);
        }

        //get one contact
        Contact oneContact = dbHandler.getContact(1);
        Log.d("GetOne||", "ID: " + oneContact.getId() + " , Name: "+ oneContact.getName()+" , Phone: "+ oneContact.getPhoneNumber());

        //update contact
        oneContact.setName("pauloooooooooo");
        int newContact = dbHandler.updateContact(oneContact);
        Log.d("GetOne||", "updated! ID: " + oneContact.getId() + " , Name: "+ oneContact.getName()+" , Phone: "+ oneContact.getPhoneNumber());

        //delete contact
        //TODO delete work but not having a proper way to show the result.
        //dbHandler.deleteContact(dbHandler.getContact(5));

        //count the number of row in table
        Log.d("Count|| ", String.valueOf(dbHandler.getContactsCount()));
    }
}
