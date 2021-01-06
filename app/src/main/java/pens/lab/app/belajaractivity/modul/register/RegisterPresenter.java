package pens.lab.app.belajaractivity.modul.register;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import pens.lab.app.belajaractivity.data.model.User;

public class RegisterPresenter implements RegisterContract.Presenter{
    private final RegisterContract.View view;
    private Context context;
    private FirebaseAuth mAuth;
    private final String TAG = "Register Presenter";

    public RegisterPresenter(RegisterContract.View view, Context context) {
        this.view = view;
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void performRegister(final String name, final String email, final String gender, final String password){
        //proses login
        //if login success call redirect to profile

        mAuth.createUserWithEmailAndPassword(email.toLowerCase(),password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User();
                            user.setName(name);
                            user.setEmail(email.toLowerCase());
                            user.setGender(gender);
                            user.setPassword(password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(context, "Authentication Success.",
                                                Toast.LENGTH_LONG).show();
                                        view.redirectToLogin();
                                    } else {
                                        Toast.makeText(context, "Authentication Failed.",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(context, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void start() {

    }
}
