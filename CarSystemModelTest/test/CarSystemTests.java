import carsystem.CarSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarSystemTests{


@Test
void testcase000(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

cs.flashOn();

assertTrue(cs.e <= 0);
assertTrue(cs.flash);

cs.soundOn();
assertTrue(cs.sound);

assertTrue(cs.e<=30);
assertTrue(cs.sound);

}

@Test
void testcase001(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

cs.flashOn();

assertTrue(cs.e <= 0);
assertTrue(cs.flash);

}

@Test
void testcase002(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

}

@Test
void testcase003(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

}

@Test
void testcase004(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.unlock();

assertTrue(cs.closed);
assertFalse(cs.locked);

}

@Test
void testcase005(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

cs.flashOn();

assertTrue(cs.e <= 0);
assertTrue(cs.flash);

cs.soundOn();
assertTrue(cs.sound);

assertTrue(cs.e<=30);
assertTrue(cs.sound);

assertTrue(cs.e < 30);
cs.g = 0;
cs.unlock();

assertTrue(cs.g <= 0);
assertFalse(cs.locked);

}

@Test
void testcase006(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertFalse(cs.closed);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

cs.flashOn();

assertTrue(cs.e <= 0);
assertTrue(cs.flash);

cs.soundOn();
assertTrue(cs.sound);

assertTrue(cs.e<=30);
assertTrue(cs.sound);

assertTrue(cs.e < 30);
cs.g = 0;
cs.unlock();

assertTrue(cs.g <= 0);
assertFalse(cs.locked);

cs.soundOff();

assertTrue(cs.g <= 0);
assertFalse(cs.sound);

}

@Test
void testcase007(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

}

@Test
void testcase008(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

}

@Test
void testcase009(){
CarSystem cs = new CarSystem();


cs.lock();

assertTrue(cs.locked);
assertFalse(cs.closed);

}

@Test
void testcase010(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

}
}