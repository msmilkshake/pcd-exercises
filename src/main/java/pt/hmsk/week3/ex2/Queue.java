package pt.hmsk.week3.ex2;

public interface Queue<T> {
    boolean empty();
    T peek();
    T poll();
    void offer(T item);
    int size();
    void setStartIndex(int idx);
}
