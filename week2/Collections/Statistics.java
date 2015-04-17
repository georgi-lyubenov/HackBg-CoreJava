import java.util.*;

public class Statistics {
	private int size = 0;
	private static final int DEFAULT_CAPACITY = 10;
	private int[] elements;

	public Statistics() {
		elements = new int[DEFAULT_CAPACITY];
	}

	public void add(Integer e) {
		if (size == elements.length) {
			ensureCapa();
		}
		elements[size++] = e;
	}

	private void ensureCapa() {
		int newSize = elements.length * 2;
		elements = Arrays.copyOf(elements, newSize);
	}

	public int size() {
		return size;
	}

	public int getMean() {
		int mean = 0;
		for (int i = 0; i < size(); i++) {
			mean += elements[i];
		}
		return mean / size();
	}

	public static void BubbleSort(int[] num) {
		int j;
		boolean flag = true;
		int temp;
		while (flag) {
			flag = false;
			for (j = 0; j < num.length - 1; j++) {
				if (num[j] < num[j + 1]) {
					temp = num[j];
					num[j] = num[j + 1];
					num[j + 1] = temp;
					flag = true;
				}
			}
		}
	}

	public int getMedian() {
		BubbleSort(elements);
		if (size() % 2 == 0) {
			return elements[(size() + 1) / 2];
		} else {
			return elements[size() / 2];
		}
	}

	public int count(int number, int[] arr) {
		int result = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == number)
				result++;
		}
		return result;
	}

	public int getMode() {
		int mode = 0;
		int modeOccur = 0;
		for (int i = 0; i < size(); i++) {
			if (elements[i] != mode && count(elements[i], elements) > modeOccur) {
				modeOccur = count(elements[i], elements);
				mode = elements[i];
			}
		}
		return mode;
	}

	public int getRange() {
		BubbleSort(elements);
		return elements[0] - elements[size() - 1];
	}

	public void print() {
		System.out.print("the mean is: " + getMean());
		System.out.println("");
		System.out.print("the median is: " + getMedian());
		System.out.println("");
		System.out.print("thee mode is: " + getMode());
		System.out.println("");
		System.out.print("the range is: " + getRange());
	}

	public static void main(String[] args) {
		Statistics s = new Statistics();
		s.add(13);
		s.add(18);
		s.add(13);
		s.add(14);
		s.add(13);
		s.add(16);
		s.add(14);
		s.add(21);
		s.add(13);
		s.print();

	}

}
