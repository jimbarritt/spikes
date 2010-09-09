package com.jimbarritt.spikes.restfulie.server.domain;

import br.com.caelum.vraptor.ioc.*;

import static java.lang.String.format;

@Component
@ApplicationScoped
public class LocationRepository {
    
    public Location get(int number) {
        Location nextLocation = new Location(33, "Next location");
        return new Location(number, format("This is my location @%d", number)).withExitTo(nextLocation);
    }
}