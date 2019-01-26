package ru.sbt.mipt.oop.observers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.EventProcessor;

public interface EventsManager {
    void runEventsCycle(SmartHome smartHome);
    void addEventProcessor(EventProcessor eventProcessor);
}
