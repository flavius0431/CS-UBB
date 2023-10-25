package com.example.map_toysocialnetwork.utils.observer;

import com.example.map_toysocialnetwork.utils.events.Event;

public interface Observable <E extends Event> {

    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);


}
