package ru.sbt.mipt.oop;
import org.junit.Test;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmStateEnum;
import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventType;

import static org.junit.Assert.*;


public class AlarmTest {

    @Test
    public void testStateOn(){
        Alarm alarm = new Alarm("1239");
        alarm.switchOn();
        AlarmStateEnum actual = alarm.getState();
        AlarmStateEnum expected = AlarmStateEnum.ON;
        assertEquals(expected, actual);
    }

    @Test
    public void testStateOff(){
        Alarm alarm = new Alarm("1239");
        AlarmStateEnum actual = alarm.getState();
        AlarmStateEnum expected = AlarmStateEnum.OFF;
        assertEquals(expected, actual);
    }

    @Test
    public void testStateRing(){
        Alarm alarm = new Alarm("1239");
        alarm.startRing();
        AlarmStateEnum actual = alarm.getState();
        AlarmStateEnum expected = AlarmStateEnum.RING;
        assertEquals(expected, actual);
    }

    @Test
    public void testStartRing(){
        Alarm alarm = new Alarm("1239");
        alarm.switchOn();
        alarm.switchOff("1238");
        AlarmStateEnum actual = alarm.getState();
        AlarmStateEnum expected = AlarmStateEnum.RING;
        assertEquals(expected, actual);
    }

    @Test
    public void testNotStartRing(){
        Alarm alarm = new Alarm("1239");
        alarm.switchOn();
        alarm.switchOff("1239");
        AlarmStateEnum actual = alarm.getState();
        AlarmStateEnum expected = AlarmStateEnum.OFF;
        assertEquals(expected, actual);
    }
}
