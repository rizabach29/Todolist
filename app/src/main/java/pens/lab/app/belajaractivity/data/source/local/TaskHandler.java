package pens.lab.app.belajaractivity.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import pens.lab.app.belajaractivity.data.model.Task;

public class TaskHandler  implements TableHandler<Task, String> {
    private DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public TaskHandler(Context context) {
        this.databaseHelper = new DatabaseHelper(context);
        this.sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public boolean create(Task task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TaskFeed.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.TaskFeed.COLUMN_DESCRIPTION, task.getDescription());

        long insert = this.sqLiteDatabase.insert(DatabaseContract.TaskFeed.TABLE_NAME, null, values);
        sqLiteDatabase.close();

        return insert == -1 ? false : true;
    }

    @Override
    public boolean delete(String id) {
//        String clause = "DELETE FROM " + DatabaseContract.TaskFeed.TABLE_NAME + " WHERE " + DatabaseContract.TaskFeed.COLUMN_ID + "=" + id;
        String clause = DatabaseContract.TaskFeed.COLUMN_ID + "=" + id;

        SQLiteDatabase sqLite = databaseHelper.getWritableDatabase();
        boolean del = this.sqLiteDatabase.delete(DatabaseContract.TaskFeed.TABLE_NAME, clause, null) > 0;
        sqLite.close();
//        Cursor cursor = sqLite.rawQuery(clause, null);

        return del;
    }

    @Override
    public boolean update(Task task) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TaskFeed.COLUMN_TITLE, task.getTitle());
        values.put(DatabaseContract.TaskFeed.COLUMN_DESCRIPTION, task.getDescription());

        SQLiteDatabase sqLite = databaseHelper.getWritableDatabase();
        String clause = DatabaseContract.TaskFeed.COLUMN_ID + "=" + task.getId();
        int update = sqLite.update(DatabaseContract.TaskFeed.TABLE_NAME, values, clause, null);
        sqLiteDatabase.close();

        return update == -1 ? false : true;
    }

    @Override
    public List<Task> selectAll() {
        List<Task> listOfTask = new ArrayList<>();
        String clause = "SELECT * FROM " + DatabaseContract.TaskFeed.TABLE_NAME;

        Cursor cursor = this.sqLiteDatabase.rawQuery(clause, null);

        if (cursor.moveToFirst())
            while (cursor.moveToNext()) {
                String taskId = cursor.getString(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                listOfTask.add(new Task(taskId, title, description));
            }

        cursor.close();
        sqLiteDatabase.close();
        return listOfTask;
    }

    @Override
    public Task selectById(String selectId) {
        Task result;
        String clause = "SELECT * FROM " + DatabaseContract.TaskFeed.TABLE_NAME + " WHERE " +
                DatabaseContract.TaskFeed.COLUMN_ID + " = " + selectId;

        Cursor cursor = this.sqLiteDatabase.rawQuery(clause, null);

        if(cursor.moveToFirst()) {
            String id = cursor.getString(0);
            String title = cursor.getString(1);
            String description = cursor.getString(2);

            result = new Task(id, title, description);
        } else
            result = null;

        cursor.close();
        sqLiteDatabase.close();
        return result;
    }
}
