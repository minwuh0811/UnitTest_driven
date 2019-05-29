import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


class IADTest {
    IHD ihd1 = new IHD();
    IHD ihd2 = new IHD();
    IAD iad = new IAD();
    IHD mock_ihd1 = mock(IHD.class);
    IHD mock_ihd2 = mock(IHD.class);

// These two first test compares two iad objects, the first object is initialized with initialize method, the second
    // is initialized locally in the test with the concstructor, and we check that their values are the same.


    @Test
    void initialize_testingInitializeMethodTwoSensor_expectFalse() {
        iad.initialize(ihd1, ihd2);
        IAD iad2 = new IAD(ihd1, ihd2, 0, 0, false, false, 30);

        System.out.println ("Test case Sensor with False");
        assertEquals(iad2, iad);
    }

    @Test
    void initialize_testingInitializeMethodTOneSensor_expectFalse() {
        iad.initialize(ihd1);
        IAD iad2 = new IAD(ihd1, ihd1, 0, 0, false, false, 5);

        assertEquals(iad2, iad);
    }


    //Testing default constructor for full coverage
    @Test
    void initialize_testingDefaultConstructorGivesNull_expectNull() {

        IAD iad2 = new IAD();

        assertNull(iad2.getIhd1());
    }


    @Test
    void execute_testingLotsOfBoundaryValuesWIthTwoIHD() {

// here i initialize the mocked ihds

        iad.initialize(mock_ihd1, mock_ihd2);


// here i create array with data that i send in to test
        int testData[] = {0, -23, 45, 290, 50, -123, 200, 199, 201, -200, 5, 1, -1, 31};

        // in this test the output is two booleans, the output might change for each loop, so i must create an array of arrays that i
        // store the expected results in. so here for example when value zero which is the first value in the testData array is sent
        // to the for loop, once it passed the system and analyze has given it two booleans,
        // it will be compared to the test0 array in the array of arrays called expectedResults.
        boolean test0[] = {false, false};
        boolean test1[] = {false, false};
        boolean test2[] = {true, true};
        boolean test3[] = {true, true};
        boolean test4[] = {true, true};
        boolean test5[] = {false, false};
        boolean test6[] = {true, true};
        boolean test7[] = {true, true};
        boolean test8[] = {true, true};
        boolean test9[] = {false, false};
        boolean test10[] = {false, false};
        boolean test11[] = {false, false};
        boolean test12[] = {false, false};
        boolean test13[] = {true, true};


        boolean[] expectedResult[] = {test0, test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11, test12, test13};


        System.out.println("");
        System.out.println("Printing out result of boundary values test with two IHD");


        for (int t = 0; t < testData.length; t++) {

            // here i mock the getoutput values with the array input

            doReturn(testData[t]).when(mock_ihd1).getOutput();
            doReturn(testData[t]).when(mock_ihd2).getOutput();

            // then i run the execute which analyze the data with the values from array
            iad.execute();


            // then i take the result and store it in an array.
            boolean[] booleansProducedByAnalyzeMethod = iad.getData();
// here i store the expectedResultvalue on position t in the testet array
            boolean testet[] = expectedResult[t];
// and last but not the least i compare both arrays and print out result.

            if (Arrays.equals(testet, booleansProducedByAnalyzeMethod))
                System.out.println("Pass boundarytesttwoIHD" + " " + t);
            else
                System.out.println("Fail boundarytesttwoIHD" + " " + t);


        }
    }

