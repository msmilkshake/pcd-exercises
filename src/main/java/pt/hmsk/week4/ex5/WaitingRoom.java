package pt.hmsk.week4.ex5;

import java.util.LinkedList;
import java.util.Queue;

public class WaitingRoom {
    private int maxChairs;
    private Queue<Client> chairs = new LinkedList<>();
    
    public WaitingRoom(int maxChairs) {
        this.maxChairs = maxChairs;
    }
    
    
}
