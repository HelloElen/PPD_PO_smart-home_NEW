package ru.sbt.mipt.oop.remoteControl.commands;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.components.SmartHome;

public class ActivateAlarm implements RemoteCommand {
    private SmartHome smartHome;

    public ActivateAlarm (SmartHome smartHome) {
        this.smartHome = smartHome;
    }
    @Override
    public void execute() {
        smartHome.getAlarm().switchOn();
    }
}
