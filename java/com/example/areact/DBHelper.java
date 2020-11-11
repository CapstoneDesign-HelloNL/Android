package com.example.areact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static volatile DBHelper instance;
    private static final String DB_NAME = "testDB";
    private static final int DB_VERSION=4;
    private SQLiteDatabase db = null;

    private DBHelper(Context context) {
        super(context, DB_NAME,null,DB_VERSION);
        if(db==null) {
            db = getWritableDatabase();
        }
    }

    public static DBHelper getInstance(Context context) {
        if(instance == null) {
            synchronized (DBHelper.class) {
                if(instance==null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DBContract.SQL_CREATE_TB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DBContract.SQL_DROP_TB);
    }

    public void insert() {
        db = getWritableDatabase();
        db.execSQL(DBContract.SQL_INSERT +"("+'"'+'"'+")");
        db.close();
    }

    public void delete(String title) {
        db = getWritableDatabase();
        db.execSQL(DBContract.SQL_DELETE+'"'+title+'"');
        db.close();
    }

    public ArrayList<FeedPost> getAll() {
        final ArrayList list = new ArrayList();
        db = getWritableDatabase();
        Cursor cursor;
        cursor = db.rawQuery(DBContract.SQL_SELECT, null);
        String str = null;

        while(cursor.moveToNext()) {
            str += cursor.getString(0) +"\r\n";
        }

        //Todo newTodo...
        //list.add();
        db.close();

        return list;
    }
}
