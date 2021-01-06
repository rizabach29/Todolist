package pens.lab.app.belajaractivity.modul.todolist;

import android.content.Context;

import java.util.ArrayList;

import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.data.source.local.TableHandler;
import pens.lab.app.belajaractivity.data.source.local.TaskHandler;

public class TodoListPresenter implements TodoListContract.Presenter{
    private final TodoListContract.View view;
    private TableHandler tableHandler;

    public TodoListPresenter(TodoListContract.View view, Context context) {
        this.view = view;
        this.tableHandler = new TaskHandler(context);
    }
    @Override
    public void start() {}

    @Override
    public ArrayList<Task> getDataSet() {
        return (ArrayList<Task>) tableHandler.selectAll();
    }

    @Override
    public boolean deleteTask(String id) {
        return tableHandler.delete(id);
    }
}
