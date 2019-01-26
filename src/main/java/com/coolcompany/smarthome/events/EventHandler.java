package com.coolcompany.smarthome.events;

import com.coolcompany.smarthome.events.CCSensorEvent;

public interface EventHandler {
    void handleEvent(CCSensorEvent event);
}