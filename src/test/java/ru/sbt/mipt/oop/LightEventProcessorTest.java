package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.LightsEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.*;
import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class LightEventProcessorTest {
    @Test
    public void testStateOn() {
        Collection<Door> doorList = new ArrayList<>(Arrays.asList(new Door(false, "1")));
        Collection<Light> lightsList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Light light = new Light(String.valueOf(i), true);
            lightsList.add(light);
        }

        SmartHome smartHome = new SmartHome(new ArrayList<>(Arrays.asList(new Room(lightsList, doorList, "kitchen"))));
        SensorEvent sensorEvent = new SensorEvent(LIGHT_ON, "1");

        LightsEventProcessor lightsEventProcessor = new LightsEventProcessor();
        lightsEventProcessor.processEvent(smartHome, sensorEvent);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    assertTrue(light.isOn());
                }
            }
        }
    }

    @Test
    public void testStateOff() {
        Collection<Door> doorList = new ArrayList<>(Arrays.asList(new Door(false, "1")));
        Collection<Light> lightsList = new ArrayList();
        for (int i = 1; i <= 5; i++) {
            Light light = new Light(String.valueOf(i), true);
            lightsList.add(light);
        }

        SmartHome smartHome = new SmartHome(new ArrayList<>(Arrays.asList(new Room(lightsList, doorList, "kitchen"))));
        SensorEvent sensorEvent = new SensorEvent(LIGHT_OFF, "1");

        LightsEventProcessor lightsEventProcessor = new LightsEventProcessor();
        lightsEventProcessor.processEvent(smartHome, sensorEvent);

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(sensorEvent.getObjectId())) {
                    assertFalse(light.isOn());
                }
            }
        }
    }
}