package com.example.application.views.login;

import java.util.ArrayList;
import java.util.List;

public class EventBus {
    // Singleton instance
    private static final EventBus INSTANCE = new EventBus();

    private final List<LoginSuccessListener> loginSuccessListeners = new ArrayList<>();

    // Private constructor to prevent instantiation from other classes
    private EventBus() {
    }

    // Get the singleton instance
    public static EventBus getInstance() {
        return INSTANCE;
    }

    public void addLoginSuccessListener(LoginSuccessListener listener) {
        loginSuccessListeners.add(listener);
    }

    public void fireLoginSuccessEvent(LoginSuccessEvent event) {
        for (LoginSuccessListener listener : loginSuccessListeners) {
            listener.onLoginSuccess(event);
        }
    }

    public interface LoginSuccessListener {
        void onLoginSuccess(LoginSuccessEvent event);
    }
}
