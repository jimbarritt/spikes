package org.jimbarritt.spikes.rest;

import org.jimbarritt.spikes.rest.logging.*;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;

public class EchoRequestServlet extends HttpServlet {

    private HttpServletRequestFormat httpServletRequestFormat = new HttpServletRequestFormat();

    @Override
    protected void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String response = httpServletRequestFormat.toString(httpServletRequest);
        
        PrintWriter writer = httpServletResponse.getWriter();
        writer.println("<html><pre>");
            writer.println(response);
        writer.println("</pre></html>");
        writer.flush();
    }
}
