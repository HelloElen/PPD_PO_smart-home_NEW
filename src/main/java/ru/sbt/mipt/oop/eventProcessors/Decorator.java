package ru.sbt.mipt.oop.eventProcessors;

import ru.sbt.mipt.oop.alarm.AlarmRing;
import ru.sbt.mipt.oop.alarm.AlarmStateEnum;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_DEACTIVATE;

public class Decorator implements EventProcessor {
    EventProcessor eventProcessor;

    public Decorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {

        if (smartHome.getAlarm().getState().equals(AlarmStateEnum.ON)) {
            smartHome.getAlarm().startRing();
        }

        eventProcessor.processEvent(smartHome, event);
    }
}
