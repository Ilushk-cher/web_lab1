package org.example.http;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ResponseBuilder {
    public static String buildSuccessResponse(boolean res, HashMap<String, String> vars, long workTime) {
        String x = vars.get("x");
        String y = vars.get("y");
        String r = vars.get("r");

        return """
                HTTP/1.1 200 OK
                Content-Type: application/json
                
                
                {"result":"%s","x":"%s","y":"%s","r":"%s","curTime":"%s","workTime":"%s"}
                """.formatted(res, x, y, r, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), workTime);
    }

    public static String buildFailResponse(int errorNumber, String message) {
        return "HTTP/1.1 %s %s".formatted(errorNumber, message);
    }
}
