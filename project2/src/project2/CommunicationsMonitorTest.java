package project2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CommunicationsMonitorTest {

	@Test
	void addTest() {
		CommunicationsMonitor c = new CommunicationsMonitor();
		c.addCommunication(1, 2, 4);
		c.addCommunication(2, 3, 5);
	}

}
