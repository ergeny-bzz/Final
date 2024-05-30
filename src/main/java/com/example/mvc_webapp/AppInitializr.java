package com.example.mvc_webapp;

import com.example.mvc_webapp.JpaConfig;
import com.example.mvc_webapp.RestConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.glassfish.jersey.servlet.WebConfig;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppInitializr implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {

        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();

        context.register(JpaConfig.class);
        context.register(RestConfig.class);

        servletContext.addListener(new ContextLoaderListener(context));
        servletContext.setInitParameter(
                "contextConfigLocation", "com.example.mvc_webapp");
    }
}