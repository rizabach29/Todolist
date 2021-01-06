package pens.lab.app.belajaractivity.modul.login;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pens.lab.app.belajaractivity.data.model.MyUser;
import pens.lab.app.belajaractivity.data.model.User;
import pens.lab.app.belajaractivity.data.source.sharedPreference.SessionRepository;

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private DatabaseReference dbReference;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository, Context context) {
        this.view = view;
        this.sessionRepository = sessionRepository;
        this.context = context;
        this.mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void start() {
        if (sessionRepository.getSessionData() != null) {
            view.redirectToHome();
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login
        //if login success call redirect to profile
        mAuth.signInWithEmailAndPassword(email.toLowerCase(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            dbReference = FirebaseDatabase.getInstance().getReference("Users");
                            String userID = firebaseUser.getUid();

                            dbReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    User user = dataSnapshot.getValue(User.class);
                                    sessionRepository.setSessionData(user);
                                    view.redirectToHome();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast.makeText(context, "Failed Login, Please check your credentials", Toast.LENGTH_LONG).show();
                                }
                            });
                        } else {
                            Toast.makeText(context, "Failed Login, Please check your credentials", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}
