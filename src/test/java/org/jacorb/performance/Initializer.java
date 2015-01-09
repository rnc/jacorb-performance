package org.jacorb.performance;

import org.jacorb.orb.portableInterceptor.ORBInitInfoImpl;
import org.omg.CORBA.INITIALIZE;
import org.omg.CORBA.LocalObject;
import org.omg.PortableInterceptor.ORBInitInfo;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.omg.PortableInterceptor.ORBInitializer;

public final class Initializer extends LocalObject implements ORBInitializer
{
    @Override
    public void pre_init(ORBInitInfo info)
    {
        ORBInitInfoImpl infoImpl = (ORBInitInfoImpl) info;
        try
        {
            info.add_client_request_interceptor(new ClientRequestInterceptorImpl(
                    "ClientRequestInterceptor", infoImpl.getORB()));
            info.add_server_request_interceptor(new ServerRequestInterceptorImpl(
                    "ServerRequestInterceptor", infoImpl.getORB()));
        }
        catch (DuplicateName e)
        {
            String message = "Unexpected error registering interceptors";
            throw new INITIALIZE(message);
        }
    }

    @Override
    public void post_init(ORBInitInfo info)
    {
    }
}
