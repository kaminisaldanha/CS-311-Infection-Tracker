package project2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CommunicationsMonitorTest {
	
	// Instance variables
    CommunicationsMonitor monitor;
    
    @Before
    public void initialize() {
        // Start each test with an empty CommunicationsMonitor
        monitor = new CommunicationsMonitor();
    }
    
    /*
     * Tests if the adjacency list is created properly using example 1 computer nodes
     */
    @Test
    public void testCreateGraph() {
    	monitor = createExample1();
    	monitor.mergeSortByTimestamp(monitor.getCompNodes());
    	monitor.createGraph();
    	
    	assertEquals(2, monitor.getGraph()[0].size());
    	assertEquals(2, monitor.getGraph()[1].size());
    	assertEquals(1, monitor.getGraph()[2].size());
    	assertEquals(2, monitor.getGraph()[3].size());
    }
    
    //Test if the ArrayList gets sorted by its Timestamp
    @Test
    public void sortArrayListbyTimestampTest() {
    	
    	//Test that Example 1 is sorted correctly
    	monitor = createExample1();
    	monitor.mergeSortByTimestamp(monitor.getCompNodes());
    	
    	assertEquals(4, monitor.getCompNodes().get(0).getTimestamp());
    	assertEquals(4, monitor.getCompNodes().get(1).getTimestamp());
    	assertEquals(8, monitor.getCompNodes().get(2).getTimestamp());
    	assertEquals(8, monitor.getCompNodes().get(3).getTimestamp());
    	assertEquals(8, monitor.getCompNodes().get(4).getTimestamp());
        assertEquals(8, monitor.getCompNodes().get(5).getTimestamp());
        assertEquals(12, monitor.getCompNodes().get(6).getTimestamp());
        assertEquals(12, monitor.getCompNodes().get(7).getTimestamp());
        
        //Test that Example 2 is sorted correctly
        monitor = createExample2();
        monitor.mergeSortByTimestamp(monitor.getCompNodes());
        
        assertEquals(8, monitor.getCompNodes().get(0).getTimestamp());
    	assertEquals(8, monitor.getCompNodes().get(1).getTimestamp());
    	assertEquals(12, monitor.getCompNodes().get(2).getTimestamp());
    	assertEquals(12, monitor.getCompNodes().get(3).getTimestamp());
    	assertEquals(14, monitor.getCompNodes().get(4).getTimestamp());
        assertEquals(14, monitor.getCompNodes().get(5).getTimestamp());
        
        //Test that a random time stamped node list is sorted correctly
        monitor = createRandomTimeStampList();
        monitor.mergeSortByTimestamp(monitor.getCompNodes());
        
        assertEquals(2, monitor.getCompNodes().get(0).getTimestamp());
    	assertEquals(2, monitor.getCompNodes().get(1).getTimestamp());
    	assertEquals(4, monitor.getCompNodes().get(2).getTimestamp());
    	assertEquals(4, monitor.getCompNodes().get(3).getTimestamp());
    	assertEquals(5, monitor.getCompNodes().get(4).getTimestamp());
        assertEquals(5, monitor.getCompNodes().get(5).getTimestamp());
        assertEquals(12, monitor.getCompNodes().get(6).getTimestamp());
        assertEquals(12, monitor.getCompNodes().get(7).getTimestamp());
        assertEquals(14, monitor.getCompNodes().get(8).getTimestamp());
        assertEquals(14, monitor.getCompNodes().get(9).getTimestamp());
      
    }
    
    @Test
    public void getComputerMapping() {
        // Test that empty HashMap is initialized on object creation
        assertEquals(0, monitor.getComputerMapping().size());

        // Insert tuple and create graph
        monitor.addCommunication(1, 2, 4);
        monitor.createGraph();

        // Test that mapping has been updated
        assertEquals(2, monitor.getComputerMapping().size());
    }

    @Test
    public void getComputerMapping1() {
        // Invalid key value should return null list
        assertEquals(null, monitor.getComputerMapping(1));

        // Insert tuple and create graph
        monitor.addCommunication(1, 2, 4);
        monitor.createGraph();

        // Test that mapping has been updated
        assertEquals(1, monitor.getComputerMapping(1).size());
        assertEquals(null, monitor.getComputerMapping(3));
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
}
