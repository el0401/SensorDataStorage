package filepersistence;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class SensorDataStorageTest {


    @Test
    void getDataAtTimeGutTest() throws PersistenceException, NoDataException{
        SensorDataStorage sds = new SensorDataStorageImpl();
        long time = 1134;
        double valueSet[] = new double[2];
        valueSet[0] = 1.32;
        valueSet[1] = 2.32;
        valueSet[2] = 3.32;
        sds.saveData(1134,valueSet);

        double getValueSet[] = new double[2];
        sds.getDataAtTime(1134);

        assertEquals(valueSet,getValueSet);
    }

    @Test(expected = NoDataException.class)
    void getDataAtTimeSchlechtTest() throws PersistenceException, NoDataException{
        SensorDataStorage sds = new SensorDataStorageImpl();
        double[] getValueSet = new double[10];
        sds.getDataAtTime(1134);
    }

    @Test
    void getDataAtPositionGutTest() throws PersistenceException, NoDataException{
        SensorDataStorage sds = new SensorDataStorageImpl();
        double[] valueSet = new double[2];
        double[][] values = new double[3][];
        values[0] = valueSet;
        valueSet[0]= 1.5;

        sds.saveData(1134,valueSet);

        double getValueSet[] = new double[2];
        sds.getDataAtPosition(0);

        assertEquals(valueSet,getValueSet);
    }

    @Test
    void sizeTest() throws PersistenceException{
        SensorDataStorage sds = new SensorDataStorageImpl();
        double[] valueSet = new double[1];
        double[][] values = new double[3][];
        values[0] = valueSet;
        valueSet[0]= 1.5;

        valueSet = new double[2];
        values[1] = valueSet;
        valueSet[0] = 1.65;
        valueSet[1] = 3.21;

        assertEquals(2,values.length);
    }

}