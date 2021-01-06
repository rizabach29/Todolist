package pens.lab.app.belajaractivity.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "TODOLIST_DB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + DatabaseContract.TaskFeed.TABLE_NAME +
                " (" + DatabaseContract.TaskFeed.COLUMN_ID + " INTEGER PRIMARY KEY, " +
                DatabaseContract.TaskFeed.COLUMN_TITLE + " TEXT, " +
                DatabaseContract.TaskFeed.COLUMN_DESCRIPTION + " TEXT )";
        sqLiteDatabase.execSQL(CREATE_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
