package ru.sbt.mipt.oop.eventProcessors;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_OFF;

public class LightsEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) return;

        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        changeLightState(light, true, " was turned on.");
                    } else {
                        changeLightState(light, false, " was turned off.");
                    }
                }
            }
        });
    }

    private void changeLightState(Light light, boolean state, String text) {
        light.setOn(state);
        System.out.println("Light " + light.getId() + " " + text);
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}
