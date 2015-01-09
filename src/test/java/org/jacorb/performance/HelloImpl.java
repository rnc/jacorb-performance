package org.jacorb.performance;

import org.jacorb.test.harness.TestUtils;

public final class HelloImpl extends HelloPOA
{
    @Override
    public void inputString(String s1)
    {

    }

    @Override
    public void inputData(Data[] ds1)
    {

    }

    @Override
    public void sayHello()
    {

    }

    @Override
    public void sayGoodbye()
    {
        String bye = "Good Bye, World!";
        TestUtils.getLogger().debug(bye);
    }

}
