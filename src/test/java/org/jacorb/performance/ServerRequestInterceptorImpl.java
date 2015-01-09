package org.jacorb.performance;

import org.jacorb.orb.ORB;
import org.omg.CORBA.LocalObject;
import org.omg.PortableInterceptor.ForwardRequest;
import org.omg.PortableInterceptor.ServerRequestInfo;
import org.omg.PortableInterceptor.ServerRequestInterceptor;

final public class ServerRequestInterceptorImpl extends LocalObject implements
        ServerRequestInterceptor
{
    private String name;

    public ServerRequestInterceptorImpl(String string, ORB orb)
    {
        this.name = string;
    }

    @Override
    public void receive_request(ServerRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void receive_request_service_contexts(ServerRequestInfo ri)
            throws ForwardRequest
    {
    }

    @Override
    public void send_exception(ServerRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void send_other(ServerRequestInfo ri) throws ForwardRequest
    {
    }

    @Override
    public void send_reply(ServerRequestInfo ri)
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
