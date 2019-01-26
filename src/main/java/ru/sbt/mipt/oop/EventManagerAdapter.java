package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.EventProcessor;
import ru.sbt.mipt.oop.observers.EventsManager;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import java.util.ArrayList;
import java.util.Collection;

public class EventManagerAdapter implements EventsManager {

    private SensorEventsManager manager;
    private final Collection<EventProcessor> eventProcessors = new ArrayList<>();

    public EventManagerAdapter(SensorEventsManager sensorEventsManager) {
       this.manager = sensorEventsManager;
    }

    @Override
    public void addEventProcessor(EventProcessor eventProcessor) {
        eventProcessors.add(eventProcessor);
    }

    private SensorEvent adapter(CCSensorEvent cCSensorEvent) {
        String id = cCSensorEvent.getObjectId();
        String type = cCSensorEvent.getEventType();
        switch (type) {
            case ("LightIsOn"):
                return new SensorEvent(SensorEventType.LIGHT_ON, id);
            case ("LightIsOff"):
                return new SensorEvent(SensorEventType.LIGHT_OFF, id);
            case ("DoorIsOpen"):
                return new SensorEvent(SensorEventType.DOOR_OPEN, id);
            case ("DoorIsClosed"):
                return new SensorEvent(SensorEventType.DOOR_CLOSED, id);
            case ("DoorIsUnlocked"):
                return new SensorEvent(SensorEventType.ALARM_DEACTIVATE, id);
            case ("DoorIsLocked"):
                return new SensorEvent(SensorEventType.ALARM_ACTIVATE, id);
            default:
                return null;
        }
    }

    @Override
    public void runEventsCycle(SmartHome smartHome) {
        manager.registerEventHandler(event -> {
            System.out.println("Event type [" + event.getEventType() + "] from object with id=" + event.getObjectId() + "]");
            SensorEvent sensorEvent = adapter(event);
            for (EventProcessor eventProcessor: eventProcessors) {
                eventProcessor.processEvent(smartHome, sensorEvent);
            }
        });

        manager.start();
    }

}
