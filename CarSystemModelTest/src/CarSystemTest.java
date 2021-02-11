import static org.junit.jupiter.api.Assertions.*;

public class CarSystemTest {
    public static void main(String[] args){
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

    }
}