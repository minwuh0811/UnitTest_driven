import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    OHD ohd = new OHD();
    IHD ihd1 = new IHD();
    IHD ihd2 = new IHD();
    IAD iad1 = new IAD();
    IAD iad2 = new IAD();
    IAD iad3 = new IAD();
    Sensor sensor = new Sensor();

    @Test
    void everything_integrationTests () {

// initiate two ihd to be able to initialize one iad

        iad1.initialize(ihd1);
        iad2.initialize(ihd1, ihd2);
        iad3.initialize(ihd1,ihd2);

        ihd1.initialize(sensor);
        ihd2.initialize(sensor);
        ohd.initialize(iad1, iad2, iad3);

        // here i create array with data that i send in to test
        int testData[] = {45, -1, 10};
        ArrayList  arrayen = new ArrayList();


        for (int t = 0; t < testData.length; t++) {

            sensor.safetyOutput = testData[t];
            sensor.originalOutput = testData[t];

            // then i must run both ihd so that the safety and original output is transferred to read1 and read2 which is used in
            // iad execute that does the analyze
            ihd1.execute();
            ihd2.execute();

            // then i run the execute which analyze the data
            iad1.execute();
            iad2.execute();
            iad3.execute();
            ohd.execute();
            // then i tread the data analyzed with the OHD
            boolean[] print = ohd.getOutput();
            for(int u=0; u< print.length; u++){


               arrayen.add( print[u] );


                System.out.println(print[u]);
            }


            }

        ArrayList<Boolean> expectedResult= new ArrayList<>(Arrays.asList(true,true,true,true,true,false,false,false,false,false,true,false,false,false,false));




        assertEquals( arrayen.toString(),expectedResult.toString() );

        }




    }










