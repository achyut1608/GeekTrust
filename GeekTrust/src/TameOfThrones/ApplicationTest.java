package TameOfThrones;

import org.junit.Test;

public class ApplicationTest {
	Application applicationTest = new Application();
	
	/*
	 * @Test public void allKindomSupportedScenario() {
	 * application.sendingMessage(); }
	 * 
	 * @Test public void allKingdomNotSupportedScenario() {
	 * application.sendingMessage(); }
	 * 
	 * @Test public void threeKingdomSupportedScenario() {
	 * application.sendingMessage(); }
	 * 
	 * @Test public void lessthan3KingdomSupportedScenario() {
	 * application.sendingMessage(); }
	 */
	
	@Test
	public void wrongKingdomName() {
		applicationTest.sendingMessage();
	}
	
	@Test
	public void wrongMessageSend() {
		applicationTest.sendingMessage();
	}
}
