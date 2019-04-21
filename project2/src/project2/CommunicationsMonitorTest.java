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
    
    @Test
    public void testCreateGraph() {
    	monitor = createExample1();
    	monitor.mergeSort(monitor.getCompNodes());
    	monitor.createGraph();
    }
    
    //Test if the ArrayList gets sorted by its Timestamp
    @Test
    public void sortArrayListbyTimestampTest() {
    	monitor = createExample1();
    	monitor.mergeSort(monitor.getCompNodes());
    	
    	System.out.println(monitor.getCompNodes().get(0).getTimestamp());
    	assertEquals(4, monitor.getCompNodes().get(0).getTimestamp());
        assertEquals(8, monitor.getCompNodes().get(1).getTimestamp());
        assertEquals(8, monitor.getCompNodes().get(2).getTimestamp());
        assertEquals(12,monitor.getCompNodes().get(3).getTimestamp());
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
    private CommunicationsMonitor createExampleTwo() {
        CommunicationsMonitor example2 = new CommunicationsMonitor();
        example2.addCommunication(2, 3, 8);
        example2.addCommunication(1, 4, 12);
        example2.addCommunication(1, 2, 14);
        example2.createGraph();
        return example2;
    }
}
