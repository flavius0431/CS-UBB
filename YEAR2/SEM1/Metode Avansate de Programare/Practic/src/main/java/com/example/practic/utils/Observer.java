package com.example.practic.utils;

import com.example.practic.utils.events.Event;

public interface Observer <E extends Event> {
    void update(E e);
}
