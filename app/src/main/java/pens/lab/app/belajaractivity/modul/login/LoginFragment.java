package pens.lab.app.belajaractivity.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.annotation.Nullable;
import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.data.source.sharedPreference.UserSessionRepository;
import pens.lab.app.belajaractivity.modul.register.RegisterActivity;
import pens.lab.app.belajaractivity.modul.todolist.TodoListActivity;

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    TextView tvRegister;

    public LoginFragment() {
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()), getContext());
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.bt_login);
        tvRegister = fragmentView.findViewById(R.id.registerBtn);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtRegisterClick();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        setTitle("Login");

        return fragmentView;
    }

    public void setBtRegisterClick() {
        Intent intent = new Intent(activity, RegisterActivity.class);
        startActivity(intent);
        activity.finish();
    }

    public void setBtLoginClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        mPresenter.performLogin(email,password);
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToHome() {
        Intent intent = new Intent(activity, TodoListActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
