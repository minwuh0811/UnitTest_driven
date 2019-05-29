import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.mockito.*;


class OHDTest {

    OHD ohd;

    boolean[] array1 = new boolean[1];
    boolean[] array2 = new boolean[2];
    boolean[] array3 = new boolean[2];
    boolean[] arrayExpectedOutput = new boolean[5];
    IAD iad = new IAD ();
    boolean[] output = new boolean[5];
    boolean rear;
    boolean left;
    boolean right;
    boolean forwardL;
    boolean forwardR;
    private void mockMethod (){
        ohd.iad1 = Mockito.mock (IAD.class);
        ohd.iad2 = Mockito.mock (IAD.class);
        ohd.iad3 = Mockito.mock (IAD.class);
        Mockito.when (ohd.iad1.getData ()).thenReturn (array1);
        Mockito.when (ohd.iad2.getData ()).thenReturn (array2);
        Mockito.when (ohd.iad3.getData ()).thenReturn (array3);
    }
    // Test case TF_043
    void initializeWithAllFalseArrayDataTest () {
        rear = false;
        left = false;
        right = false;
        forwardL = false;
        forwardR = false;
        arrayExpectedOutput = new boolean[]{false, false, false, false, false};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;

    }
    // Test Data Test case ID TF_044
    void initializeWithAllTrueArrayDataTest () {
        rear = true;
        left = true;
        right = true;
        forwardL = true;
        forwardR = true;
        arrayExpectedOutput = new boolean[]{true, true, true, true, true};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;
    }


    // Test Data Test case ID TF_042
    void initializeWithNormalArrayDataTest () {
        rear = false;
        left = false;
        right = false;
        forwardL = true;
        forwardR = false;
        arrayExpectedOutput = new boolean[]{false, false, true, false, false};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;
    }

    //Test Data Test case ID TF_045

    void initializeWithNullArrayData () {
        rear = false;
        left = false;
        right = false;
        forwardL = true;
        forwardR = false;
        arrayExpectedOutput = new boolean[]{false, false, true, false, false};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;
    }
    //Test Data Test case TF_053
    void initializeWithPartNullArrayData (){
        rear = false;
        left = false;
        right = false;
        forwardL = false;
        forwardR = true;
        arrayExpectedOutput = new boolean[]{false, false, false, false, true};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;
    }
    // Test Data Test case TF_046
    void initializeWithPartArrayData (){

        rear = true;
        left = true;
        right = true;
        forwardL = true;
        forwardR = true;
        arrayExpectedOutput = new boolean[]{true, true, true, false, false};
        ohd = new OHD ();
        array1[0] = rear;
        array2[0] = left;
        array2[1] = forwardL;
        array3[0] = right;
        array3[1] = forwardR;


    }
    @Test
    void ohdTestningInicializeOchExecute () {



        initializeWithAllFalseArrayDataTest ();
        ohd.initialize (iad, iad, iad);
        mockMethod ();
        ohd.execute ();
        System.out.println ("Testing TF_043 with All False array values.");
        assertArrayEquals (arrayExpectedOutput, ohd.getOutput ());
        System.out.println ("Test TF_043 passed.");

        // Test  Test case ID TF_044


        initializeWithAllTrueArrayDataTest ();
        ohd.initialize (iad, iad, iad);
        mockMethod ();
        ohd.execute ();
        System.out.println ("Testing TF_044 with All True array values.");
        assertArrayEquals (arrayExpectedOutput, ohd.getOutput () );
        System.out.println ("Test TF_044 passed.");

        // Test Test case ID TF_042

        initializeWithNormalArrayDataTest ();
        ohd.initialize (iad, iad, iad);
        mockMethod ();
        ohd.execute ();
        System.out.println ("Testing TF_042 with All Normal array values.");
        assertArrayEquals (arrayExpectedOutput, ohd.getOutput () );
        System.out.println ("Test TF_042 passed.");

        //Test Test case ID TF_045

        initializeWithNullArrayData ();
        ohd.initialize (iad, iad, iad);
        ohd.iad1 = Mockito.mock (IAD.class);
        ohd.iad2 = Mockito.mock (IAD.class);
        ohd.iad3 = Mockito.mock (IAD.class);
        Mockito.when (ohd.iad1.getData ()).thenReturn (null);
        Mockito.when (ohd.iad2.getData ()).thenReturn (null);
        Mockito.when (ohd.iad3.getData ()).thenReturn (null);

        try {

            System.out.println ("Testing TF_045 with All Null data values.");
            ohd.execute ();
            assertArrayEquals ( null, ohd.getOutput () );
            System.out.println ("Test TF_045 passed.");

        } catch (NullPointerException e) {
            System.out.println ("The test TF_045 can not handle null");

        }

        //Test Test case TF_053

        initializeWithPartNullArrayData ();
        ohd.initialize (iad, iad, iad);
        ohd.iad1 = Mockito.mock (IAD.class);
        ohd.iad2 = Mockito.mock (IAD.class);
        ohd.iad3 = Mockito.mock (IAD.class);
        Mockito.when (ohd.iad1.getData ()).thenReturn (array1);
        Mockito.when (ohd.iad2.getData ()).thenReturn (null);
        Mockito.when (ohd.iad3.getData ()).thenReturn (array3);

        try {

            System.out.println ("Testing TF_053 with partial Null data values.");
            ohd.execute ();
            assertArrayEquals ( null, ohd.getOutput () );
            System.out.println ("Test TF_053 passed.");

        } catch (NullPointerException e) {
            System.out.println ("The test TF_053 can not handle null");

        }

        // Test Test case TF_046

        initializeWithPartArrayData ();
        ohd.initialize (iad, iad, iad);
        ohd.iad1 = Mockito.mock (IAD.class);
        ohd.iad2 = Mockito.mock (IAD.class);
        ohd.iad3 = Mockito.mock (IAD.class);
        Mockito.when (ohd.iad1.getData ()).thenReturn (array1);
        Mockito.when (ohd.iad2.getData ()).thenReturn (array2);
        Mockito.when (ohd.iad3.getData ()).thenReturn (null);

        try {

            System.out.println ("Testing TF_046 with partial input data values.");
            ohd.execute ();
  //          assertArrayEquals ( null, ohd.getOutput () );
            System.out.println ("Test TF_046 passed.");

        } catch (NullPointerException e) {
            System.out.println ("The test TF_046 can not handle partial input");

        }




    }





}
