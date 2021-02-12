import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarSystemTests{

@BeforeEach
 void setup(){
 CarSystem cs = new CarSystem();
}

@Test
 void test000(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

}

@Test
 void test001(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

unlock();

soundOff();

}

@Test
 void test002(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

soundOff();

soundOff();

flashOff();

}

@Test
 void test003(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

soundOff();

soundOff();

flashOff();

}

@Test
 void test004(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

soundOff();

soundOff();

flashOff();

unlock();

}

@Test
 void test005(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

soundOff();

unlock();

}

@Test
 void test006(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

open();

flashOn();

soundOn();

unlock();

soundOff();

flashOff();

}

@Test
 void test007(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

wait20seconds();
armedOn();

unlock();

armedOff();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

}

@Test
 void test008(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

open();

}

@Test
 void test009(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

open();

assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);

}

@Test
 void test010(){
CarSystem cs = new CarSystem();


cs.lock();

assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);

unlock();

}

@Test
 void test011(){
CarSystem cs = new CarSystem();


cs.lock();

assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);

cs.close();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

}

@Test
 void test012(){
CarSystem cs = new CarSystem();


cs.close();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

cs.lock();
cs.c = 0;

assertEquals(0, cs.c);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);

unlock();

assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);

}
}