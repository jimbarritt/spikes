package com.jimbarritt.spikes.restfulie.server.domain;

import br.com.caelum.vraptor.ioc.*;

import static com.jimbarritt.spikes.restfulie.server.domain.LocationDescription.*;

@Component
@ApplicationScoped
public class LocationRepository {

    public Location get(int number) {
        Location westLocation = new Location(33, "The room to the west");
        Location eastLocation = new Location(44, "The room to the east");
        LocationDescription description = new LocationDescription()
                .appendText("You are in a dark room. Something smells fishy. ")
                .appendText("You could leave by the west door {0} or the east door {1}");
        return new Location(number, description)
                .withExitTo(westLocation)
                .withExitTo(eastLocation);
    }
}