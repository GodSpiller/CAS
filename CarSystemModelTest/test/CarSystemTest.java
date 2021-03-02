import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CarSystemTests{

@BeforeEach
 void setup(){
 CarSystem cs = new CarSystem();
}

@Test
void test(){
CarSystem cs = new CarSystem();
cs.lock();
assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);
cs.close();
cs.c = 0;
assertTrue(cs.c == 0);
assertTrue(cs.locked);
assertTrue(cs.closed);
assertFalse(cs.opened);
assertFalse(cs.unlocked);
cs.unlock();
assertFalse(cs.locked);
assertTrue(cs.unlocked);
assertFalse(cs.opened);
assertTrue(cs.closed);
assertFalse(cs.armed);
cs.open();

cs.lock();
assertTrue(cs.locked);
assertFalse(cs.unlocked);
assertTrue(cs.opened);
assertFalse(cs.closed);
cs.close();
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
cs.g = 0;
cs.unlock();
assertTrue(cs.g <= 0);
assertTrue(cs.unlocked);
}
}