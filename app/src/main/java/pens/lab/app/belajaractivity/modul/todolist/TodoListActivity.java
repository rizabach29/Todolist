package pens.lab.app.belajaractivity.modul.todolist;

import android.view.View;

import pens.lab.app.belajaractivity.base.BaseFragmentHolderActivity;


public class TodoListActivity extends BaseFragmentHolderActivity {
    TodoListFragment todoListFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        todoListFragment = new TodoListFragment();
        setCurrentFragment(todoListFragment, false);

    }



}
