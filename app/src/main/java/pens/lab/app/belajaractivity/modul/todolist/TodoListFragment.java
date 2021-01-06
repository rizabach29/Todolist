package pens.lab.app.belajaractivity.modul.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.modul.edittask.EditTaskActivity;
import pens.lab.app.belajaractivity.modul.newtask.NewTaskActivity;
import pens.lab.app.belajaractivity.utils.RecyclerViewAdapterTodolist;

public class TodoListFragment extends BaseFragment<TodoListActivity, TodoListContract.Presenter> implements TodoListContract.View {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton buttonAdd;
    private ArrayList<Task> data;

    public TodoListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_todolist, container, false);
        mPresenter = new TodoListPresenter(this, getContext());
        mPresenter.start();

        mRecyclerView = fragmentView.findViewById(R.id.recyclerViewTodolist);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        data = mPresenter.getDataSet();
        mAdapter = new RecyclerViewAdapterTodolist(data);
        mRecyclerView.setAdapter(mAdapter);
        setTitle("Todo List");

        buttonAdd = fragmentView.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoNewTask();
            }
        });

        ((RecyclerViewAdapterTodolist) mAdapter).setOnItemClickListener(new RecyclerViewAdapterTodolist.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                String id = data.get(position).getId();
                Log.d("BELAJAR ACTIVITY",">>>>>"+ position);
                editTask(id);
            }

            @Override
            public void onItemCheck(int position, View v) {
                String id = data.get(position).getId();
                Log.d("BELAJAR ACTIVITY", "Deleting task : " + id);
                deleteTask(id);
                data.remove(position);
                mRecyclerView.removeViewAt(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position,data.size());
            }
        });

        return fragmentView;
    }

    @Override
    public void setPresenter(TodoListContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void gotoNewTask() {
        Intent intent = new Intent(activity, NewTaskActivity.class);
        startActivity(intent);
    }

    @Override
    public void deleteTask(String id) {
        boolean result = mPresenter.deleteTask(id);
        if (result) {
            Toast.makeText(getActivity(), "Delete Task Success", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getActivity(), "Delete Task Failed", Toast.LENGTH_SHORT).show();
    }

    public void editTask(String id) {
        Intent intent = new Intent(activity, EditTaskActivity.class);
        intent.putExtra("TaskId", id);
        startActivity(intent);
    }
}
