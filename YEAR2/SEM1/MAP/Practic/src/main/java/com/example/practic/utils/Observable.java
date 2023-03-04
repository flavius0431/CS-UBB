package com.example.practic.utils;

import com.example.practic.utils.events.Event;

public interface Observable  <E extends Event> {

    void addObserver(Observer<E> e);

    void removeObserver(Observer<E> e);

    void notifyObservers(E t);

}
