package pens.lab.app.belajaractivity.modul.register;

import android.view.View;

import pens.lab.app.belajaractivity.base.BaseFragmentHolderActivity;


public class RegisterActivity extends BaseFragmentHolderActivity {
    RegisterFragment registerFragment;
    private final int UPDATE_REQUEST = 2019;

    @Override
    protected void initializeFragment() {
        initializeView();

        btBack.setVisibility(View.GONE);
        btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        ivIcon.setVisibility(View.VISIBLE);

        registerFragment = new RegisterFragment();
        setCurrentFragment(registerFragment, false);

    }




}
