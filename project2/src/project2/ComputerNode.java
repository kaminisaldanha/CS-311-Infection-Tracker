package project2;
import java.util.ArrayList;
import java.util.List;

/**
 * The ComputerNode class represents the nodes of the graph G, which are pairs (Ci, t).
 *
 * @author Smruthi Sandhanam
 * @author Kamini Saldanha
 * @author Meghna Vaidya
 */
public class ComputerNode {
	
	private int timestamp;
	private int ID;
	private List<ComputerNode> neighbors;
	private ComputerNode predeccesor;
	private int color; // 0 for white, 1 for grey, 2 for black
	
	//READ-ME: add predeccesor part here 
	public ComputerNode(int ID, int timestamp) {
		neighbors = new ArrayList<ComputerNode>();
		this.ID = ID;
		this.timestamp = timestamp;
	}
	
	/**
     * Returns the ID of the associated computer.
     *
     * @return Associated Computer's ID
     */
    public int getID() {
        return this.ID;
    }
    
    public void setID(int ID) {
    	this.ID = ID;
    }

    /**
     * Returns the timestamp associated with this node.
     *
     * @return Timestamp for the node
     */
    public int getTimestamp() {
        return this.timestamp;
    }
    
    public void setTimestamp(int timestamp) {
    	this.timestamp = timestamp;
    }
    
    public void addNeighbor(ComputerNode neighbor) {
    	this.neighbors.add(neighbor);
    }

    /**
     * Returns a list of ComputerNode objects to which there is outgoing edge from this ComputerNode object.
     *
     * @return a list of ComputerNode objects that have an edge from this to the nodes in the list.
     */
    public List<ComputerNode> getOutNeighbors() {
        return this.neighbors;
    }
    
    public ComputerNode getPredeccesor() {
    	return this.predeccesor;
    }
    
    public void setColor(int color) {
		this.color = color;
	}
	public int getColor() {
		return color;
	}

}