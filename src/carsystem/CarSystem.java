package carsystem;
public class CarSystem implements ICarAlarmSystem {
    public boolean closed = false;
    public boolean locked = false;
    public boolean flash = false;
    public boolean sound = false;
    public boolean armed = false;
    public int c = 0;
    public int g = 0;
    public int e = 0;
    public int d = 0;
    public int f = 0;
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
