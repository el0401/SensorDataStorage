package filepersistence;

import java.util.Date;

/**
 * We assume: Each sensor gets its own storage engine. There wont be a parameter
 * sensor name.
 */
public interface SensorDataStorage {

    /**
     * This method can be called by a sensor to save a data set.
     * @param time UNIX time when measurement took place
     * @param valueSet sensor data
     * @throws PersistenceException if something unexpected happened. Insufficient right, medium broken, offline..
     */
    void saveData(long time, double[] valueSet) throws PersistenceException;

    /**
     * This method returns the data measured at a certain time
     * @param time wanted time of measurement
     * @return data
     * @throws NoDataException if there is no data measured at that time
     * @throws PersistenceException if something unexpected happened.
     */
    float[] getDataAtTime(long time) throws PersistenceException,NoDataException;

    /**
     * This method returns the data at a certain position
     * @param position wanted position of measurement
     * @return data
     * @throws PersistenceException if something unexpected happened.
     * @throws NullPointerException if there is no data at that position
     */
    float[] getDataAtPosition(int position) throws PersistenceException,NullPointerException;

    /**
     * This method returns the size of a data set
     * @return size
     * @throws PersistenceException if something unexpected happened.
     */
    int size() throws PersistenceException;

    /**
     * This method returns im the given value is in
     * @param valueSet value to search for
     * @return true, if contains
     * @throws PersistenceException if something unexpected happened.
     */
    boolean contains(double[] valueSet) throws PersistenceException;
}