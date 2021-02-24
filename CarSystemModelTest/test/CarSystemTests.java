import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarSystemTests{

@BeforeEach
 void setup(){
 CarSystem cs = new CarSystem();
}

@Test
void testcase000(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

cs.armedOff();

assertTrue(cs.e <= 0);
assertFalse(cs.armed);

}

@Test
void testcase003(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

}

@Test
void testcase004(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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

cs.tick(30);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

cs.tick(270);
assertTrue(cs.e == 300);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

cs.flashOff();

assertFalse(cs.flash);

cs.f = 0;
cs.close();

assertTrue(cs.f == 0);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertTrue(cs.locked);
assertFalse(cs.unlocked);

}

@Test
void testcase005(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.unlock();

assertTrue(cs.closed);
assertTrue(cs.unlocked);

}

@Test
void testcase006(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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
assertTrue(cs.unlocked);

}

@Test
void testcase007(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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
assertTrue(cs.unlocked);

cs.soundOff();

assertTrue(cs.g <= 0);
assertFalse(cs.sound);

}

@Test
void testcase008(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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

cs.tick(30);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

cs.tick(270);
assertTrue(cs.e == 300);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

}

@Test
void testcase009(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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

cs.tick(30);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

}

@Test
void testcase010(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

}

@Test
void testcase011(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

}

@Test
void testcase012(){
CarSystem cs = new CarSystem();


cs.lock();

assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);

}

@Test
void testcase013(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

}

@Test
void testcase014(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);

cs.lock();
cs.c = 0;

assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

cs.tick(20);
cs.armedOn();

assertTrue(cs.armed);

cs.open();
cs.e = 0;

assertTrue(cs.e <= 0);
assertTrue(cs.opened);

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

cs.tick(30);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

cs.tick(270);
assertTrue(cs.e == 300);
cs.soundOff();

assertTrue(cs.e <= 300);
assertFalse(cs.sound);

cs.flashOff();

assertFalse(cs.flash);

}
}