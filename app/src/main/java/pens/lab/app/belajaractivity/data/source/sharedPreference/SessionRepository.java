package pens.lab.app.belajaractivity.data.source.sharedPreference;

public interface SessionRepository<T> {
    public final String SHARED_PREFERENCE_NAME = "TodolistApp";

    T initialize(T sessionData);
    T getSessionData();
    void setSessionData(T sessionData);
    void destroy();
    void update(T sessionData);
}
