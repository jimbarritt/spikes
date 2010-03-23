package org.jimbarritt.spikes.rest;

import org.apache.log4j.*;
import org.jimbarritt.spikes.rest.logging.*;
import org.jimbarritt.spikes.rest.collection.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class RestSpikeController extends HttpServlet {
    private static final Logger log = Logger.getLogger(RestSpikeController.class);

    private HttpServletRequestFormat httpServletRequestFormat = new HttpServletRequestFormat();    

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String contextPath = httpServletRequest.getContextPath();

    }
}
