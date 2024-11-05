package org.example.http;

import org.example.exceptions.IncorrectMethodException;

import java.util.HashMap;
import java.util.Properties;

public class RequestHandler {
    private static final String requestMethod = "GET";

    public static boolean checkRequestMethod(Properties params) {
        return params.getProperty("REQUEST_METHOD").equals(requestMethod);
    }

    public static HashMap<String, String> parseRequestParams(Properties params) throws IncorrectMethodException {
        String request = params.getProperty("QUERY_STRING");
        String[] vars = request.split("&");
        HashMap<String, String> newMap = new HashMap<>();
        for (String v : vars) {
            String[] param = v.split("=");
            if (param.length != 2) {
                throw new NullPointerException();
            }
            newMap.put(param[0], param[1]);
        }
        return newMap;
    }
}
