package org.jacorb.performance;

import com.carrotsearch.junitbenchmarks.BenchmarkOptions;
import com.carrotsearch.junitbenchmarks.BenchmarkRule;
import com.carrotsearch.junitbenchmarks.Clock;
import com.carrotsearch.junitbenchmarks.annotation.AxisRange;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkHistoryChart;
import com.carrotsearch.junitbenchmarks.annotation.BenchmarkMethodChart;
import com.carrotsearch.junitbenchmarks.annotation.LabelType;
import org.jacorb.test.harness.ClientServerSetup;
import org.jacorb.test.harness.ClientServerTestCase;
import org.jacorb.test.harness.ORBTestCase;
import org.junit.*;
import org.junit.rules.TestRule;

import java.util.Properties;

@AxisRange(min = 0.0, max = 0.1)
@BenchmarkMethodChart(filePrefix = "benchmark-lists")
@BenchmarkHistoryChart(labelWith = LabelType.CUSTOM_KEY, filePrefix =  "benchmark")
public final class PerformanceTest extends ClientServerTestCase
{
    private static final int BENCHMARK_LOOP = 20;
    private static final int METHOD_LOOP = 3000;

    private static final String bigString;
    private static final Data[] bigData = new Data[2];

    private static Hello server;
    private static Hello serverNoDeferred;

    private static ORBTestCase clientORBTestCase = new ORBTestCase ()
    {
        @Override
        protected void patchORBProperties(Properties props) throws Exception
        {
            props.setProperty("jacorb.deferredArrayQueue", "0");
            props.setProperty("jacorb.codeset", "off");
        }
    };

    static
    {
        String temp = "";
        for (int i=0; i<10000; i++)
        {
            temp += "1";
        }
        bigString = temp;

        int max = 10000;
        byte[] bytes = new byte[max];
        for (int i=0; i < max; i++)
        {
            bytes[i] = 1;
        }

        bigData[0] = new Data();
        bigData[0].bytes = bytes;
        bigData[0].name = "first";
        bigData[1] = new Data();
        bigData[1].bytes = new byte[] { 2, 2 };
        bigData[1].name = "second";

    }

    @Rule
    public TestRule benchmarkRun = new BenchmarkRule();

    @BeforeClass
    public static void beforeClassSetup() throws Exception
    {
        clientORBTestCase.ORBSetUp ();

        Properties props = new Properties();

        props.setProperty("org.omg.PortableInterceptor.ORBInitializerClass."
                          + "ORBInit", Initializer.class.getName());
        props.setProperty("jacorb.codeset", "off");

        setup = new ClientServerSetup( "org.jacorb.performance.HelloImpl", props, props );
        server = HelloHelper.narrow(setup.getServerObject());

         org.omg.CORBA.Object obj = clientORBTestCase.getORB ().string_to_object
            (setup.getServerIOR());
        serverNoDeferred = HelloHelper.narrow (obj);
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testSayHello()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            server.sayHello();
        }
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testString()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            server.inputString(bigString);
        }
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testOctet()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            server.inputData(bigData);
        }
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testSayHelloNoDeferred()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            serverNoDeferred.sayHello();
        }
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testStringNoDeferred()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            serverNoDeferred.inputString(bigString);
        }
    }

    @Test
    @BenchmarkOptions(clock = Clock.NANO_TIME, benchmarkRounds = BENCHMARK_LOOP, warmupRounds = 1)
    public void testOctetNoDeferred()
    {
        for ( int i = 0 ; i < METHOD_LOOP; i++ )
        {
            serverNoDeferred.inputData(bigData);
        }
    }
}
