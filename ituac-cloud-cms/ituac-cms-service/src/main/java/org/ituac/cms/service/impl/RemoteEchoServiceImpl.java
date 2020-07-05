package org.ituac.cms.service.impl;

//import org.apache.dubbo.config.annotation.Service;
import org.ituac.api.cms.dubbo.RemoteEchoServiceDubbo;


//@Service
public class RemoteEchoServiceImpl implements RemoteEchoServiceDubbo {

    @Override
    public String echo(String title) {
        return "echo cms dubbo :" + title;
    }
}
