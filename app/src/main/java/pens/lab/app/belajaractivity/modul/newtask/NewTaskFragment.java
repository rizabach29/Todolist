package pens.lab.app.belajaractivity.modul.newtask;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.modul.todolist.TodoListActivity;

public class NewTaskFragment extends BaseFragment<NewTaskActivity, NewTaskContract.Presenter> implements NewTaskContract.View {

    EditText etTaskTitle;
    EditText etTaskDescription;
    Button btnSave, btnShare;


    public NewTaskFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_task, container, false);
        mPresenter = new NewTaskPresenter(this, getActivity());
        mPresenter.start();

        etTaskTitle = fragmentView.findViewById(R.id.etTaskTitle);
        etTaskDescription = fragmentView.findViewById(R.id.etTaskDescription);
        btnSave = fragmentView.findViewById(R.id.btnSave);
        btnShare = fragmentView.findViewById(R.id.btnShare);
        btnShare.setVisibility(View.GONE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtSaveClick();
            }
        });

        setTitle("Add New Task");

        return fragmentView;
    }

    public void setBtSaveClick(){
        String title = etTaskTitle.getText().toString();
        String description = etTaskDescription.getText().toString();
        if(title != null && description != null)
            mPresenter.saveData(title,description);
        else
            Toast.makeText(getActivity(), "Must Fill All the Column", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(NewTaskContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToTaskList() {
            Intent intent = new Intent(activity, TodoListActivity.class);
            startActivity(intent);
            activity.finish();
    }


}
