package pens.lab.app.belajaractivity.modul.todolist;

import java.util.ArrayList;

import pens.lab.app.belajaractivity.base.BasePresenter;
import pens.lab.app.belajaractivity.base.BaseView;
import pens.lab.app.belajaractivity.data.model.Task;

public interface TodoListContract {
    interface View extends BaseView<Presenter> {
        void gotoNewTask();
        void deleteTask(String id);
        void editTask(String id);
    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getDataSet();
        boolean deleteTask(String id);
    }
}
