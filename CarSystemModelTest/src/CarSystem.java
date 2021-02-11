


public class CarSystem implements ICarAlarmSystem {

    boolean opened = true;
    boolean closed = false;
    boolean locked = false;
    boolean unlocked = true;
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
        unlocked = false;
    }

    @Override
    public void unlock() {
        unlocked = true;
        locked = false;
    }

    @Override
    public void close() {
        closed = true;
        opened = false;
    }

    @Override
    public void open() {
        opened = true;
        closed = false;
    }

    @Override
    public void tick() {

    }

}
