package evalclass;

class App {
  private static int OFF = 0;
  private static int ON = 1;
  public int state = OFF;

  public void up() {
    this.state++;
  }

  public void down() {
    this.state--;
  }

  public void expect_max() {
    assert this.state == 9;
  }

  public void expect_on(int val) {
    assert this.state==val;
  }

  public void expect_off() {
    assert this.state == 0;
  }
}


