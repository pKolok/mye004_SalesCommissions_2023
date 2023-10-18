package tests.data;

import static org.junit.Assert.*;

import data.Trouser;

import org.junit.Test;

public class TrouserTest {

	@Test
	public void testTrouser() {
		Trouser trouser = new Trouser();
		assertEquals(trouser.getKind(), "Trouser");
	}

}