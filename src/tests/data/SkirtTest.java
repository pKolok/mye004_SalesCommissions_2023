package tests.data;

import static org.junit.Assert.*;

import data.Skirt;

import org.junit.Test;

public class SkirtTest {

	@Test
	public void testSkirt() {
		Skirt skirt = new Skirt();
		assertEquals(skirt.getKind(), "Skirt");
	}

}