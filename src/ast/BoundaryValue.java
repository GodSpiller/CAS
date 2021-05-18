package ast;

public class BoundaryValue {
    private int value;
    private boolean validity;
    private String guard;
    private String clock;
    private int queryValue;
    private int indexEnd;
    private int indexStart;

    public BoundaryValue(int value, boolean validity, String clock, int queryValue, int index) {
        this.value = value;
        this.validity = validity;
        this.clock = clock;
        this.queryValue = queryValue;
        this.indexEnd = index;
        this.indexStart = index - String.valueOf(queryValue).length();
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

    public int getQueryValue() {
        return queryValue;
    }

    public int getIndexStart() {
        return indexStart;
    }

    public int getIndexEnd() {
        return indexEnd;
    }
}
