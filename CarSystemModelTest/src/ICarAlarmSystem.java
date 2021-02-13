
public interface ICarAlarmSystem {

	void lock();
	void unlock();
	void close();
	void open();
	void tick(int i);
	void wait20seconds();
	void armedOn();
	void armedOff();
	void flashOn();
	void flashOff();
	void soundOn();
	void soundOff();
}


