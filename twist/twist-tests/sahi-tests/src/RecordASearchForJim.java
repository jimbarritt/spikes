import net.sf.sahi.client.Browser;
import static junit.framework.Assert.*;

public class RecordASearchForJim {

	private Browser browser;

	public RecordASearchForJim(Browser browser) {
		this.browser = browser;
	}

	public void performGoogleSearchFor(String searchExpression) throws Exception {
		browser.navigateTo("http://www.google.com");		
		browser.textbox("q").setValue(searchExpression);
		browser.button("Google Search").click();
		browser.link("non-random ramble").click();
		assertTrue(browser.link("non-random ramble").exists());		
	}

}