    @Test
    void execute_testingLotsOfBoundaryValueswithoneIHD() {

// here i initialize the mocked ihds

        iad.initialize(mock_ihd1);


// here i create array with data that i send in to test
        int testData[] = {0, -23, 45, 290, 50, -123, 200, 199, 201, -200, 6, 1, -1, 31};

        // in this test the output is two booleans, the output might change for each loop, so i must create an array of arrays that i
        // store the expected results in. so here for example when value zero which is the first value in the testData array is sent
        // to the for loop, once it passed the system and analyze has given it two booleans,
        // it will be compared to the test0 array in the array of arrays called expectedResults.
        boolean test0[] = {false, false};
        boolean test1[] = {false, false};
        boolean test2[] = {true, true};
        boolean test3[] = {true, true};
        boolean test4[] = {true, true};
        boolean test5[] = {false, false};
        boolean test6[] = {true, true};
        boolean test7[] = {true, true};
        boolean test8[] = {true, true};
        boolean test9[] = {false, false};
        boolean test10[] = {true, true};
        boolean test11[] = {false, false};
        boolean test12[] = {false, false};
        boolean test13[] = {true, true};


        boolean[] expectedResult[] = {test0, test1, test2, test3, test4, test5, test6, test7, test8, test9, test10, test11, test12, test13};

        System.out.println("");
        System.out.println("Printing out result of boundary values test with one IHD");

        for (int t = 0; t < testData.length; t++) {

            // here i mock the getoutput values with the array input

            doReturn(testData[t]).when(mock_ihd1).getOutput();


            // then i run the execute which analyze the data
            iad.execute();


            // then i take the result and store it in an array.
            boolean[] booleansProducedByAnalyzeMethod = iad.getData();
// here i store the expectedResultvalue on position t in the testet array
            boolean testet[] = expectedResult[t];
// and last but not the least i compare both arrays and print out result.

            if (Arrays.equals(testet, booleansProducedByAnalyzeMethod))
                System.out.println("Pass boundarytestoneIHD" + " " +  t);
            else
                System.out.println("Fail boundarytestoneIHD" + " " +  t);


        }
    }

    @Test
    void execute_testingLotsOfBoundaryValuesWIthTwoIHDWithdifferentIHDValues() {

// here i initialize the mocked ihds

        iad.initialize(mock_ihd1, mock_ihd2);


// here i create array with data that i send in to test
        int testData[] = {29, 31, 29, 31 };
        int testData2[] = {29, 29, 31, 31 };

        // in this test the output is two booleans, the output might change for each loop, so i must create an array of arrays that i
        // store the expected results in. so here for example when value zero which is the first value in the testData array is sent
        // to the for loop, once it passed the system and analyze has given it two booleans,
        // it will be compared to the test0 array in the array of arrays called expectedResults.
        boolean test0[] = {false, false};
        boolean test1[] = {true, false};
        boolean test2[] = {false, true};
        boolean test3[] = {true, true};



        boolean[] expectedResult[] = {test0, test1, test2, test3};


        System.out.println("");
        System.out.println("Printing out result of boundary values test with two IHD- different values in each IHD");

        // after initialize inputs are set to zero, here inputs is changed with array content at index t
        for (int t = 0; t < testData.length; t++) {

            // here i mock the getoutput values with the array input

            doReturn(testData[t]).when(mock_ihd1).getOutput();
            doReturn(testData2[t]).when(mock_ihd2).getOutput();

            // then i run the execute which analyze the data
            iad.execute();


            // then i take the result and store it in an array.
            boolean[] booleansProducedByAnalyzeMethod = iad.getData();
// here i store the expectedResultvalue on position t in the testet array
            boolean testet[] = expectedResult[t];
// and last but not the least i compare both arrays and print out result.

            if (Arrays.equals(testet, booleansProducedByAnalyzeMethod))
                System.out.println("Pass boundarytesttwoIHDDiffValue" + " " + t);
            else
                System.out.println("Fail boundarytesttwoIHDDiffValue" + " " + t);


        }
    }




//Koden saknar nullhantering för denna modul därför är detta test inte genomförbart.
    // code is missing null-handling for this module and therefore this test isnt applicable.
  /*  @Test
    void execute_testingAnalyzeWithNull() {

// here i initialize the mocked ihds

        iad.initialize(mock_ihd1, mock_ihd2);


        // here i mock the getoutput values two null

        doReturn(null).when(mock_ihd1).getOutput();
        doReturn(null).when(mock_ihd2).getOutput();

        // then i run the execute which analyze the data
        iad.execute();


        // then i take the result and store it in an array.
        boolean[] booleansProducedByAnalyzeMethod = iad.getData();
// here i store the expectedResultvalue on position t in the testet array

        boolean testet[] = {false, false};
// and last but not the least i compare both arrays and print out result.

        if (Arrays.equals(testet, booleansProducedByAnalyzeMethod))
            System.out.println("Pass boundaryvaluestest");
        else
            System.out.println("Fail boundaryvaluestest");


    }*/
}




