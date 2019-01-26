package ru.sbt.mipt.oop.alarm;


public class Alarm {
    private AlarmState state;
    private final String rightPassword;

    public Alarm(String password) {
        this.rightPassword = password;
        state = new AlarmDeactivate(this);
    }

    public void switchOn(){
        state.switchOn();
    }

    public void switchOff(String password) {
        state.switchOff(password);
    }

    public void enterPassword(String password) {
        state.enterPassword(password);
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmStateEnum getState() {
        return state.getState();
    }

    public void startRing() {
        state.startRing();
    }

    public String getRightPassword() {
        return rightPassword;
    }
}