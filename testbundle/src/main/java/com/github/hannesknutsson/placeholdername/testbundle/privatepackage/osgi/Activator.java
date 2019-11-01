package com.github.hannesknutsson.placeholdername.testbundle.privatepackage.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

public class Activator implements BundleActivator
{

    private volatile LoggerFactory loggerFactory;
    private Logger LOG;

    public void start(BundleContext context)
    {
        try {
            System.out.println("Start of activator");
            System.out.println("Getting service reference...");
            ServiceReference ref = context.getServiceReference(LoggerFactory.class.getName());
            if (ref != null)
            {
                System.out.println("Found a LoggerFactory implementation!");
                loggerFactory = (LoggerFactory) context.getService(ref);
            } else {
                System.out.println("Did not find a LoggerFactory implementation :(");
            }

            System.out.println("Trying to get a logger...");
            LOG = loggerFactory.getLogger(this.getClass());
            System.out.println("Trying to log on audit level...");
            LOG.audit("Hello logging world!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void stop(BundleContext context) {
        System.out.println("We just got removed!");
        LOG.audit("We just got deactivated!");
    }
}
