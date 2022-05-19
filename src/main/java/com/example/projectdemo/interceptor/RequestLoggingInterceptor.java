package com.example.projectdemo.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Scanner;

public class RequestLoggingInterceptor implements HandlerInterceptor {

    private final String MY_PAGE_TIME_LOG_KEY = "TIME_LOG";

    private final String MY_PAGE_IMEI_LOG_KEY = "imei";

    private final Logger logger = LogManager.getLogger(RequestLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;

        request.setAttribute(MY_PAGE_TIME_LOG_KEY, System.currentTimeMillis());
        logger.info(method.getMethod().getDeclaringClass().getName() + " START "
                + request.getRequestURI() + " " + request.getMethod());

//        logger.info(request.getReader().lines().reduce("",String::concat));
//        System.out.println(extractPostRequestBody(request));
        logger.info("Request"  + request);

        return true;
    }

    private String extractPostRequestBody(HttpServletRequest request) {
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            Scanner s = null;
            try {
                s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.hasNext() ? s.next() : "";
        }
        return "";
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return;
        }

        HandlerMethod method = (HandlerMethod) handler;
        Long startTime = (Long) request.getAttribute(MY_PAGE_TIME_LOG_KEY);
        String imei = (String) request.getAttribute(MY_PAGE_IMEI_LOG_KEY);
        if (startTime != null) {
            long processTime = System.currentTimeMillis() - startTime;
            logger.info(method.getMethod().getDeclaringClass().getName() + " FINISH " + processTime + " msec");
        }
    }
}
