package tests.data;

import static org.junit.Assert.*;

import data.Shirt;

import org.junit.Test;

public class ShirtTest {

	@Test
	public void testShirt() {
		Shirt shirt = new Shirt();
		assertEquals(shirt.getKind(), "Shirt");
	}

}