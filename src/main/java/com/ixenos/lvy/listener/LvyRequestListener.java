package com.ixenos.lvy.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 * Application Lifecycle Listener implementation class TEt
 *
 */
@WebListener
public class LvyRequestListener implements ServletRequestListener {

    /**
     * Default constructor. 
     */
    public LvyRequestListener() {
    }
       
    /**
     * @see HttpSessionEvent#HttpSessionEvent(HttpSession)
     */
    public LvyRequestListener(HttpSession source) {
        super();
    }

    /**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
    	
    }

    /**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
    }

	
}
