package ru.sbt.mipt.oop.loaders;

import ru.sbt.mipt.oop.eventProcessors.*;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.observers.EventsManager;

public class EventsLoader {
    public static void runEvents (SmartHome smartHome, EventsManager eventsManager) {
        eventsManager.addEventProcessor(new Decorator(new LightsEventProcessor()));
        eventsManager.addEventProcessor(new Decorator(new DoorEventProcessor()));
        eventsManager.addEventProcessor(new Decorator(new AlarmEventProcessor()));
        eventsManager.addEventProcessor(new Decorator(new HallDoorEventProcessor()));
        eventsManager.runEventsCycle(smartHome);
    }
}
