package carsystem;

public interface ICarAlarmSystem {

	void lock();
	void unlock();
	void close();
	void open();
	void tick(int i);
	void armedOn();
	void armedOff();
	void flashOn();
	void flashOff();
	void soundOn();
	void soundOff();
}


