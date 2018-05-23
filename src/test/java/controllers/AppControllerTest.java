package controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ninja.NinjaTest;

public class AppControllerTest extends NinjaTest {

	@Test
	public void testIndex() {
		String result = ninjaTestBrowser.makeRequest(getServerAddress() + "/");
		assertTrue(result.contains("Add"));
	}

}
