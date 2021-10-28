package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "todoListDataBase";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_todolist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_MESSAGE = "message";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_MESSAGE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNote(String title, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_MESSAGE, message);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            Toast.makeText(context, "Failed to add note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllNotes(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String rowId, String title, String message){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_MESSAGE, message);

        long result = db.update(TABLE_NAME, contentValues, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to update note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note update successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String rowId){
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete(TABLE_NAME, "id=?", new String[]{rowId});
        if (result == -1){
            Toast.makeText(context, "Failed to delete note", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Note deleted successfully!", Toast.LENGTH_SHORT).show();
        }
    }
}
