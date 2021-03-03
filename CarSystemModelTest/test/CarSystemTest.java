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
}