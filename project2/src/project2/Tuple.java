package project2;

public class Tuple {
	
	private int c1;
	private int c2;
	private int timestamp;

	public Tuple(int c1, int c2, int timestamp) {
		this.c1 = c1;
		this.c2 = c2;
		this.timestamp = timestamp;
	}
	
	public int getC1() {
		return this.c1;
	}
	
	public int getC2() {
		return this.c2;
	}
	
	public int getTimestamp() {
		return this.timestamp;
	}
	
}
