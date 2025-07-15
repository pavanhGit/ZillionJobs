package comproject.jobApp.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("---------- Incoming Request ----------");
        System.out.println("URL     : " + req.getRequestURL());
        System.out.println("Method  : " + req.getMethod());
        System.out.println("Headers : " + req.getHeaderNames().asIterator().toString());
        System.out.println("--------------------------------------");

        chain.doFilter(request, response); // continue the chain

        System.out.println("---------- Outgoing Response ----------");
        System.out.println("Status  : " + res.getStatus());
        System.out.println("---------------------------------------");
    }
}

