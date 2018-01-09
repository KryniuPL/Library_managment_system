package com.library.ssh;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

    private SSHConnection sshConnection;

    public MyContextListener()
    {
        super();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        System.out.println("Context initialized ... !");
        try {
            sshConnection=new SSHConnection();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            System.out.println("Error connecting to ssh server");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        System.out.println("Context destroyed ... !");
        sshConnection.closeSSH();
        System.out.println("Disconnected");
    }
}
