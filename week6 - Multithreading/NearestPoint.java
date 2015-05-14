package problems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Point {
	public int x;
	public int y;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String print() {
		return "(" + x + "," + y + ")";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}

public class NearestPoint {
	public static List<Point> generatePoints() {
		List<Point> l = new ArrayList<Point>();
		for (int i = 0; i < 10_000; i++) {

			l.add(new Point((int) (Math.random() * 10_000), (int) (Math
					.random() * 10_000)));
		}
		return l;
	}

	public boolean closer(Point current, Point center, Point currentClosest) {
		double distanceToCurrent = Math.sqrt(Math.pow(
				center.getX() - current.getX(), 2)
				+ Math.pow(center.getY() - current.getY(), 2));
		double distanceToCurrentClosest = Math.sqrt(Math.pow(center.getX()
				- currentClosest.getX(), 2)
				+ Math.pow(center.getY() - currentClosest.getY(), 2));
		if (distanceToCurrent < distanceToCurrentClosest)
			return true;
		return false;

	}

	public Point findNearest(Point p, List<Point> l) {
		Point nearest = new Point(10_000, 10_000);
		for (Point pt : l) {
			if (closer(pt, p, nearest) == true && !pt.equals(p)) {
				nearest = pt;
			}
		}
		return nearest;
	}

	public synchronized void getNearestPoints(final List<Point> generatedPoints) {
		final Map<Point, Point> result = new HashMap<Point, Point>();
		final Map<Point, Point> result2 = new HashMap<Point, Point>();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				doCalculations(generatedPoints, 0, generatedPoints.size() / 2,
						result);
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				doCalculations(generatedPoints, generatedPoints.size() / 2,
						generatedPoints.size(), result2);
			}
		});
		t1.start();
		t2.start();

	}

	public static void print(Map<Point, Point> result) {
		for (Point name : result.keySet()) {
			String key = name.print();
			String value = result.get(name).print();
			System.out.println(key + " : " + value);
		}
	}

	public void doCalculations(List<Point> inPoints, int indexFrom,
			int indexTo, Map<Point, Point> outMap) {

		for (int i = indexFrom; i < indexTo; i++) {
			outMap.put(inPoints.get(i), findNearest(inPoints.get(i), inPoints));
		}
		print(outMap);
	}

	public static void main(String[] args) {
		NearestPoint o = new NearestPoint();
		List<Point> l = generatePoints();
		o.getNearestPoints(l);

	}
}
