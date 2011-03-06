
// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class NewWorkflow {

	private Browser browser;

	public NewWorkflow(Browser browser) {
		this.browser = browser;
	}

	public void test() throws Exception {
		browser.navigateTo("http://www.google.com");
		
	}

}
