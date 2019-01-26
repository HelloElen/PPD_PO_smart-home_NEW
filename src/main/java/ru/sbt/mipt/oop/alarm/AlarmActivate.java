package ru.sbt.mipt.oop.alarm;


public class AlarmActivate implements AlarmState {
    private final Alarm alarm;

    public AlarmActivate(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void switchOn() {}

    @Override
    public void switchOff(String password) {
        System.out.println("Password: ");
        System.out.println("****");
        this.enterPassword(password);
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
        return AlarmStateEnum.ON;
    }
}
