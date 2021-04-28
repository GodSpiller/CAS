package carsystem;

import org.junit.Test;

import static org.junit.Assert.*;

public class CarSystemTest {


@Test
public void testcase000(){
CarSystem cs = new CarSystem();
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
public void testcase001(){
CarSystem cs = new CarSystem();
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
public void testcase002(){
CarSystem cs = new CarSystem();
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
public void testcase003(){
CarSystem cs = new CarSystem();
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
public void testcase004(){
CarSystem cs = new CarSystem();
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
public void testcase005(){
CarSystem cs = new CarSystem();
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
public void testcase006(){
CarSystem cs = new CarSystem();
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
public void testcase007(){
CarSystem cs = new CarSystem();
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
public void testcase008(){
CarSystem cs = new CarSystem();
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
public void testcase009(){
CarSystem cs = new CarSystem();
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
public void testcase010(){
CarSystem cs = new CarSystem();
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
public void testcase011(){
CarSystem cs = new CarSystem();
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
public void testcase012(){
CarSystem cs = new CarSystem();
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
public void testcase013(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase014(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase015(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase016(){
CarSystem cs = new CarSystem();
cs.lock();
assertTrue(cs.locked);
}

@Test
public void testcase017(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase018(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase019(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase020(){
CarSystem cs = new CarSystem();
cs.lock();
assertTrue(cs.locked);
}

@Test
public void testcase021(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase022(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
}

@Test
public void testcase023(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase024(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase025(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
cs.lock();
cs.c = 0;
assertTrue(cs.locked);
assertTrue(cs.c == 0);
fail();
}

@Test
public void testcase026(){
CarSystem cs = new CarSystem();
cs.close();
assertTrue(cs.closed);
}
}