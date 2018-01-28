package com.langel.snake.action;

import com.langel.snake.http.RequestMethod;

import java.util.Arrays;
import java.util.Collection;

public class ActionWrapper {
    private ActionSource actionSource;

    private Action action;
    private Collection<RequestMethod> methods;

    public ActionWrapper(Action action, RequestMethod[] methods, ActionSource actionSource) {
        this.actionSource = actionSource;
        this.action = action;
        this.methods = Arrays.asList(methods);
    }


    public Action action() {
        return action;
    }

    public Collection<RequestMethod> methods() {
        return methods;
    }
}
