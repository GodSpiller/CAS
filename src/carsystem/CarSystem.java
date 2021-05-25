package carsystem;
//implementation for the CAS UPPAAL model
public class CarSystem implements ICarAlarmSystem {
    public boolean closed;
    public boolean locked;
    public boolean flash;
    public boolean sound;
    public boolean armed;

    public int c;
    public int e;

    @Override
    public void lock() {
        locked = true;
    }

    @Override
    public void unlock() {
        locked = false;
    }

    @Override
    public void close() {
        closed = true;
    }

    @Override
    public void open() {
        closed = false;
    }

    @Override
    public void armedOn() {
        armed = true;
    }

    @Override
    public void armedOff() {
        armed = false;
    }

    @Override
    public void flashOn() {
        flash = true;
    }

    @Override
    public void flashOff() {
        flash = false;
    }

    @Override
    public void soundOn() {
        sound = true;
    }

    @Override
    public void soundOff() {
        sound = false;
    }

    @Override
    public void tick(int i) {
        c += i;
        e += i;
    }
}
