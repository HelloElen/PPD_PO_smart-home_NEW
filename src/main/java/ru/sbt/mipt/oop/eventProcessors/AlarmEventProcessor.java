package ru.sbt.mipt.oop.eventProcessors;

import ru.sbt.mipt.oop.eventProcessors.EventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.sensors.SensorEventType.ALARM_DEACTIVATE;
public class AlarmEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if(!isAlarmEvent(event)) return;
        if(event.getType() == ALARM_ACTIVATE) {
            smartHome.getAlarm().switchOn();
        }
        else {
            smartHome.getAlarm().switchOff(smartHome.getAlarm().getRightPassword());
        }
    }
    private boolean isAlarmEvent(SensorEvent event) {
        return event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE;
    }
}