package ru.sbt.mipt.oop.observers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventProvider;

import java.util.ArrayList;
import java.util.Collection;

public class HomeEventObserver implements EventsManager {

    private static Collection<EventProcessor> eventProcessors = null;
    protected SmartHome smartHome;
    private SensorEventProvider sensorEventProvider;

    public HomeEventObserver(SmartHome smartHome, SensorEventProvider sensorEventProvider){
        this.smartHome = smartHome;
        this.sensorEventProvider = sensorEventProvider;
    }

    @Override
    public void runEventsCycle(SmartHome smartHome) {
        SensorEvent event = SensorEventProvider.getNextSensorEvent();
          while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, event);
            }
            event = SensorEventProvider.getNextSensorEvent();
        }
    }

    @Override
    public void addEventProcessor(EventProcessor eventProcessor) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.add(eventProcessor);
    }

    public void addAllEventProcessors( Collection<EventProcessor> allEventProcessors) {
        if (eventProcessors == null) {
            eventProcessors = new ArrayList<>();
        }
        eventProcessors.addAll(allEventProcessors);
    }
}