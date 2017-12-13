package ru.java.test;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class Injector {
    private static HashMap<Class<?>, Integer> state = new HashMap<>(); // 0 - не создавался до, 1 - сейчас создаем, 2 - уже создан
    private static HashMap<Class<?>, Object> readyClasses = new HashMap<>();

    /**
     * @param clazz        - Class that will be created
     * @param dependencies - array of classes to be able to create
     * @return new object describes of clazz
     * @throws InjectionCycleException          - if dependencies is cycle
     * @throws AmbiguousImplementationException - if found more that one dependence for one argument
     * @throws ImplementationNotFoundException  - if no dependence for constructor argument
     */

    public static Object initialize(@NotNull Class<?> clazz, @NotNull Class<?>[] dependencies) throws InjectionCycleException, AmbiguousImplementationException,
            ImplementationNotFoundException {
        Object ans = init(clazz, dependencies);
        state.clear();
        readyClasses.clear();
        return ans;
    }

    private static Object init(@NotNull Class<?> clazz, @NotNull Class<?>[] dependencies) throws InjectionCycleException, AmbiguousImplementationException,
            ImplementationNotFoundException {
        Constructor<?> constructor = clazz.getConstructors()[0];
        state.put(clazz, 1);
        Class<?>[] args = constructor.getParameterTypes();
        Object[] readyArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (state.getOrDefault(args[i], 0) == 1)
                throw new InjectionCycleException();
            if (state.getOrDefault(args[i], 0) == 2) {
                readyArgs[i] = readyClasses.get(args[i]);
                continue;
            }
            Class<?> neededClass = null;
            for (int j = 0; j < dependencies.length; j++) {
                if (args[i].isAssignableFrom(dependencies[j])) {
                    if (neededClass == null) {
                        neededClass = dependencies[j];
                        readyArgs[i] = init(dependencies[j], dependencies);
                    } else
                        throw new AmbiguousImplementationException();
                }
            }
            if (neededClass == null)
                throw new ImplementationNotFoundException();
        }
        Object answer = null;
        try {
            answer = constructor.newInstance(readyArgs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        state.put(clazz, 2);
        readyClasses.put(clazz, answer);
        return answer;
    }

    static public class InjectionCycleException extends Exception {
    }

    static public class ImplementationNotFoundException extends Exception {
    }

    static public class AmbiguousImplementationException extends Exception {
    }
}
