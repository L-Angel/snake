package com.langel.snake.action;


import com.langel.snake.SnakeResponse;
import com.langel.snake.action.support.DefaultHttpAction;
import com.langel.snake.action.support.NotFoundAction;
import com.langel.snake.annotation.SnakeAction;
import com.langel.snake.exception.SnakeRegisterException;
import com.langel.snake.http.RequestMethod;
import com.langel.snake.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionRegister {
    private static volatile Map<String, ActionWrapper> actionMap = new ConcurrentHashMap<String, ActionWrapper>();

    static {
        register("/", DefaultHttpAction.class, ActionSource.SNAKE_SYSTEM);
        register("null", NotFoundAction.class, ActionSource.SNAKE_SYSTEM);
    }

    private ActionRegister() {
    }

    public static void register(Class<? extends Action> action) {
        if (action.isAnnotationPresent(SnakeAction.class)) {
            register("*", action, ActionSource.SNAKE_CUSTOMIZE);
        } else {
            System.exit(-100);
            //new SnakeRegisterException("action: " + action.getName() + " is not annotated with snakeaction.");
        }

    }

    public static void register(String path, Action action, ActionSource actionSource) {
        Class<?> clazz = action.getClass();
        ActionWrapper actionWrapper;
        if (clazz.isAnnotationPresent(SnakeAction.class)) {
            SnakeAction snakeAction = clazz.getAnnotation(SnakeAction.class);
            if (!StringUtils.isNullOrEmpty(snakeAction.path())) {
                path = snakeAction.path();
            }
            actionWrapper = new ActionWrapper(action, snakeAction.method(), actionSource);
        } else {
            actionWrapper = new ActionWrapper(action, new RequestMethod[0], actionSource);
        }
        actionMap.put(path, actionWrapper);
    }

    public static void register(String path, Class<? extends Action> action) {
        register(path, action, ActionSource.SNAKE_CUSTOMIZE);
    }

    public static void register(String path, Class<? extends Action> action, ActionSource actionSource) {
        try {
            Action ac = action.newInstance();
            register(path, ac, actionSource);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
   /* public static Action getAction(String path) {
        if (actionMap.containsKey(path)) {
            return actionMap.get(path).action();
        }
        return null;

    }*/

    public static Action getAction(String path, RequestMethod method) {
        if (contains(path)) {
            ActionWrapper aw = actionMap.get(path);
            if (aw.methods().size() == 0
                    || (aw.methods().size() > 0 && aw.methods().contains(method))) {
                return actionMap.get(path).action();
            }
        }
        return notFoundAction();
    }

    public static boolean contains(String path) {
        return actionMap.containsKey(path);
    }

    public static Action notFoundAction() {
        return actionMap.get("null").action();
    }
}
