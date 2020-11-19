package pens.lab.app.belajaractivity.modul.login;

import android.content.Context;

import pens.lab.app.belajaractivity.data.model.User;
import pens.lab.app.belajaractivity.data.source.sharedPreference.SessionRepository;

/**
 * Created by fahrul on 13/03/19.
 */

public class LoginPresenter implements LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {
        this.view = view;
        this.sessionRepository = sessionRepository;
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
        User loggedUser = new User(email, "HaloTampan");
        sessionRepository.setSessionData(loggedUser);

        view.redirectToHome();
    }

}
