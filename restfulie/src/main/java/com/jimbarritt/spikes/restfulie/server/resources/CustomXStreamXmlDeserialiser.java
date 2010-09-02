package com.jimbarritt.spikes.restfulie.server.resources;

import br.com.caelum.vraptor.deserialization.*;
import br.com.caelum.vraptor.http.*;
import br.com.caelum.vraptor.ioc.*;
import br.com.caelum.vraptor.resource.*;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.*;

import java.io.*;
import java.lang.reflect.*;

@ApplicationScoped
@Component
public class CustomXStreamXmlDeserialiser implements XMLDeserializer {

    private final ParameterNameProvider provider;

    public CustomXStreamXmlDeserialiser(ParameterNameProvider provider) {
        this.provider = provider;
    }

    public Object[] deserialize(InputStream inputStream, ResourceMethod method) {
        Method javaMethod = method.getMethod();
        Class<?>[] types = javaMethod.getParameterTypes();
        if (types.length == 0) {
            throw new IllegalArgumentException("Methods that consumes xml must receive just one argument: the xml root element");
        }
        XStream xStream = getConfiguredXStream(javaMethod, types);

        Object[] params = new Object[types.length];

        chooseParam(types, params, xStream.fromXML(inputStream));

        return params;
    }

    /**
     * Returns an xstream instance already configured.
     */
    public XStream getConfiguredXStream(Method javaMethod, Class<?>[] types) {
        XStream xStream = getXStream();
        aliasParams(javaMethod, types, xStream);
        return xStream;
    }

    private void chooseParam(Class<?>[] types, Object[] params, Object deserialized) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].isInstance(deserialized)) {
                params[i] = deserialized;
            }
        }
    }

    private void aliasParams(Method method, Class<?>[] types, XStream deserializer) {
        String[] names = provider.parameterNamesFor(method);
        for (int i = 0; i < names.length; i++) {
            deserializer.alias(names[i], types[i]);
        }
    }

    /**
     * Extension point to configure your xstream instance.
     *
     * @return the configured xstream instance
     */
    protected XStream getXStream() {
        return new XStream(new DomDriver());
	}

}
