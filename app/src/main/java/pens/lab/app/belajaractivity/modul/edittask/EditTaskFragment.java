package pens.lab.app.belajaractivity.modul.edittask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.modul.todolist.TodoListActivity;

public class EditTaskFragment extends BaseFragment<EditTaskActivity, EditTaskContract.Presenter> implements EditTaskContract.View {

    private EditText etTaskTitle;
    private EditText etTaskDescription;
    private Button btnSave, btnShare;
    private String id;

    public EditTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_task, container, false);
        mPresenter = new EditTaskPresenter(this, getActivity());
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.etTaskTitle);
        etTaskDescription = fragmentView.findViewById(R.id.etTaskDescription);
        btnSave = fragmentView.findViewById(R.id.btnSave);
        btnShare = fragmentView.findViewById(R.id.btnShare);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShareTask();
            }
        });

        setTitle("Edit Task");
        mPresenter.loadData(this.id);

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        mPresenter.saveData(title,description);
    }

    @Override
    public void setPresenter(EditTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }

    @Override
    public void showData(Task task) {
        this.etTaskTitle.setText(task.getTitle());
        this.etTaskDescription.setText(task.getDescription());
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }


    public void setShareTask() {
        mPresenter.shareTask();
    }
}
