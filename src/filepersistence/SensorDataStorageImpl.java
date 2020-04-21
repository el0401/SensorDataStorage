package filepersistence;

public class SensorDataStorageImpl implements SensorDataStorage {
    @Override
    public void saveData(long time, double[] valueSet) throws PersistenceException {
    }

    @Override
    public float[] getDataAtTime(long time) throws PersistenceException, NoDataException {
        return new float[0];
    }

    @Override
    public float[] getDataAtPosition(int position) throws PersistenceException, NullPointerException {
        return new float[0];
    }

    @Override
    public int size() throws PersistenceException {
        return 0;
    }

    @Override
    public boolean contains(double[] valueSet) throws PersistenceException {
        return false;
    }


    public int size(double[][] values) throws PersistenceException {
        return 0;
    }
}
