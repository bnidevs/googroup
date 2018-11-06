package dvorak.app;

import android.content.ContentValues;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Storage {

    public void write(String usr, String msg){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sender", usr);
        cv.put("content", msg);

        db.insert("msgs", null, cv);

    }

    public void write(String msg){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("sender", "public");
        cv.put("content", msg);

        db.insert("msgs", null, cv);

    }

    public ArrayList<String> read(String usr){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT content FROM msgs WHERE sender = '" + usr + "'", null);

        ArrayList<String> rtrnArr = new ArrayList<String>();
        while(c.moveToNext()){
            rtrnArr.add(c.getString(0));
        }

        return rtrnArr;

    }

    public void erase(String usr, String msg){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM msgs WHERE sender = '" + usr + "' AND content = '" + msg + "'");

    }

    public void purge(){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getWritableDatabase();

        db.execSQL("DELETE FROM msgs");

    }

    public ArrayList<String> usrs(){

        SQLiteOpenHelper helper = open();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT sender FROM msgs", null);

        ArrayList<String> rtrnArr = new ArrayList<String>();
        while(c.moveToNext()){
            rtrnArr.add(c.getString(0));
        }

        return rtrnArr;

    }

    public SQLiteOpenHelper open(){

        SQLiteOpenHelper helper = new SQLiteOpenHelper(null, "msgs", null, 1, null) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE msgs (ID INTEGER PRIMARY KEY AUTOINCREMENT, sender TEXT, content TEXT)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP IF TABLE EXISTS msgs");
                onCreate(db);
            }
        };

        return helper;

    }
}
