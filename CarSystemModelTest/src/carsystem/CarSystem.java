package carsystem;

public class CarSystem implements ICarAlarmSystem {

    boolean closed = false;
    boolean locked = false;
    boolean flash = false;
    boolean sound = false;
    boolean armed = false;

    int c = 0;
    int g = 0;
    int e = 0;
    int d = 0;
    int f = 0;


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
    public void tick(int i) {
        c += i;
        d += i;
        e += i;
        f += i;
        g += i;
    }

    @Override
    public void wait20seconds() {
        tick(20);
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

}
