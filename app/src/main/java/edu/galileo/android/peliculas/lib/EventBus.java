package edu.galileo.android.peliculas.lib;

/**
 * Created by Luis.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
