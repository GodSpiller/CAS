package evalclass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UpDownTest{


@Test
public void testcase000(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
fail();
cs.expect_max();
		cs.up();
}

@Test
public void testcase001(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
}

@Test
public void testcase002(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
fail();
}

@Test
public void testcase003(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
assertTrue(cs.state>1);
cs.down();
cs.expect_on(cs.state);
}

@Test
public void testcase004(){
App cs = new App();
cs.up();
cs.expect_on(1);
fail();
cs.down();
cs.expect_off();
}

@Test
public void testcase005(){
App cs = new App();
cs.up();
cs.expect_on(1);
fail();
}

@Test
public void testcase006(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.expect_max();
		cs.up();
cs.down();
cs.expect_on(9);
}

@Test
public void testcase007(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
cs.up();
cs.expect_on(cs.state);
fail();
}

@Test
public void testcase008(){
App cs = new App();
}

@Test
public void testcase009(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.down();
cs.expect_off();
cs.up();
cs.expect_on(1);
}

@Test
public void testcase010(){
App cs = new App();
}

@Test
public void testcase011(){
App cs = new App();
cs.up();
cs.expect_on(1);
cs.up();
cs.expect_on(cs.state);
fail();
}
}