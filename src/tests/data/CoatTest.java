package tests.data;

import static org.junit.Assert.*;

import data.Coat;

import org.junit.Test;

public class CoatTest {

	@Test
	public void testCoat() {
		Coat coat = new Coat();
		assertEquals(coat.getKind(), "Coat");
	}

}
