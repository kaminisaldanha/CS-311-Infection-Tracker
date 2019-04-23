package project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * The CommunicationsMonitor class represents the graph G
 * built to answer infection queries.
 *
 * @author Meghna Vaidya
 * @author Smruthi Sandhanam
 * @author Kamini Saldanha
 */
public class CommunicationsMonitor {

	private ArrayList<Tuple> tuples; 
	private HashMap<Integer, List<ComputerNode>> map;
	
    /**
     * Constructor with no parameters
     */
    public CommunicationsMonitor() {
    	tuples = new ArrayList<Tuple>();
    	map = new HashMap<Integer, List<ComputerNode>>();
    }

    /**
     * Takes as input two integers c1, c2, and a timestamp. This triple represents the fact that the computers with IDs
     * c1 and c2 have communicated at the given timestamp. This method should run in O(1) time. Any invocation of this
     * method after createGraph() is called will be ignored.
     *
     * @param c1        First ComputerNode in the communication pair.
     * @param c2        Second ComputerNode in the communication pair.
     * @param timestamp Time the communication took place.
     */
    public void addCommunication(int c1, int c2, int timestamp) {
    	
    	tuples.add(new Tuple(c1, c2, timestamp));
    }

    /**
     * Constructs the data structure as specified in the Section 2. This method should run in O(n + m log m) time.
     */
    @SuppressWarnings("unchecked")
	public void createGraph() {

    	//sort the tuples by timestamp
    	this.mergeSort(this.tuples);
    	
    	//---- adding first tuple into map ----
    	Tuple firstTuple = this.tuples.get(0);
    	List<ComputerNode> list = new ArrayList<ComputerNode>();
    	list.add(new ComputerNode(firstTuple.getC1(), firstTuple.getTimestamp()));
    	map.put(firstTuple.getC1(), list);
    	list = new ArrayList<ComputerNode>();
    	list.add(new ComputerNode(firstTuple.getC2(), firstTuple.getTimestamp()));
    	map.put(firstTuple.getC2(), list);
    	
    	//adding all tuples into map
    	for(int i = 1; i < this.tuples.size(); i++) {
    		
    		Tuple curTuple = this.tuples.get(i);
    		Tuple prevTuple = this.tuples.get(i-1);
    		ComputerNode c1 = new ComputerNode(curTuple.getC1(), curTuple.getTimestamp());
			ComputerNode c2 = new ComputerNode(curTuple.getC2(), curTuple.getTimestamp());
    		
    		//returns 0 if there is no duplicate
    		int isDuplicate = isDuplicate(curTuple, prevTuple);
	
			//if there are no duplicates
    		if(isDuplicate == 0) {
    			//add each node and its neighbors
    			c1.addNeighbor(c2);
    			c2.addNeighbor(c1);
    			addNode(c1);
    			addNode(c2);
    		} else { //there was a duplicate
    			//if the duplicate was c2, add c1
    			if(isDuplicate == curTuple.getC2()) {
    				addNode(c1);	
    			} else {
    				//if the duplicate was c1, add c2
    				addNode(c2);
    			}
    		}
    	}
    }
    
    /**
     * Adds this node into the map
     * @param c
     */
    public void addNode(ComputerNode c) {
    	
    	List<ComputerNode> list;
    	
    	if(this.map.get(c.getID()) == null) {
			list = new ArrayList<ComputerNode>();
			list.add(c);
			map.put(c.getID(), list);
		} else {
			list = this.map.get(c.getID());
			list.add(c);
			this.map.put(c.getID(), list);
		}	
    }
    
    /**
     * Finds any duplicates found in cur when compared to prev by comparing each of the 
     * ID's in cur with the ID's in prev if the timestamp if the same
     * @param cur
     * @param prev
     * @return a list of all duplicate computer node ID's
     */
    public int isDuplicate(Tuple cur, Tuple prev) {
    	

    	if(cur.getTimestamp() == prev.getTimestamp()) {	
    		if(cur.getC1() == prev.getC1()) {
    			return cur.getC1();
    		} else if (cur.getC1() == prev.getC2()) {
    			return cur.getC1();
    		} else if(cur.getC2() == prev.getC2()) {
    			return cur.getC2();
    		} else if(cur.getC2() == prev.getC1()) {
    			return cur.getC2();
    		}
    	} 
    	
    	return 0;	
    }
    
