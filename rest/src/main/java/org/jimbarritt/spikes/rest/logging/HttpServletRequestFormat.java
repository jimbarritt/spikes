package org.jimbarritt.spikes.rest.logging;

import org.apache.commons.io.*;

import javax.servlet.http.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class HttpServletRequestFormat {

    public String toString(HttpServletRequest httpServletRequest) throws IOException {
        StringBuilder sb = new StringBuilder();

        final String INDENT = "    ";

        sb.append("\n\nHttpServletRequest Received @ [").append(httpServletRequest.getRequestURL()).append("]:\n");
        printGetters(httpServletRequest, sb, INDENT, asHashSet("getReader", "getInputStream"));

        List<String> headerNames = asList(httpServletRequest.getHeaderNames());
        sb.append("\nHeaders (count=").append(headerNames.size()).append(") : \n");
        for (String name : headerNames) {
            sb.append(INDENT).append(padRight(name, 20)).append(" : ").append(httpServletRequest.getHeader(name)).append("\n");
        }

        List<String> parameterNames = asList(httpServletRequest.getParameterNames());
        sb.append("\nParameters (count=").append(parameterNames.size()).append(") : \n");
        for (String name : parameterNames) {
            sb.append(INDENT).append(padRight(name, 20)).append(" : [").append(formatValues(httpServletRequest.getParameterValues(name))).append("]\n");
        }

        List<String> attributeNames = asList(httpServletRequest.getAttributeNames());
        sb.append("\nAttributes (count=").append(attributeNames.size()).append(") : \n");
        for (String name : attributeNames) {
            sb.append(INDENT).append(padRight(name, 20)).append(" : ").append(httpServletRequest.getAttribute(name)).append("\n");
        }

        if (!"application/x-www-form-urlencoded".equals(httpServletRequest.getContentType())) {
            String requestBody = IOUtils.toString(httpServletRequest.getInputStream(), "UTF-8");
            sb.append("\nBody:\n------BEGIN BODY------\n[").append(requestBody).append("]\n------END BODY------\n");
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> asList(Enumeration enumeration) {
        List<T> list = new ArrayList<T>();

        while (enumeration.hasMoreElements()) {
            list.add((T) enumeration.nextElement());
        }
        return list;
    }

    private static <T> Set<T> asHashSet(T... elements) {
        return new HashSet<T>(Arrays.<T>asList(elements));
    }

    private static void printGetters(Object instance, StringBuilder sb, String indent, Set<String> ignoreMethods) {
        List<Method> getters = allGettersFor(instance.getClass());
        try {
            for (Method getter : getters) {
                if (!ignoreMethods.contains(getter.getName())) {
                    String formattedName = formatMethodName(getter.getName());
                    String value = formatValue(getter.invoke(instance));
                    sb.append(indent).append(padRight(formattedName, 20)).append(" : ").append(value).append("\n");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not debug request - Reflection exception: " + e.getMessage(), e);
        }
    }

    private static String formatValue(Object value) {
        if (value instanceof Enumeration) {
            return formatValues((Enumeration) value);
        }
        if (value instanceof Map) {
            return formatValues((Map) value);
        }
        if (value instanceof String[]) {
            return formatValues((String[]) value);
        }
        return value == null ? "" : value.toString();
    }

    private static String formatValues(String[] stringArray) {
        StringBuilder sb = new StringBuilder();
        for (String element : stringArray) {
            sb.append(element).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }

    private static String formatValues(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Object entryObject : map.entrySet()) {
            Map.Entry entry = (Map.Entry) entryObject;
            sb.append(entry.getKey()).append("=").append(formatValue(entry.getValue())).append(", ");
        }
        return sb.length() > 0 ? "{" + sb.substring(0, sb.length() - 2) + "}" : "{}";
    }

    private static String formatValues(Enumeration enumeration) {
        StringBuilder sb = new StringBuilder();
        while (enumeration.hasMoreElements()) {
            Object element = enumeration.nextElement();
            sb.append(element.toString()).append(", ");
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "[]";
    }

    private static String padRight(String text, int padLength) {
        if (text.length() == padLength) {
            return text;
        }
        if (text.length() > padLength) {
            return text.substring(0, padLength);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(text);
        for (int i = text.length(); i < padLength; ++i) {
            sb.append(" ");
        }
        return sb.toString();
    }


    private static String formatMethodName(String name) {
        String formatted = name;
        if (name.startsWith("get")) {
            formatted = name.substring(3);
        }
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }

    private static List<Method> allGettersFor(Class<?> beanClass) {
        Method[] methodArray = beanClass.getMethods();

        List<Method> methods = new ArrayList<Method>();
        for (Method method : methodArray) {
            if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
                methods.add(method);
            }
        }
        return methods;
    }

}
