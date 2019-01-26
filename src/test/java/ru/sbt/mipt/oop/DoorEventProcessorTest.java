package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.eventProcessors.DoorEventProcessor;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
import static ru.sbt.mipt.oop.sensors.SensorEventType.*;

public class DoorEventProcessorTest {

    @Test
    public void testStateOpened(){
        Collection<Door> doorList = new ArrayList<>(Arrays.asList(new Door(true, "1")));
        SmartHome smartHome = new SmartHome();
        smartHome.addRoom(new Room(doorList,"kitchen"));
        SensorEvent sensorEvent = new SensorEvent(DOOR_OPEN, "1");

        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        doorEventProcessor.processEvent(smartHome, sensorEvent);

        for (Room room : smartHome.getRooms())
            for (Door door : room.getDoors())
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    boolean actual = true;
                    boolean expected = door.isOpen();
                    assertEquals(expected, actual);
                }
    }

    @Test
    public void testStateClosed(){
        Collection<Door> doorList = (Arrays.asList(new Door(false, "2")));
        SmartHome smartHome = new SmartHome();
        smartHome.addRoom(new Room(doorList,"kitchen"));
        SensorEvent sensorEvent = new SensorEvent(DOOR_CLOSED, "2");

        DoorEventProcessor doorEventProcessor = new DoorEventProcessor();
        doorEventProcessor.processEvent(smartHome, sensorEvent);

        for (Room room : smartHome.getRooms())
            for (Door door : room.getDoors())
                if (door.getId().equals(sensorEvent.getObjectId())) {
                    boolean actual = false;
                    boolean expected = door.isOpen();
                    assertEquals(expected, actual);
                }
    }

}