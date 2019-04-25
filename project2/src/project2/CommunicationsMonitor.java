package project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
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

	/**
	 * Array list of all the tuples that have been added
	 */
	private ArrayList<Tuple> tuples; 
	/**
	 * The adjacency list that represents this graph
	 */
	private HashMap<Integer, List<ComputerNode>> map;
	
    /**
     * Constructor with no parameters. It intializes the tuples and the map.
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
	public void createGraph() {
    	//sort the tuples by timestamp
    	this.mergeSort(this.tuples);
    	
    	//adding all tuples into map
    	for(int i = 0; i < this.tuples.size(); i++) {
    		
    		ComputerNode c1 = new ComputerNode(this.tuples.get(i).getC1(), this.tuples.get(i).getTimestamp());
			ComputerNode c2 = new ComputerNode(this.tuples.get(i).getC2(), this.tuples.get(i).getTimestamp());
    		boolean c1IsDuplicate = isDuplicate(c1, c2);
    		boolean c2IsDuplicate = isDuplicate(c2, c1);
	
    		//if c1 & c2 are not duplicates -> add c1 and c2
    		if(c1IsDuplicate == false && c2IsDuplicate == false) {
    			c1.addNeighbor(c2);
    			c2.addNeighbor(c1);
    			addNode(c1);
    			addNode(c2);
    		} else { 
    			//if the duplicate was c2 -> add c1
    			if(c1IsDuplicate == true) {
    				addNode(c2);
    			} else { //if the duplicate was c1 -> add c2
    				addNode(c1);
    			}
    		}
    	}	
    }
    
    /**
     * Adds this node into the map
     * @param cur
     */
    public void addNode(ComputerNode cur) {
    	List<ComputerNode> list;
    	if(this.map.get(cur.getID()) == null) {
			list = new ArrayList<ComputerNode>();
			list.add(cur);
		} else {
			list = this.map.get(cur.getID());
			
			for(int i = 0; i < map.get(cur.getID()).size(); i++) {
				ComputerNode prev = map.get(cur.getID()).get(i);
				prev.addNeighbor(cur);
			}
			
			list.add(cur);
		}	
    	
		this.map.put(cur.getID(), list);
    }
    
    /**
     * Finds any duplicates found in cur when compared to prev by comparing each of the 
     * ID's in cur with the ID's in prev if the timestamp if the same
     * @param cur
     * @param prev
     * @return a list of all duplicate computer node ID's
     */
    public boolean isDuplicate(ComputerNode c1, ComputerNode c2) {
    	if(this.map.get(c1.getID()) != null) {
        	for(int i = 0; i < this.map.get(c1.getID()).size(); i++) {
        		if(c1.getTimestamp() == this.map.get(c1.getID()).get(i).getTimestamp()) {
        			this.map.get(c1.getID()).get(i).addNeighbor(c2);
        			c2.addNeighbor(this.map.get(c1.getID()).get(i));
        			return true;
        		}
        	}
    	}
    	
    	return false;
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
    	ComputerNode found = null;
    	List<ComputerNode> start = this.map.get(c1);
    	for(ComputerNode node: start) {
    		if(node.getTimestamp() >= x) {
    			found = DFS(node, c2, y);
    			break;
    		}
    	}
    	
    	if(found != null) {
    		return findPath(found);
    	}
    	
    	return null;	
    }
    
    /**
<<<<<<< HEAD
     * Performs DFS to search for a computer node with a specific ID and timestamp. It first makes every nodes color black. 
     * It then calls DFSVisit on node if its color is black. If a node was found with the ID and timestamp, it returns it.  
     * @param node is the node we are currently at
     * @param ID is the ID of the node we are looking for
     * @param timestamp is the timestamp of the node we are looking for
     * @return the node we find if there is a path to it
=======
     * 
     * @param node
     * @param ID
     * @param timestamp
     * @return
>>>>>>> 823510d2f3a992693a49e6cd18dc560a14baf247
     */
    private ComputerNode DFS(ComputerNode node, int ID, int timestamp) {
    	
    	for(Integer computer: this.map.keySet()) {
    		List<ComputerNode> clist = this.map.get(computer);
    		for(ComputerNode cnode: clist) {
    			cnode.setColor(0);
    			cnode.setPredeccesor(null);
    		}
    	}
    	if(node.getColor() == 0) {
    		ComputerNode found = DFSVisit(node, ID, timestamp);
    		if(found != null) {
    			return found;
    		}
    	}
    	return null;
    }
    
    /**
<<<<<<< HEAD
     * Performs DFSVisit to search for a computer node with a specific ID and timestamp. It calls DFSVisit on all of its neighbors
     * until it finds the node with the ID and timestamp of the node you are looking for.
=======
     * 
>>>>>>> 823510d2f3a992693a49e6cd18dc560a14baf247
     * @param node
     * @param ID
     * @param timestamp
     * @return
     */
	private ComputerNode DFSVisit(ComputerNode node, int ID, int timestamp){
		node.setColor(1); //node is visited (i.e. grey)
	  	List<ComputerNode> neighbors = node.getOutNeighbors();
	  	for(int i = 0; i < neighbors.size(); i++) {
	  		ComputerNode next = neighbors.get(i);
	  		if(next.getColor() == 0) { //isWhite
	  			 next.setPredeccesor(node);
	  			 
	  			 if(next.getID() == ID && next.getTimestamp() <= timestamp) { //found the end node
	  				 return next;
	  			 } else {
	  				 ComputerNode result = DFSVisit(next, ID, timestamp); // keep looking
	  				 if(result != null) {
	  					 return result;
	  				 }
	  			 }
	  		}
	  	}
	  	node.setColor(2); //node is completed (i.e. set black)
	  	return null;
	}
    
	/**
<<<<<<< HEAD
	 * Find the path of the node. It keeps looking for its predecessors and adds them to a list.
	 * @param node the node you want to find the path for
	 * @return a list of all the predecessors of a node
=======
	 * 
	 * @param node
	 * @return
>>>>>>> 823510d2f3a992693a49e6cd18dc560a14baf247
	 */
    public List<ComputerNode> findPath(ComputerNode node){
    	
    	List<ComputerNode> path = new ArrayList<ComputerNode>();
    	
    	while(node != null) {
    		path.add(node);
    		node = node.getPredeccesor();
    	}
    	
    	Collections.reverse(path);
    	return path;
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
    	
    	if(this.map.get(c) == null) {
    		return null;
    	}
    	
    	return this.map.get(c);
    }
    
    /**
     * 
     * @param arrayList
     * @param rightArray
     * @param leftArray
     */
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
    
    /**
     * 
     * @param arrayList
     * @return
     */
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
 
    /**
<<<<<<< HEAD
     * Returns all of the tuples that were added in
     * @return an array list of all tuples 
=======
     * 
     * @return
>>>>>>> 823510d2f3a992693a49e6cd18dc560a14baf247
     */
    public ArrayList<Tuple> getTuples() {
    	return this.tuples;
    }	
}
