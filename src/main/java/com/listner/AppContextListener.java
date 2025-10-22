package com.listner;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    private ExecutorService executorService;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Create a fixed thread pool and store it in ServletContext
        executorService = Executors.newFixedThreadPool(5);
        sce.getServletContext().setAttribute("executorService", executorService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Shutdown the pool gracefully when app stops
        executorService.shutdown();
    }
}
