package ast;

public class BoundaryValue {
    private int value;
    private boolean validity;
    private String guard;
    private String clock;
    private int originalValue;
    private int indexEnd;
    private int indexStart;

    public BoundaryValue(int value, boolean validity, String clock, int originalValue, int index) {
        this.value = value;
        this.validity = validity;
        this.clock = clock;
        this.originalValue = originalValue;
        this.indexEnd = index;
        this.indexStart = index - String.valueOf(originalValue).length();
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

    public int getOriginalValue() {
        return originalValue;
    }

    public int getIndexStart() {
        return indexStart;
    }

    public int getIndexEnd() {
        return indexEnd;
    }
}
