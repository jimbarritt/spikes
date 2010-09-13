package com.jimbarritt.spikes.restfulie.functionaltest;

import br.com.caelum.restfulie.*;
import br.com.caelum.restfulie.mediatype.*;
import com.jimbarritt.spikes.restfulie.client.*;
import com.jimbarritt.spikes.restfulie.client.Player;
import com.jimbarritt.spikes.restfulie.logging.*;
import org.junit.*;

import static com.jimbarritt.spikes.restfulie.logging.StringFormatLogger.getStringFormatLogger;
import static java.lang.String.format;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

public class XmlPlayerTest {

    private static final StringFormatLogger log = getStringFormatLogger(XmlLocationTest.class);

      private RestClient restfulie;
      private RemoteApplication remoteApplication;

      @Before
      public void setUp() throws Exception {
          restfulie = Restfulie.custom();
          restfulie.getMediaTypes().register(new XmlMediaType()
                                                      .withTypes(Player.class));
          remoteApplication = new RemoteApplication();
      }

      @Test
      public void canGetAPlayerRepresentation() throws Exception {
          Response response = restfulie.at(remoteApplication.uriForPath("/players/666"))
                                       .accept("application/xml")
                                       .get();
          log.info(format("Response was \n%s", response.getContent()));
          Player player = response.getResource();

          assertNotNull(player);
          assertThat(player.toString(), containsString("Johnny Foo"));
          assertThat(response.getCode(), is(200));

          log.info(format("Current Player : %s", player));

      }

      @Test
      public void canPutAPlayer() throws Exception {
          Player player = new Player("Me foo too");
          Response response = restfulie.at(remoteApplication.uriForPath("/players/666"))
                                               .as("application/xml")
                                               .put(player);

          assertThat(response.getCode(), is(200));
          log.info(format("Response was (%d)", response.getCode()));

      }

}