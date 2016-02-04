package rules;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DiskTest {
	Disk d;
	
	@Before
	public void setup() {
		d = new Disk();
	}

	@Test
	public void newDiskIsFree() {
		assertTrue(d.isFree());
	}

	@Test
	public void diskCanTurnWhite() {
		d.turnWhite();
		assertTrue(d.isWhite());
	}
	
	@Test
	public void diskCanTurnBlack() {
		d.turnBlack();
		assertTrue(d.isBlack());
	}
	
	@Test
	public void blackDiskIsNotWhite() {
		d.turnBlack();
		assertFalse(d.isWhite());
	}
	
	@Test
	public void whiteDiskIsNotBlack() {
		d.turnWhite();
		assertFalse(d.isBlack());
	}
}


