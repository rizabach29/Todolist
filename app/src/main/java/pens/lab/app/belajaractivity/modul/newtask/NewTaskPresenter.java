package pens.lab.app.belajaractivity.modul.newtask;

import pens.lab.app.belajaractivity.data.model.Task;

/**
 * Created by fahrul on 13/03/19.
 */

public class NewTaskPresenter implements NewTaskContract.Presenter{
    private final NewTaskContract.View view;

    public NewTaskPresenter(NewTaskContract.View view) {
        this.view = view;
    }

    @Override
    public void start() {}

    @Override
    public void saveData(final String title, final String description){
        Task newTask = new Task("3", title, description);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

}
