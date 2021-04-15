package ast.nodes;

public class BoundaryValue {
    private int value;
    private boolean validity;
    private String guard;
    private String clock;

    public BoundaryValue(int value, boolean validity, String clock) {
        this.value = value;
        this.validity = validity;
        this.clock = clock;
    }

    public int getValue() {
        return value;
    }

    public boolean getValidity() {
        return validity;
    }

    public String getGuard() {
        return guard;
    }

    public void setGuard(String guard) {
        this.guard = guard;
    }

    public String getClock() {
        return clock;
    }
}
