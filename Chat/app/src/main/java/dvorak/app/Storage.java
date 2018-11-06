package dvorak.app;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Storage {

    public void write(String msg){

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

        SQLiteDatabase db = helper.getWritableDatabase();

    }

}
