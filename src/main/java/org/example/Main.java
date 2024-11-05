package org.example;

import com.fastcgi.FCGIInterface;
import org.example.exceptions.IncorrectMethodException;
import org.example.exceptions.ParamsValidationException;
import org.example.http.RequestHandler;
import org.example.http.ResponseBuilder;
import org.example.utils.Validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        var fcgiInterface = new FCGIInterface();
        while (fcgiInterface.FCGIaccept() >= 0) {
            try {
                Properties params = FCGIInterface.request.params;

                if (!RequestHandler.checkRequestMethod(params)) {
                    throw new IncorrectMethodException();
                }

                long time = LocalDateTime.now().getNano();
                HashMap<String, String> gotVars = RequestHandler.parseRequestParams(params);
                boolean res = Validator.getResult(gotVars);

                System.out.println(ResponseBuilder.buildSuccessResponse(res, gotVars, LocalDateTime.now().getNano() - time));
            } catch (IncorrectMethodException e) {
                System.out.println(ResponseBuilder.buildFailResponse(406, e.getMessage()));
            } catch (ParamsValidationException e) {
                System.out.println(ResponseBuilder.buildFailResponse(400, e.getMessage()));
            } catch (NullPointerException e) {
                System.out.println(ResponseBuilder.buildFailResponse(404, "Not Found"));
            }
        }
    }
}