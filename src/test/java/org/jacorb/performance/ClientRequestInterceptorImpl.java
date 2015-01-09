package org.jacorb.performance;

import org.omg.CORBA.LocalObject;
import org.omg.CORBA.ORB;
import org.omg.PortableInterceptor.ClientRequestInfo;
import org.omg.PortableInterceptor.ClientRequestInterceptor;
import org.omg.PortableInterceptor.ForwardRequest;

final public class ClientRequestInterceptorImpl extends LocalObject implements
        ClientRequestInterceptor
{
    private String name;
    private ORB orb;

    public ClientRequestInterceptorImpl(String string, ORB orb)
    {
        this.name = string;
        this.orb = orb;
    }

    @Override
    public void send_request(ClientRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void receive_exception(ClientRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void receive_other(ClientRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void receive_reply(ClientRequestInfo ri)
    {
    }

    @Override
    public void send_poll(ClientRequestInfo ri)
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public String name()
    {
        return this.name;
    }

}