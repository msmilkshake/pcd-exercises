package pt.hmsk.week3.ex2;

public class SafeQueue<T> implements Queue<T> {
    private final double resizeFactor;
    
    private T[] queue;
    private int headIndex = 0;
    private int tailIndex = 0;
    private boolean isFull = false;
    
    public SafeQueue() {
        this(128, 1.5);
    }
    
    public SafeQueue(int startCapacity, double resizeFactor) {
        if (startCapacity <= 0) {
            throw new RuntimeException("The starting capacity must be a positive integer.");
        }
        if (resizeFactor < 0.0) {
            throw new RuntimeException("The resize factor must be positive or zero.");
        }
        queue = (T[]) new Object[startCapacity];
        this.resizeFactor = resizeFactor; 
    }
    
    private void resize() {
        int newLength = (int) (queue.length + queue.length * resizeFactor);
        newLength += newLength == queue.length ? 1 : 0;
        
        T[] temp = (T[]) new Object[newLength];
        
        for (int i = 0; i < size(); ++i) {
            temp[i] = queue[(headIndex + i) % queue.length];
        }
        headIndex = 0;
        tailIndex = size();
        queue = temp;
    }
    
    @Override
    public synchronized boolean empty() {
        return headIndex == tailIndex && !isFull;
    }

    @Override
    public synchronized T peek() {
        if (empty()) {
            throw new IllegalStateException("The queue is empty.");
        }
        return queue[headIndex];
    }

    @Override
    public synchronized T poll() {
        T head = peek();
        ++headIndex;
        headIndex %= queue.length;
        if (isFull) {
            isFull = false;
        }
        return head;
    }

    @Override
    public synchronized void offer(T item) {
        if (isFull) {
            throw new IllegalStateException("The queue is full.");
        }
        queue[tailIndex++] = item;
        tailIndex %= queue.length;
        if (tailIndex == headIndex) {
            if (resizeFactor == 0.0) {
                isFull = true;
            } else {
                resize();
            }
        }
    }

    @Override
    public synchronized int size() {
        if (isFull) {
            return queue.length;
        }
        int size = tailIndex - headIndex;
        size += size < 0 ? queue.length : 0;
        return size;
    }
    
    // !!! FOR TEST PURPOSES ONLY !!! //
    @Override
    public synchronized void setStartIndex(int idx) {
        if (!empty()) {
            throw new IllegalStateException(
                    "Can't set the initial indexes of a non empty queue.");
        }
        headIndex = idx;
        tailIndex = idx;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("| ");
        for (int i = 0; i < size(); ++i) {
            sb.append(queue[(i + headIndex) % queue.length]);
            if (i == 0) {
                sb.append(" |");
            }
            sb.append(", ");
        }
        if (empty()) {
            sb.append("|");
        } else {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("\nHead index: ");
        sb.append(headIndex);
        sb.append(", Tail index: ");
        sb.append(tailIndex);
        sb.append(", Size: ");
        sb.append(size());
        
        return sb.toString();
    }

    // Testing the Queue
    public static void main(String[] args) {
        SafeQueue<Integer> q = new SafeQueue<>(16, 0.0);
        for (int i = 0; i < 16; ++i) {
            q.offer(i + 10);
        }
        System.out.println(q);
        
        for (int i = 0; i < 3; ++i) {
            q.poll();
        }
        System.out.println(q);

        for (int i = 0; i < 2; ++i) {
            q.offer(i + 100);
        }
        System.out.println(q);

        for (int i = 0; i < 6; ++i) {
            q.poll();
        }
        System.out.println(q);

        for (int i = 0; i < 9; ++i) {
            q.poll();
        }
        System.out.println(q);
        
        q.setStartIndex(9);

        for (int i = 0; i < 10; ++i) {
            q.offer(i + 1000);
        }
        System.out.println(q);

        try {
            for (int i = 0; i < 11; ++i) {
                q.poll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(q);
        
        try {
            for (int i = 0; i < 17; ++i) {
                q.offer(i + 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(q);
    }
}
