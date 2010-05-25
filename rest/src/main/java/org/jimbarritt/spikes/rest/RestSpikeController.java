package org.jimbarritt.spikes.rest;

import org.apache.log4j.*;
import org.jimbarritt.spikes.rest.logging.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RestSpikeController extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String contextPath = httpServletRequest.getContextPath();

    }
}
