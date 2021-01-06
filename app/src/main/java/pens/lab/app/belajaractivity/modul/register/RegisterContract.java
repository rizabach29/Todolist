package pens.lab.app.belajaractivity.modul.register;

import pens.lab.app.belajaractivity.base.BasePresenter;
import pens.lab.app.belajaractivity.base.BaseView;


public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void performRegister(String name, String email, String gender, String password);
    }
}
