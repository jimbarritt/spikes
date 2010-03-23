package org.jimbarritt.spikes.rest;

import org.jimbarritt.spikes.rest.logging.*;
import org.apache.log4j.*;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class HtmlFormController extends HttpServlet {
    private static final Logger log = Logger.getLogger(HtmlFormController.class);

    private HttpServletRequestFormat httpServletRequestFormat = new HttpServletRequestFormat();

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        log.info(httpServletRequestFormat.toString(httpServletRequest));
        super.service(httpServletRequest, httpServletResponse);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(400);
        response.getWriter().write("<h1>You failed validation</h1>");
        response.getWriter().flush();
    }
}
