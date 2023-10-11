package pt.hmsk.week4.ex1;

public class Boar {
    private int cookId;
    private int sequence;
    
    public Boar(int cookId, int sequence) {
        this.cookId = cookId;
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "Boar #" + sequence + ", cooked by Cook #" + cookId;
    }
}
