package org.example;

import com.fastcgi.FCGIInterface;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static List<Point> points = new ArrayList<>();

    public static void main(String[] args) {
        PrintStream cout = System.out;
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            Properties params = FCGIInterface.request.params;
            if (params.getProperty("REQUEST_METHOD").equals("GET")) {
                long time = LocalDateTime.now().getNano();
                String request = params.getProperty("QUERY_STRING");
                if (!Objects.equals(request, "")) {
                    try {
                        HashMap<String, String> qs = getParams(request);
                        double x = Double.parseDouble(qs.get("x"));
                        int y = Integer.parseInt(qs.get("y"));
                        int r = Integer.parseInt(qs.get("r"));
                        if (!validate(x, y, r)) throw new IllegalArgumentException();
                        System.out.println(
                                getResponse(check(x, y, r).toString(), qs.get("x"), qs.get("y"), qs.get("r"), LocalDateTime.now().getNano() - time)
                        );
                        cout.println(1);
                    } catch (IllegalArgumentException e) {
                        System.out.println("""
                        HTTP/1.1 406 Not Acceptable
                        """);
                    } catch (NullPointerException e) {
                        System.out.println("""
                        HTTP/1.1 400 Bad Request
                        """);
                    }
                }
            } else System.out.println("HTTP/1.1 404 Not Found");
        }
    }

    private static HashMap<String, String> getParams(String text) {
        String[] params = text.split("&");
        HashMap<String, String> newMap = new HashMap<>();
        for (String p : params) {
            String[] param = p.split("=");
            if (param.length != 2) {
                throw new NullPointerException();
            }
            newMap.put(param[0], param[1]);
        }
        return newMap;
    }

    private static String getResponse(String result, String x, String y, String r, long workTime) {
        return """
                HTTP/1.1 200 OK
                Content-Type: application/json
                
                
                {"result":"%s","x":"%s","y":"%s","r":"%s","curTime":"%s","workTime":"%s"}
                """.formatted(result, x, y, r, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")), workTime);
    }

    public static Boolean validate(double x, int y, int R) {
        return x >= -3 && x <= 5 && y >= -5 && y <= 3 && R >= 1 && R <= 5;
    }

    public static Boolean check(Double x, Integer y, Integer R) {
        boolean res;
        if (x >= 0 && y <= 0) {
            res = (x <= (double) R / 2 && y >= - R);
        } else if (x <= 0 && y <= 0) {
            res = (y >= -(x + R) / 2);
        } else if (x <= 0) {
            res = (x * x + y * y <= R * R);
        } else res = false;
        points.add(new Point(x, y, R, res));
        return res;
    }
}