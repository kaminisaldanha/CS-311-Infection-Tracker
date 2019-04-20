package project2;

import java.util.ArrayList;
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

	private List<ComputerNode> compNodes; 
	private List<Integer> nodes;
	
    /**
     * Constructor with no parameters
     */
    public CommunicationsMonitor() {
    	compNodes = new ArrayList<ComputerNode>();
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
    	
    	//creating both computer nodes
    	ComputerNode node = new ComputerNode(c1, timestamp);
    	ComputerNode node2 = new ComputerNode(c2, timestamp);
    	
    	//add neighbor for node1 and node 2
    	node.addNeighbor(node2);
    	node2.addNeighbor(node);
    	
    	if(!nodes.contains(node.getID())){
    		
    	}
    	
    	//add the node1 to the list of computer nodes
    	if(!compNodes.contains(node)) {
    	   	compNodes.add(node);
    	}
    	
    	//add the nodee2 to the list of computer nodes
    	if(!compNodes.contains(node2)) {
    		compNodes.add(node2);
    	}
    }

    /**
     * Constructs the data structure as specified in the Section 2. This method should run in O(n + m log m) time.
     */
    public void createGraph() {

    	List<Integer> nodes = new ArrayList<>();
    	List<ComputerNode> sorted = this.sortList();
    	
    	ArrayList<ComputerNode>[] adjList = new ArrayList[nodes.size()];
    	
    	for(int i = 0; i < sorted.size(); i++) {
    		
    		
    		
    	}
    	
    	
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
        return null;
    }

    /**
     * Returns the list of ComputerNode objects associated with computer c by performing a lookup in the mapping.
     *
     * @param c ID of computer
     * @return ComputerNode objects associated with c.
     */
    public List<ComputerNode> getComputerMapping(int c) {
        return null;
    }
    
    public List<ComputerNode> sortList() {
        return null;
    }
}
