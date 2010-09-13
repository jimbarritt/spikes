package com.jimbarritt.spikes.restfulie.server.domain;

import br.com.caelum.vraptor.ioc.*;
import com.jimbarritt.spikes.restfulie.logging.*;

import java.util.*;
import java.util.regex.*;

import static com.jimbarritt.spikes.restfulie.io.Iox.*;
import static java.lang.String.format;

@Component
@ApplicationScoped
public class LocationRepository {

    private static final StringFormatLogger log = StringFormatLogger.getStringFormatLogger(LocationRepository.class);

    public Location get(int number) {
        String locationDefinition = loadClasspathResourceAsString(format("locations/%d.location", number));

        LocationDefinitionParser parser = new LocationDefinitionParser(locationDefinition);
        parser.parse();


        LocationDescription description = new LocationDescription().appendText(parser.parsedDescription);

        Location location = new Location(number, description);
        for (Integer locationNumber : parser.exitNumbers()) {
            location.withExitTo(locationNumber);
        }
        return location;
    }

    private static class LocationDefinitionParser {
        private final String locationDefinition;
        private final Pattern pattern = Pattern.compile("\\{[0-9]*\\}");
        private ArrayList<Integer> ids;
        private String parsedDescription;

        private LocationDefinitionParser(String locationDefinition) {
            this.locationDefinition = locationDefinition;
        }

        public void parse() {
            Matcher matcher = pattern.matcher(locationDefinition);

            ids  = new ArrayList<Integer>();
            while (matcher.find()) {
                ids.add(Integer.parseInt(matcher.group().replaceAll("\\{|\\}", "")));
            }

            String output = locationDefinition;
            for (int i=0; i<ids.size(); ++i) {
                log.info("i=%d", i);
                String pattern = format("\\{%s\\}", ids.get(i));
                String replacement = format("\\{%d\\}", i);
                output = output.replaceAll(pattern, replacement);
            }

            this.parsedDescription = output;
        }

        public List<Integer> exitNumbers() {
            return this.ids;
        }

        public String parsedDescription() {
            return this.parsedDescription;
        }

    }
}