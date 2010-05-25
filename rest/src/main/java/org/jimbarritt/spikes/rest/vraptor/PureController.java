package org.jimbarritt.spikes.rest.vraptor;

import br.com.caelum.vraptor.*;

import javax.annotation.*;
import javax.annotation.Resource;


@Resource
@Path("/pure-vraptor")
public class PureController {

    @Path("/getRepresentation")
    public String getARepresentation() {
        return "This is my representation";        
    }
}
