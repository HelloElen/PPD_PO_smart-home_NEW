package ru.sbt.mipt.oop.alarm;


public class AlarmDeactivate implements AlarmState {
    private final Alarm alarm;

    public AlarmDeactivate(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void switchOn() {
        alarm.setState(new AlarmActivate(alarm));
    }

    @Override
    public void switchOff(String password) {

    }

    @Override
    public void enterPassword(String password) {
        if (alarm.getRightPassword().equals(password)) {
            alarm.setState(new AlarmDeactivate(alarm));
            System.out.println("It is a right password. Alarm is deactivated.");
        }
        else {
            alarm.setState(new AlarmRing(alarm));
            System.out.println("It is a wrong password.");
            alarm.startRing();
        }
    }

    @Override
    public void startRing() {
        alarm.setState(new AlarmRing(alarm));
        System.out.println("Sending sms");
    }

    @Override
    public AlarmStateEnum getState() {
        return AlarmStateEnum.OFF;
    }
}
