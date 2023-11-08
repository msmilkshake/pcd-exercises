package pt.hmsk.week6.ex1.countdown;

import java.util.concurrent.CountDownLatch;


class SearcherThread extends Thread {
    private String myText;
    private String textToFind;
    private CountDownLatch latch;
    private int result = -1;

    public SearcherThread(String myText, String textToFind,
                          CountDownLatch latch) {
        this.myText = myText;
        this.textToFind = textToFind;
        this.latch = latch;
    }

    public String getMyText() {
        return myText;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        long start = System.nanoTime();
        result = myText.indexOf(textToFind);
        latch.countDown();
        System.err.println("Thread finishing at:" + (System.nanoTime() - start) / 1e6);
    }


}

