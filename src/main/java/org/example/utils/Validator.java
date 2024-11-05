package org.example.utils;

import org.example.exceptions.ParamsValidationException;

import java.util.HashMap;

public class Validator {
    public static boolean getResult(HashMap<String, String> vars) throws ParamsValidationException {
        double x = Double.parseDouble(vars.get("x"));
        int y = Integer.parseInt(vars.get("y"));
        int r = Integer.parseInt(vars.get("r"));

        if (!validate(x, y, r)) {
            throw new ParamsValidationException();
        }

        return checkPoint(x, y, r);
    }
    private static boolean validate(double x, int y, int r) {
        return (x >= -3 && x <= 5 && y >= -5 && y <= 3 && r >= 1 && r <= 5);
    }

    public static Boolean checkPoint(Double x, Integer y, Integer r) {
        boolean res;
        if (x >= 0 && y <= 0) {
            res = (x <= (double) r / 2 && y >= - r);
        } else if (x <= 0 && y <= 0) {
            res = (y >= -(x + r) / 2);
        } else if (x <= 0) {
            res = (x * x + y * y <= r * r);
        } else res = false;
        return res;
    }
}
