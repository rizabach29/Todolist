package pens.lab.app.belajaractivity.modul.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.base.BaseFragment;
import pens.lab.app.belajaractivity.data.model.User;
import pens.lab.app.belajaractivity.modul.login.LoginActivity;
import pens.lab.app.belajaractivity.modul.todolist.TodoListActivity;

import static com.basgeekball.awesomevalidation.ValidationStyle.TEXT_INPUT_LAYOUT;

public class RegisterFragment extends BaseFragment<RegisterActivity, RegisterContract.Presenter> implements RegisterContract.View {

    EditText etEmail;
    EditText etName;
    EditText etPassword;
    EditText etConfirmPassword;
    RadioButton rbMale;
    RadioButton rbFemale;
    Button btnRegister;
    ProgressBar progressBar;

    public RegisterFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        mPresenter = new RegisterPresenter(this, getContext());
        mPresenter.start();

        etName = fragmentView.findViewById(R.id.et_name);
        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        etConfirmPassword = fragmentView.findViewById(R.id.et_confirm_password);
        rbFemale = fragmentView.findViewById(R.id.rb_female);
        rbMale = fragmentView.findViewById(R.id.rb_male);
        btnRegister = fragmentView.findViewById(R.id.bt_register);
        progressBar = fragmentView.findViewById(R.id.progressBar2);

        progressBar.setVisibility(View.INVISIBLE);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtRegisterClick();
            }
        });

        setTitle("Register");

        return fragmentView;
    }

    public void setBtRegisterClick(){
        if (validateRegistrationForm()) {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();
            String gender = rbMale.isChecked() ? User.GENDER_MALE : User.GENDER_FEMALE;

//            if (password != confirmPassword) {
//                Toast.makeText(getContext(), "Password and Confirmation Password didn't match ", Toast.LENGTH_LONG).show();
//                return;
//            }

            progressBar.setVisibility(View.VISIBLE);
            mPresenter.performRegister(name, email, gender, password);
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToLogin() {
        progressBar.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }

    private boolean validateRegistrationForm() {
        AwesomeValidation formValidation = new AwesomeValidation(TEXT_INPUT_LAYOUT);
        formValidation.addValidation(activity, R.id.til_email, Patterns.EMAIL_ADDRESS,
                R.string.validation_email_must_be_valid);
        formValidation.addValidation(activity, R.id.til_email, RegexTemplate.NOT_EMPTY,
                R.string.validation_email_should_not_empty);
        formValidation.addValidation(activity, R.id.til_name, RegexTemplate.NOT_EMPTY,
                R.string.validation_name_should_not_empty);
        formValidation.addValidation(activity, R.id.til_password, RegexTemplate.NOT_EMPTY,
                R.string.validation_password_should_not_empty);
        formValidation.addValidation(activity, R.id.til_confirmation_password, RegexTemplate.NOT_EMPTY,
                R.string.validation_confirm_password_should_not_empty);
        return formValidation.validate();
    }

}
