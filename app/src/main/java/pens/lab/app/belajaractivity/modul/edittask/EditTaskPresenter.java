package pens.lab.app.belajaractivity.modul.edittask;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.core.app.ShareCompat;

import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.data.source.local.TableHandler;
import pens.lab.app.belajaractivity.data.source.local.TaskHandler;

public class EditTaskPresenter implements EditTaskContract.Presenter{
    private final EditTaskContract.View view;
    private TableHandler taskHandler;
    private String idTask;
    private Task task;
    private Activity context;

    public EditTaskPresenter(EditTaskContract.View view, Activity context) {
        this.view = view;
        taskHandler = new TaskHandler(context);
        this.context = context;
    }

    @Override
    public void start() {
    }

    @Override
    public void saveData(final String title, final String description){
        Task newTask = new Task(idTask, title, description);
        taskHandler.update(newTask);
        //save new task
        //then go back to task list
        view.redirectToTaskList();
    }

    @Override
    public void loadData(String id) {
        //load data task by id
        //then send data to fragment
        idTask = id;
        task = (Task) taskHandler.selectById(id);
//        Task task = new Task("3", "title of taskIndex:"+id, "description of taskIndex:"+id);
        view.showData(task);
    }

    public void shareTask() {
        String message = task.getTitle() + " : " + task.getDescription();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(context)
                .setType(mimeType)
                .setChooserTitle(task.getTitle() + " - My Task")
                .setText(message)
                .startChooser();
    }

}
