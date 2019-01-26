package ru.sbt.mipt.oop.eventProcessors;

import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
