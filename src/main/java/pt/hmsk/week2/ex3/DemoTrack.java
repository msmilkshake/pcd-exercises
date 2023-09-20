package pt.hmsk.week2.ex3;

import javax.swing.*;

public class DemoTrack {

	public static void main(String[] args) {
		int carCount = 12;
		int distance = 200;

		JFrame frame = new JFrame("Race Track");
		Track track = new Track(carCount, distance, frame);
		
		Thread[] threads = new Thread[carCount];
		for (int i = 0; i < carCount; ++i) {
			Car car = new Car(i, distance);
			car.addObserver(track);
			threads[i] = new Thread(car);
		}
		
		track.setThreads(threads);
		
		
		frame.add(track);
		frame.setSize(500, 300);
		frame.setVisible(true);

		for (int i = 0; i < carCount; ++i) {
			threads[i].start();
		}
	}

}