    /**
     * Determines whether computer c2 could be infected by time y if computer c1 was infected at time x. If so, the
     * method returns an ordered list of ComputerNode objects that represents the transmission sequence. This sequence
     * is a path in graph G. The first ComputerNode object on the path will correspond to c1. Similarly, the last
     * ComputerNode object on the path will correspond to c2. If c2 cannot be infected, return null.
     * <p>
     * Example 3. In Example 1, an infection path would be (C1, 4), (C2, 4), (C2, 8), (C4, 8), (C3, 8)
     * <p>
     * This method can assume that it will be called only after createGraph() and that x <= y. This method must run in
     * O(m) time. This method can also be called multiple times with different inputs once the graph is constructed
     * (i.e., once createGraph() has been invoked).
     *
     * @param c1 ComputerNode object to represent the Computer that is hypothetically infected at time x.
     * @param c2 ComputerNode object to represent the Computer to be tested for possible infection if c1 was infected.
     * @param x  Time c1 was hypothetically infected.
     * @param y  Time c2 is being tested for being infected.
     * @return List of the path in the graph (infection path) if one exists, null otherwise.
     */
    public List<ComputerNode> queryInfection(int c1, int c2, int x, int y) {
    	//Walk through the list for Ca until we reach the first reference to a node (Ca, x')
    	//such that x' >= x. 
//    	for (ArrayList<ComputerNode> node : adjList){
//    		
//    	}
    	//Run BFS or DFS on G to determine all nodes reachable from (Ca, x')
    	//DFS(c1);
    	//If a node (Cb. y') with y' <= y is reachable from (Ca, x'), then we declare that Cb could
    	//have become infected by time y; otherwise, we declare tha this is impossible
    	
        return null;
    }

    /**
     * Returns a HashMap that represents the mapping between an Integer and a list of ComputerNode objects. The Integer
     * represents the ID of some computer Ci, while the list consists of pairs (Ci, t1),(Ci, t2),..., (Ci, tk),
     * represented by ComputerNode objects, that specify that Ci has communicated with other computers at times
     * t1, t2,...,tk. The list for each computer must be ordered by time; i.e., t1\<t2\<...\<tk.
     *
     * @return HashMap representing the mapping of an Integer and ComputerNode objects.
     */
    public HashMap<Integer, List<ComputerNode>> getComputerMapping() {
        return this.map;   
    }

    /**
     * Returns the list of ComputerNode objects associated with computer c by performing a lookup in the mapping.
     *
     * @param c ID of computer
     * @return ComputerNode objects associated with c.
     */
    public List<ComputerNode> getComputerMapping(int c) {
    	return this.map.get(c);
    }
    
    public void merge(ArrayList<Tuple> arrayList, ArrayList<Tuple> rightArray, ArrayList<Tuple> leftArray) {
    	int rightIndex = 0;
    	int leftIndex = 0;
    	int arrayListIndex = 0;

        while(leftIndex < leftArray.size() && rightIndex < rightArray.size()){
        	
        		if(leftArray.get(leftIndex).getTimestamp() < rightArray.get(rightIndex).getTimestamp()){
        			arrayList.set(arrayListIndex, leftArray.get(leftIndex));
        			leftIndex++;
        		}else{
        			arrayList.set(arrayListIndex, rightArray.get(rightIndex));
        			rightIndex++;
        		}
        		arrayListIndex++;
        }
        
        ArrayList<Tuple> restArray;
        int restIndex = 0;
        if(leftIndex >= leftArray.size()){
        	restArray = rightArray;
        	restIndex = rightIndex;
        }
        else{
        	restArray = leftArray;
        	restIndex = leftIndex;	
        }
        
        //Copy rest of the Arraylist (left or right) that hasn't been used
        for(int i = restIndex; i < restArray.size(); i++){
        	arrayList.set(arrayListIndex, restArray.get(i));
        	arrayListIndex++;
        }

    }
    
    public ArrayList<Tuple> mergeSort(ArrayList<Tuple> arrayList){
    	
    	ArrayList<Tuple> leftArray = new ArrayList<Tuple>();
        ArrayList<Tuple> rightArray = new ArrayList<Tuple>();
        int center;
        
    	if(arrayList.size() == 1){
    		return arrayList;	
    		
    	} else{
    		
    		center = arrayList.size()/2;
    		//copy the left half of the arrayList into leftArray
    		for(int i = 0; i<center; i++){
    			leftArray.add(arrayList.get(i));
    		}
    		
    		//copy the right half of the arrayList into rightArray
    		for(int i = center; i < arrayList.size(); i++){
    			rightArray.add(arrayList.get(i));
    		}
    		
    		//sort left and right half of the array
    		leftArray = mergeSort(leftArray);
    		rightArray = mergeSort(rightArray);
    		
    		//merge the results back together
    		merge(arrayList, rightArray, leftArray);
    	}
    	
    	return arrayList;
    }
 
    
    public ArrayList<Tuple> getTuples() {
    	return this.tuples;
    }
    
    private List<ComputerNode> DFS(ComputerNode c1, ComputerNode c2, int x, int y) {
		List<ComputerNode> path = new ArrayList<ComputerNode>();
		path.add(c1);
		path = DFSVisit(c1, c2, x, y, path);
		if(!path.get(path.size()- 1 ).equals(c2)) {
			return null;
		}
		return path;
		//DFS(Visit)
		
		
	}
	
	private List<ComputerNode> DFSVisit(ComputerNode c1, ComputerNode c2, int x, int y, List<ComputerNode> path){
		//c1.setColor(1);
		for(int i = 0; i < c1.getOutNeighbors().size(); i++) {
			ComputerNode temp = c1.getOutNeighbors().get(i);
		}
		return path;
	}
	
	
}
