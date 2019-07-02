package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import Model.Contact;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //SQL - structured query language
        String CREATE_CONTACT_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "(" +
                Util.KEY_ID + " INTEGER PRIMARY KEY," +
                Util.KEY_NAME + " TEXT, " +
                Util.KEY_PHONE_NAMBER + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop is deleting
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ Util.TABLE_NAME);
        //create table again
        onCreate(sqLiteDatabase);
    }

    //CRUD operation: create read update delete

    //add a contact
    public void addContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        //making the content
        ContentValues value = new ContentValues();
        value.put(Util.KEY_NAME, contact.getName());
        value.put(Util.KEY_PHONE_NAMBER, contact.getPhoneNumber());

        //insert to row
        db.insert(Util.TABLE_NAME, null, value);
        db.close();
    }

    //get one contact
    public Contact getContact(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[] { Util.KEY_ID, Util.KEY_NAME, Util.KEY_PHONE_NAMBER},
                Util.KEY_ID+"=?",
                new String[] {String.valueOf(id)},
                null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();
        }

        Contact resContact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return resContact;

    }

    //get all contact
    public List<Contact> getAllContacts(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Contact> contactList = new ArrayList<>();

        //select all contact
        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll,null);

        //loop through our contact
        if(cursor.moveToFirst()){
            do{
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                //add contact object to our contact list
                contactList.add(contact);


            }while(cursor.moveToNext());
        }
        return contactList;
    }

    //update Contact
    public int updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.KEY_NAME, contact.getName());
        values.put(Util.KEY_PHONE_NAMBER, contact.getPhoneNumber());

        //update row
        return db.update(Util.TABLE_NAME,values,Util.KEY_ID+"=?",
                new String[] {String.valueOf(contact.getId())});

    }

}





