import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import carsystem.CarSystem;class CarSystemTests{


@Test
void testcase000(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
cs.tick(30);
cs.soundOff();
assertFalse(cs.sound);
assertTrue(cs.e == 30);
cs.tick(270);
assertTrue(cs.e == 300);
cs.soundOff();
assertFalse(cs.sound);
assertTrue(cs.e == 300);
}

@Test
void testcase001(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
cs.tick(30);
cs.soundOff();
assertFalse(cs.sound);
assertTrue(cs.e == 30);
fail();
assertTrue(cs.e == 300);
}

@Test
void testcase002(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
cs.tick(30);
cs.soundOff();
assertFalse(cs.sound);
assertTrue(cs.e == 30);
fail();
assertTrue(cs.e == 300);
}

@Test
void testcase003(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
cs.tick(30);
cs.soundOff();
assertFalse(cs.sound);
assertTrue(cs.e == 30);
}

@Test
void testcase004(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.e == 30);
}

@Test
void testcase005(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.e == 30);
}

@Test
void testcase006(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.g == 0);
}

@Test
void testcase007(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
assertTrue(cs.e < 30);
cs.g = 0;
cs.unlock();
assertFalse(cs.locked);
assertTrue(cs.g == 0);
}

@Test
void testcase008(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.g == 0);
}

@Test
void testcase009(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.g == 0);
}

@Test
void testcase010(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
fail();
assertTrue(cs.g == 0);
}

@Test
void testcase011(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
cs.e = 0;
cs.open();
assertFalse(cs.closed);
assertTrue(cs.e == 0);
cs.armedOff();
assertFalse(cs.armed);
assertTrue(cs.e == 0);
cs.flashOn();
assertTrue(cs.flash);
cs.soundOn();
assertTrue(cs.sound);
assertTrue(cs.e < 30);
cs.g = 0;
cs.unlock();
assertFalse(cs.locked);
assertTrue(cs.g == 0);
}

@Test
void testcase012(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
cs.tick(20);
cs.armedOn();
assertTrue(cs.armed);
}

@Test
void testcase013(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase014(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase015(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase016(){
CarSystem1 cs = new CarSystem();
cs.lock();
assertTrue(cs.locked);
}

@Test
void testcase017(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase018(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase019(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase020(){
CarSystem1 cs = new CarSystem();
cs.lock();
assertTrue(cs.locked);
}

@Test
void testcase021(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase022(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
}

@Test
void testcase023(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase024(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase025(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
void testcase026(){
CarSystem1 cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
}
}