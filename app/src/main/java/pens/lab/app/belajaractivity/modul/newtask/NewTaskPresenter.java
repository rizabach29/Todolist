package pens.lab.app.belajaractivity.modul.newtask;

import android.content.Context;

import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.data.source.local.TaskHandler;

/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;
    private TaskHandler taskHandler;

    public NewTaskPresenter(NewTaskContract.View view, Context context) {
        this.view = view;
        taskHandler = new TaskHandler(context);
    }

    @Override
    public void start() {}

    @Override
    public void saveData(final String title, final String description){
        Task newTask = new Task("-1" ,title, description);
        taskHandler.create(newTask);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

}
