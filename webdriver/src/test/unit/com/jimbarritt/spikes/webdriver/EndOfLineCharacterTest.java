package com.jimbarritt.spikes.webdriver;

import org.junit.*;
import org.openqa.selenium.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class EndOfLineCharacterTest {

    @Test
    public void convertsBreaksToEndLineCharacters() throws Exception {

        String html = "<html><body>"
                + "<p id='aParagraph'>Some text with <br/> a break.</p>"
                + "</body></html>";

        WebElement paragraphElement = StringWebDriver.loadElementByIdFromRepresentation(html, "aParagraph");
        assertThat(paragraphElement.getText(), is("Some text with " + System.getProperty("line.separator") + " a break."));

    }

}