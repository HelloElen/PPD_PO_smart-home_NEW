package ru.sbt.mipt.oop.alarm;


public interface AlarmState {
    void switchOn();
    void switchOff(String password);
    void enterPassword(String password);
    void startRing();
    AlarmStateEnum getState();}
