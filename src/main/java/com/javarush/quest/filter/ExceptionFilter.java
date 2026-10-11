package com.javarush.quest.filter;

import com.javarush.quest.config.constant.Schema.Jsp;
import com.javarush.quest.config.constant.Schema.Url;
import com.javarush.quest.exception.AppException;
import jakarta.annotation.Priority;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.HttpURLConnection;

@Slf4j
@Priority(1)
@WebFilter(Url.WILDCARD)
public class ExceptionFilter implements Filter {
    private static final String ERROR_JSP = String.format(Jsp.FORMAT, Jsp.ERROR);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
        } catch (AppException e) {
            String message = "Client request failed. Status: {}, Reason: {}";
            log.warn(message, e.getHttpStatus(), e.getMessage());
            resp.setStatus(e.getHttpStatus());
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        } catch (Throwable t) {
            log.error("Critical system error occurred during request execution.", t);
            resp.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
            req.getRequestDispatcher(ERROR_JSP).forward(req, resp);
        }
    }
}
