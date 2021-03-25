package ast.nodes;

public class BoundaryValue {
    private int value;
    private boolean validity;
    private String guard;

    public BoundaryValue(int value, boolean validity) {
        this.value = value;
        this.validity = validity;
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
}
