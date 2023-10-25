package com.example.map_toysocialnetwork.utils.observer;

import com.example.map_toysocialnetwork.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
