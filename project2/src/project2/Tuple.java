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
	
	/**
     * Gets the C1 Node
     */
	public int getC1() {
		return this.c1;
	}
	
	//Gets the C2 Node
	public int getC2() {
		return this.c2;
	}
	
	//Returns the timestamp of the node
	public int getTimestamp() {
		return this.timestamp;
	}
	
}
