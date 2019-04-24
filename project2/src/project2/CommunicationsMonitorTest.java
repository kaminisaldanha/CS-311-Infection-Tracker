package project2;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommunicationsMonitorTest {
	
	// Instance variables
    CommunicationsMonitor monitor;
    
    @Before
    public void initialize() {
        // Start each test with an empty CommunicationsMonitor
    }
    
    CommunicationsMonitor cm;
    
	@Before
	public void before() {
		cm = new CommunicationsMonitor();
		cm.addCommunication(1, 2, 4);
		cm.addCommunication(2, 4, 8);
		cm.addCommunication(3, 4, 8);
		cm.addCommunication(1, 4, 12);
	}
    
    /*
     * Tests if the adjacency list is created properly using example 1 computer nodes
     */
    @Test
    public void testCreateGraph1() {
    	monitor = createExample1();
    	monitor.createGraph();
    	
    	//testing size of lists for each computer
    	assertEquals(2, monitor.getComputerMapping(1).size());
    	assertEquals(2, monitor.getComputerMapping(2).size());
    	assertEquals(1, monitor.getComputerMapping(3).size());
    	assertEquals(2, monitor.getComputerMapping(4).size());	
    	
    	//testing size of neighbors for each computer node
    	assertEquals(2, monitor.getComputerMapping(1).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(1).get(1).getOutNeighbors().size());
    	assertEquals(2, monitor.getComputerMapping(2).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(2).get(1).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(3).get(0).getOutNeighbors().size());
    	assertEquals(3, monitor.getComputerMapping(4).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(4).get(1).getOutNeighbors().size());
    	
    }
    
    @Test
    public void testCreateGraph2() {
    	monitor = createExample2();
    	monitor.createGraph();
    	
    	//testing size of lists for each computer
    	assertEquals(2, monitor.getComputerMapping(1).size());
    	assertEquals(2, monitor.getComputerMapping(2).size());
    	assertEquals(1, monitor.getComputerMapping(3).size());
    	assertEquals(1, monitor.getComputerMapping(4).size());
    	
    	//testing size of neighbors for each computer node
    	assertEquals(2, monitor.getComputerMapping(1).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(1).get(1).getOutNeighbors().size());
    	assertEquals(2, monitor.getComputerMapping(2).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(2).get(1).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(3).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(4).get(0).getOutNeighbors().size());	
    }
    
    @Test
    public void testCreateGraph3() {
    	
//        example3.addCommunication(2, 3, 8);
//        example3.addCommunication(1, 2, 14);
//        example3.addCommunication(2, 4, 8);
    	
    	
    	monitor = createExample3();
    	monitor.createGraph();
    	
    	//testing size of lists for each computer
    	assertEquals(1, monitor.getComputerMapping(1).size());
    	assertEquals(2, monitor.getComputerMapping(2).size());
    	assertEquals(1, monitor.getComputerMapping(3).size());
    	assertEquals(1, monitor.getComputerMapping(4).size());
    	
    	//testing size of neighbors for each computer node
    	assertEquals(1, monitor.getComputerMapping(1).get(0).getOutNeighbors().size());
    	assertEquals(3, monitor.getComputerMapping(2).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(2).get(1).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(3).get(0).getOutNeighbors().size());
    	assertEquals(1, monitor.getComputerMapping(4).get(0).getOutNeighbors().size());
    }
    
    @Test
    public void testCreateGraph4(){
    	monitor = createIdenticalComputerNodes();
    	monitor.createGraph();
    	assertEquals(1, monitor.getComputerMapping(1).size());
    	assertEquals(1, monitor.getComputerMapping(2).size());
    	assertEquals(1, monitor.getComputerMapping(3).size());
    	assertEquals(1, monitor.getComputerMapping(4).size());
    	assertEquals(1, monitor.getComputerMapping(5).size());
    	assertEquals(1, monitor.getComputerMapping(6).size());
    	
    }
    
    //Test if the ArrayList gets sorted by its Timestamp
    @Test
    public void sortTest1() {
    	
    	//Test that Example 1 is sorted correctly
    	monitor = createExample1();
    	monitor.mergeSort(monitor.getTuples());
    	
    	assertEquals(4, monitor.getTuples().get(0).getTimestamp());
    	assertEquals(8, monitor.getTuples().get(1).getTimestamp());
    	assertEquals(8, monitor.getTuples().get(2).getTimestamp());
    	assertEquals(12, monitor.getTuples().get(3).getTimestamp());
    }
    
    @Test
    public void sortTest2() {
        //Test that Example 2 is sorted correctly
        monitor = createExample2();
        monitor.mergeSort((monitor.getTuples()));
        
        assertEquals(8, monitor.getTuples().get(0).getTimestamp());
    	assertEquals(12, monitor.getTuples().get(1).getTimestamp());
    	assertEquals(14, monitor.getTuples().get(2).getTimestamp());
 
    }
    
    @Test
    public void sortTest3() {
        //Test that a random time stamped node list is sorted correctly
        monitor = createRandomTimeStampList();
        monitor.mergeSort((monitor.getTuples()));
        
        assertEquals(2, monitor.getTuples().get(0).getTimestamp());
    	assertEquals(4, monitor.getTuples().get(1).getTimestamp());
    	assertEquals(5, monitor.getTuples().get(2).getTimestamp());
    	assertEquals(12, monitor.getTuples().get(3).getTimestamp());
    	assertEquals(14, monitor.getTuples().get(4).getTimestamp());
    }
    
    @Test
    public void getComputerMapping1() {
        monitor = createExample1();
        monitor.createGraph();
        assertEquals(4,monitor.getComputerMapping().size());
   }

    @Test
    public void getComputerMapping2() {
        // Invalid key value should return null list
    	monitor = new CommunicationsMonitor();
        assertEquals(null, monitor.getComputerMapping(1));

        // Insert tuple and create graph
        monitor.addCommunication(1, 2, 4);
        monitor.createGraph();

        // Test that mapping has been updated
        assertEquals(1, monitor.getComputerMapping(1).size());
        assertEquals(null, monitor.getComputerMapping(3));
    }
    
    @Test
	public void testCreateGraph() {
		cm.createGraph();
		HashMap<Integer, List<ComputerNode>> adjList = cm.getComputerMapping();
		//C1
		List<ComputerNode> c1 = adjList.get(1);
		assertEquals(2, c1.size());
			//C1[0]
		List<ComputerNode> c1Neighbors = c1.get(0).getOutNeighbors();
		assertEquals(2, c1Neighbors.size());
		assertEquals(2, c1Neighbors.get(0).getID());
		assertEquals(4, c1Neighbors.get(0).getTimestamp());
		assertEquals(1, c1Neighbors.get(1).getID());
		assertEquals(12, c1Neighbors.get(1).getTimestamp());
			//C1[1]
		c1Neighbors = c1.get(1).getOutNeighbors();
		assertEquals(1, c1Neighbors.size());
		assertEquals(4, c1Neighbors.get(0).getID());
		assertEquals(12, c1Neighbors.get(0).getTimestamp());
		
		//C2
		List<ComputerNode> c2 = adjList.get(2);
		assertEquals(2, c2.size());
			//C2[0]
		List<ComputerNode> c2Neighbors = c2.get(0).getOutNeighbors();
		assertEquals(2, c2Neighbors.size());
		assertEquals(1, c2Neighbors.get(0).getID());
		assertEquals(4, c2Neighbors.get(0).getTimestamp());
		assertEquals(2, c2Neighbors.get(1).getID());
		assertEquals(8, c2Neighbors.get(1).getTimestamp());
			//C2[1]
		c2Neighbors = c2.get(1).getOutNeighbors();
		assertEquals(1, c2Neighbors.size());
		assertEquals(4, c2Neighbors.get(0).getID());
		assertEquals(8, c2Neighbors.get(0).getTimestamp());
		
		//C3
		List<ComputerNode> c3 = adjList.get(3);
		assertEquals(1, c3.size());
			//C3[0]
		List<ComputerNode> c3Neighbors = c3.get(0).getOutNeighbors();
		assertEquals(1, c3Neighbors.size());
		assertEquals(4, c3Neighbors.get(0).getID());
		assertEquals(8, c3Neighbors.get(0).getTimestamp());
		
		//C4
		List<ComputerNode> c4 = adjList.get(4);
		assertEquals(2, c4.size());
			//C4[0]
		List<ComputerNode> c4Neighbors = c4.get(0).getOutNeighbors();
		assertEquals(3, c4Neighbors.size());
		assertEquals(3, c4Neighbors.get(0).getID());
		assertEquals(8, c4Neighbors.get(0).getTimestamp());
		assertEquals(2, c4Neighbors.get(1).getID());
		assertEquals(8, c4Neighbors.get(1).getTimestamp());
		assertEquals(4, c4Neighbors.get(2).getID());
		assertEquals(12, c4Neighbors.get(2).getTimestamp());
			//C4[1]
		c4Neighbors = c4.get(1).getOutNeighbors();
		assertEquals(1, c4Neighbors.size());
		assertEquals(1, c4Neighbors.get(0).getID());
		assertEquals(12, c4Neighbors.get(0).getTimestamp());
		
	}
    
//	example1.addCommunication(1, 2, 4);
//	example1.addCommunication(2, 4, 8);
//	example1.addCommunication(3, 4, 8);
//	example1.addCommunication(1, 4, 12);
    
    @Test
    public void testQueryInfectionExample1() {
    	monitor = createExample1();
    	monitor.createGraph();
    	List<ComputerNode> path = monitor.queryInfection(1, 3, 2, 8);
//      path should be = (1, 4), (2, 4), (2, 8), (4, 8), (3, 8)
//		Neighbors should be:
//    	(1,4) = {(2,4), (1, 12)}
//    	(1, 12) = {(4, 12), (1, 12)}
//    	(2, 4) = {(1, 4), (2,8)}
//    	(2, 8) = {(4, 8), (2, 4)}
//    	(3, 8) = {(4, 8)}
//    	(4, 8) = {(3, 8), (2, 8), (4, 12)}
//    	(4, 12) = {(1, 12), (4, 8)
    	
    	assertEquals(1, path.get(0).getID());
    	assertEquals(4, path.get(0).getTimestamp());
    	assertEquals(2, path.get(1).getID());
    	assertEquals(4, path.get(1).getTimestamp());
    	assertEquals(2, path.get(2).getID());
    	assertEquals(8, path.get(2).getTimestamp());
    	assertEquals(4, path.get(3).getID());
    	assertEquals(8, path.get(3).getTimestamp());
    	assertEquals(3, path.get(4).getID());
    	assertEquals(8, path.get(4).getTimestamp());
    }
    
    @Test
	public void testQueryInfectionExample2() {
		
    	monitor = createExample2();
    	monitor.createGraph();
		List<ComputerNode> path = monitor.queryInfection(1, 3, 2, 9);
		assertEquals(null, path);
	}
    
    
    
    @Test
    public void testQueryInfectionExample4() {
    	
    	monitor = createExample4();
    	monitor.createGraph();
    	List<ComputerNode> path = monitor.queryInfection(1, 4, 2, 7);
    	
    	//testing the order of the ID and timestamps of the nodes in the path
    	assertEquals(1, path.get(0).getID());
    	assertEquals(4, path.get(0).getTimestamp());
    	assertEquals(2, path.get(1).getID());
    	assertEquals(4, path.get(1).getTimestamp());
    	assertEquals(2, path.get(2).getID());
    	assertEquals(5, path.get(2).getTimestamp());
    	assertEquals(3, path.get(3).getID());
    	assertEquals(5, path.get(3).getTimestamp());
    	assertEquals(3, path.get(4).getID());
    	assertEquals(7, path.get(4).getTimestamp());
    	assertEquals(4, path.get(5).getID());
    	assertEquals(7, path.get(5).getTimestamp());
    }

    @Test
	public void testQueryInfectionNoNode() {
		cm.createGraph();
		List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);
		
		path = cm.queryInfection(10, 4, 0, 10);
		assertEquals(null, path);
	}
	
	@Test
	public void testQueryInfectionNoPath() {
		cm.createGraph();
		List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);
		
		path = cm.queryInfection(1, 4, 2, 7);
		assertEquals(null, path);
	}

    @Test
    public void createGraphExampleOne() {
        // Create graph from example 1
        monitor = createExample1();
        monitor.createGraph();

        assertEquals(2, monitor.getComputerMapping(1).size());
    	assertEquals(2, monitor.getComputerMapping(2).size());
    	assertEquals(1, monitor.getComputerMapping(3).size());
    	assertEquals(2, monitor.getComputerMapping(4).size());

        // Test C1 HashMap
        List<ComputerNode> c1Mapping = monitor.getComputerMapping(1);
        assertEquals(2, c1Mapping.size());
        ComputerNode c1Four = c1Mapping.get(0);
        ComputerNode c1Twelve = c1Mapping.get(1);
        assertEquals(1, c1Four.getID());
        assertEquals(4, c1Four.getTimestamp());
        assertEquals(1, c1Twelve.getID());
        assertEquals(12, c1Twelve.getTimestamp());

        // Test (C1, 4) Neighbors
        assertEquals(2, c1Four.getOutNeighbors().size());
        assertEquals(2, c1Four.getOutNeighbors().get(0).getID());
        assertEquals(4, c1Four.getOutNeighbors().get(0).getTimestamp());
        assertEquals(1, c1Four.getOutNeighbors().get(1).getID());
        assertEquals(12, c1Four.getOutNeighbors().get(1).getTimestamp());

        // Test (C1, 12) Neighbors
        assertEquals(1, c1Twelve.getOutNeighbors().size());
        assertEquals(4, c1Twelve.getOutNeighbors().get(0).getID());
        assertEquals(12, c1Twelve.getOutNeighbors().get(0).getTimestamp());

        // Test C2 HashMap
        List<ComputerNode> c2Mapping = monitor.getComputerMapping(2);
        assertEquals(2, c2Mapping.size());
        ComputerNode c2Four = c2Mapping.get(0);
        ComputerNode c2Eight = c2Mapping.get(1);
        assertEquals(2, c2Four.getID());
        assertEquals(4, c2Four.getTimestamp());
        assertEquals(2, c2Eight.getID());
        assertEquals(8, c2Eight.getTimestamp());

        // Test (C2, 4) Neighbors
        assertEquals(2, c2Four.getOutNeighbors().size());
        assertEquals(1, c2Four.getOutNeighbors().get(0).getID());
        assertEquals(4, c2Four.getOutNeighbors().get(0).getTimestamp());
        assertEquals(2, c2Four.getOutNeighbors().get(1).getID());
        assertEquals(8, c2Four.getOutNeighbors().get(1).getTimestamp());

        // Test (C2, 8) Neighbors
        assertEquals(1, c2Eight.getOutNeighbors().size());
        assertEquals(4, c2Eight.getOutNeighbors().get(0).getID());
        assertEquals(8, c2Eight.getOutNeighbors().get(0).getTimestamp());

        // Test C3 HashMap
        List<ComputerNode> c3Mapping = monitor.getComputerMapping(3);
        assertEquals(1, c3Mapping.size());
        ComputerNode c3Eight = c3Mapping.get(0);
        assertEquals(3, c3Eight.getID());
        assertEquals(8, c3Eight.getTimestamp());

        // Test (C3, 8) Neighbors
        assertEquals(1, c3Eight.getOutNeighbors().size());
        assertEquals(4, c3Eight.getOutNeighbors().get(0).getID());
        assertEquals(8, c3Eight.getOutNeighbors().get(0).getTimestamp());

        // Test C4 HashMap
        List<ComputerNode> c4Mapping = monitor.getComputerMapping(4);
        assertEquals(2, c4Mapping.size());
        ComputerNode c4Eight = c4Mapping.get(0);
        ComputerNode c4Twelve = c4Mapping.get(1);
        assertEquals(4, c4Eight.getID());
        assertEquals(8, c4Eight.getTimestamp());
        assertEquals(4, c4Twelve.getID());
        assertEquals(12, c4Twelve.getTimestamp());

        // Test (C4, 8) Neighbors
        assertEquals(3, c4Eight.getOutNeighbors().size());
        assertEquals(3, c4Eight.getOutNeighbors().get(0).getID());
        assertEquals(8, c4Eight.getOutNeighbors().get(0).getTimestamp());
        assertEquals(2, c4Eight.getOutNeighbors().get(1).getID());
        assertEquals(8, c4Eight.getOutNeighbors().get(1).getTimestamp());
        assertEquals(4, c4Eight.getOutNeighbors().get(2).getID());
        assertEquals(12, c4Eight.getOutNeighbors().get(2).getTimestamp());

        // Test (C4, 12) Neighbors
        assertEquals(1, c4Twelve.getOutNeighbors().size());
        assertEquals(1, c4Twelve.getOutNeighbors().get(0).getID());
        assertEquals(12, c4Twelve.getOutNeighbors().get(0).getTimestamp());
    }

    @Test
    public void queryInfectionExampleOne() {
        // Create Example 1 graph
        monitor = createExample1();
        monitor.createGraph();

        // Test that C3 gets infected at time = 8 if C1 is infected at time = 2
        List<ComputerNode> infectedList = monitor.queryInfection(1, 3, 2, 8);
        assertEquals(5, infectedList.size());
        assertEquals(1, infectedList.get(0).getID());
        assertEquals(4, infectedList.get(0).getTimestamp());
        assertEquals(2, infectedList.get(1).getID());
        assertEquals(4, infectedList.get(1).getTimestamp());
        assertEquals(2, infectedList.get(2).getID());
        assertEquals(8, infectedList.get(2).getTimestamp());
        assertEquals(4, infectedList.get(3).getID());
        assertEquals(8, infectedList.get(3).getTimestamp());
        assertEquals(3, infectedList.get(4).getID());
        assertEquals(8, infectedList.get(4).getTimestamp());
    }

    
    @Test
    public void queryInfectionExampleTwo() {
        // Create Example 2 graph
        monitor = createExample2();

        // If C1 is infected at time = 2, C3 does not get infected in the time
        // observed...
        List<ComputerNode> infectedList = monitor.queryInfection(1, 3, 2, 15);
        assertEquals(null, infectedList);

        // But C2 does
        infectedList = monitor.queryInfection(1, 2, 2, 15);
        assertEquals(3, infectedList.size());
        assertEquals(1, infectedList.get(0).getID());
        assertEquals(12, infectedList.get(0).getTimestamp());
        assertEquals(1, infectedList.get(1).getID());
        assertEquals(14, infectedList.get(1).getTimestamp());
        assertEquals(2, infectedList.get(2).getID());
        assertEquals(14, infectedList.get(2).getTimestamp());
    }

    @Test
    public void getComputerMapping() {
        // Test that empty HashMap is initialized on object creation
    	monitor = new CommunicationsMonitor();
        assertEquals(0, monitor.getComputerMapping().size());

        // Insert tuple and create graph
        monitor.addCommunication(1, 2, 4);
        monitor.createGraph();

        // Test that mapping has been updated
        assertEquals(2, monitor.getComputerMapping().size());
    }

    @Test
    public void getComputerMapping3() {
        // Invalid key value should return null list
    	monitor = new CommunicationsMonitor();
        assertEquals(null, monitor.getComputerMapping(1));

        // Insert tuple and create graph
        monitor.addCommunication(1, 2, 4);
        monitor.createGraph();

        // Test that mapping has been updated
        assertEquals(1, monitor.getComputerMapping(1).size());
        assertEquals(null, monitor.getComputerMapping(3));
    }

    
    @Test
 	public void testCaseOne() {
     monitor = new CommunicationsMonitor();
	 monitor.addCommunication(1, 2, 5);
	 monitor.addCommunication(1, 3, 6);
	 monitor.addCommunication(1, 4, 7);
	 monitor.addCommunication(3, 4, 8);
	 monitor.addCommunication(2, 3, 10);
	
     monitor.createGraph();
	 //List<ComputerNode> infectionPath = monitor.queryInfection(1, 2, 5, 5);
//	 assertEquals(1, infectionPath.get(0).getID());
//	 assertEquals(5, infectionPath.get(0).getTimestamp());
//	 assertEquals(2, infectionPath.get(1).getID());
//	 assertEquals(5, infectionPath.get(1).getTimestamp());
//	 assertEquals(2, infectionPath.size());
//	 
//	 infectionPath = monitor.queryInfection(1, 3, 6, 6);
//	 assertEquals(1, infectionPath.get(0).getID());
//	 assertEquals(6, infectionPath.get(0).getTimestamp());
//	 assertEquals(3, infectionPath.get(1).getID());
//	 assertEquals(6, infectionPath.get(1).getTimestamp());
//	 assertEquals(2, infectionPath.size());
	 
     List<ComputerNode> infectionPath = monitor.queryInfection(1, 4, 5, 7);
	 assertEquals(1, infectionPath.get(0).getID());
	 assertEquals(5, infectionPath.get(0).getTimestamp());
	 assertEquals(1, infectionPath.get(1).getID());
	 assertEquals(6, infectionPath.get(1).getTimestamp());
	 assertEquals(1, infectionPath.get(2).getID());
	 assertEquals(7, infectionPath.get(2).getTimestamp());
	 assertEquals(4, infectionPath.get(3).getID());
	 assertEquals(7, infectionPath.get(3).getTimestamp());
	 assertEquals(4, infectionPath.size());
	 
	 infectionPath = monitor.queryInfection(1, 4, 6, 8);
	 assertEquals(1, infectionPath.get(0).getID());
	 assertEquals(6, infectionPath.get(0).getTimestamp());
	 assertEquals(3, infectionPath.get(1).getID());
	 assertEquals(6, infectionPath.get(1).getTimestamp());
	 assertEquals(3, infectionPath.get(2).getID());
	 assertEquals(8, infectionPath.get(2).getTimestamp());
	 assertEquals(4, infectionPath.get(3).getID());
	 assertEquals(8, infectionPath.get(3).getTimestamp());
	 assertEquals(4, infectionPath.size());
	 
	 infectionPath = monitor.queryInfection(1, 2, 6, 10);
	 assertEquals(1, infectionPath.get(0).getID());
	 assertEquals(6, infectionPath.get(0).getTimestamp());
	 assertEquals(3, infectionPath.get(1).getID());
	 assertEquals(6, infectionPath.get(1).getTimestamp());
	 assertEquals(3, infectionPath.get(2).getID());
	 assertEquals(8, infectionPath.get(2).getTimestamp());
	 assertEquals(3, infectionPath.get(3).getID());
	 assertEquals(10, infectionPath.get(3).getTimestamp());
	 assertEquals(2, infectionPath.get(4).getID());
	 assertEquals(10, infectionPath.get(4).getTimestamp());
	 assertEquals(5, infectionPath.size());
	 
	 
 }
 	
	@Test
 	public void testCaseTwo() {
	 monitor.addCommunication(1, 2, 1);
	 monitor.addCommunication(2, 3, 2);
	 monitor.addCommunication(11, 2, 1);
	 monitor.addCommunication(13, 15, 2);
	 monitor.addCommunication(3, 5, 3);
	 monitor.addCommunication(4, 8, 4);
	 monitor.addCommunication(9, 14, 3);
	 monitor.addCommunication(5, 7, 5);
	 monitor.addCommunication(2, 10, 6);
	 monitor.addCommunication(3, 14, 8);
	 monitor.addCommunication(4, 5, 9);
	 monitor.addCommunication(7, 8, 10);
	 monitor.addCommunication(6, 15, 11);
	 monitor.addCommunication(10, 12, 12);
	 monitor.createGraph();
	 
	 List<ComputerNode> infectionPath = monitor.queryInfection(1, 14, 1, 8);
	 assertEquals(1, infectionPath.get(0).getID());
	 assertEquals(1, infectionPath.get(0).getTimestamp());
	 assertEquals(2, infectionPath.get(1).getID());
	 assertEquals(1, infectionPath.get(1).getTimestamp());
	 assertEquals(2, infectionPath.get(2).getID());
	 assertEquals(2, infectionPath.get(2).getTimestamp());
	 assertEquals(3, infectionPath.get(3).getID());
	 assertEquals(2, infectionPath.get(3).getTimestamp());
	 assertEquals(3, infectionPath.get(4).getID());
	 assertEquals(3, infectionPath.get(4).getTimestamp());
	 assertEquals(3, infectionPath.get(5).getID());
	 assertEquals(8, infectionPath.get(5).getTimestamp());
	 assertEquals(14, infectionPath.get(6).getID());
	 assertEquals(8, infectionPath.get(6).getTimestamp());
	 assertEquals(7, infectionPath.size());
	 
	 infectionPath = monitor.queryInfection(2, 12, 6, 12);
	 assertEquals(2, infectionPath.get(0).getID());
	 assertEquals(6, infectionPath.get(0).getTimestamp());
	 assertEquals(10, infectionPath.get(1).getID());
	 assertEquals(6, infectionPath.get(1).getTimestamp());
	 assertEquals(10, infectionPath.get(2).getID());
	 assertEquals(12, infectionPath.get(2).getTimestamp());
	 assertEquals(12, infectionPath.get(3).getID());
	 assertEquals(12, infectionPath.get(3).getTimestamp());
	 assertEquals(4, infectionPath.size());
	 
	 infectionPath = monitor.queryInfection(13, 6, 2, 11);
	 assertEquals(13, infectionPath.get(0).getID());
	 assertEquals(2, infectionPath.get(0).getTimestamp());
	 assertEquals(15, infectionPath.get(1).getID());
	 assertEquals(2, infectionPath.get(1).getTimestamp());
	 assertEquals(15, infectionPath.get(2).getID());
	 assertEquals(11, infectionPath.get(2).getTimestamp());
	 assertEquals(6, infectionPath.get(3).getID());
	 assertEquals(11, infectionPath.get(3).getTimestamp());
	 assertEquals(4, infectionPath.size());
	 
	 infectionPath = monitor.queryInfection(13, 10, 2, 12);
	 assertEquals(null, infectionPath);
	 
	}
 	
 	
    
    //--------------------------------------------------------------------------
    // Helper Methods
    //--------------------------------------------------------------------------
    
    /**
     * Creates and returns the CommunicationsMonitor from Example 1 in the PDF
     * @return Example 1 CommunicationsMonitor
     */
    private CommunicationsMonitor createExample1() {
    	CommunicationsMonitor example1 = new CommunicationsMonitor();
    	example1.addCommunication(1, 2, 4);
    	example1.addCommunication(2, 4, 8);
    	example1.addCommunication(3, 4, 8);
    	example1.addCommunication(1, 4, 12);
    	return example1;
    }
    
    /**
     * Creates and returns the CommunicationsMonitor from Example 2 in the PDF
     * @return Example 2 CommunicationsMonitor
     */
    private CommunicationsMonitor createExample2() {
        CommunicationsMonitor example2 = new CommunicationsMonitor();
        example2.addCommunication(2, 3, 8);
        example2.addCommunication(1, 4, 12);
        example2.addCommunication(1, 2, 14);
        return example2;
    }
    
    private CommunicationsMonitor createExample3() {
    	CommunicationsMonitor example3 = new CommunicationsMonitor();
        example3.addCommunication(2, 3, 8);
        example3.addCommunication(1, 2, 14);
        example3.addCommunication(2, 4, 8);
        return example3;
    }
    
    private CommunicationsMonitor createExample4() {
    	CommunicationsMonitor example4 = new CommunicationsMonitor();
    	example4.addCommunication(1, 2, 4);
    	example4.addCommunication(2, 3, 5);
    	example4.addCommunication(3, 4, 7);
    	return example4;
    }
    
    /**
     * Creates and returns 
     * @return Sorted ArrayList
     */
    private CommunicationsMonitor createRandomTimeStampList() {
    	CommunicationsMonitor randomTS = new CommunicationsMonitor();
    	randomTS.addCommunication(1, 2, 4);
    	randomTS.addCommunication(2, 4, 12);
    	randomTS.addCommunication(3, 4, 5);
    	randomTS.addCommunication(1, 4, 14);
    	randomTS.addCommunication(1, 4, 2);
    	return randomTS;
    	
    }
    
    private CommunicationsMonitor createIdenticalComputerNodes(){
    	CommunicationsMonitor example4 = new CommunicationsMonitor();
    	example4.addCommunication(1, 2, 4);
    	example4.addCommunication(1, 3, 4);
    	example4.addCommunication(1, 4, 4);
    	example4.addCommunication(1, 5, 4);
    	example4.addCommunication(1, 6, 4);
    	return example4;
    }
    
    private CommunicationsMonitor EmptyComputerNodeList(){
    	CommunicationsMonitor example4 = new CommunicationsMonitor();
    	return example4;
    }
}
