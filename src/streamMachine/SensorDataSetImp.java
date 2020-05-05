package streamMachine;

public class SensorDataSetImp implements SensorDataSet {
    private final long timeStamp;
    private final float[] values;
    private final String name;

    SensorDataSetImp(String name, long timeStamp, float values[]){
        this.name = name;
        this.timeStamp = timeStamp;
        this.values = values;
    }

    @Override
    public long getTime() {
        return this.timeStamp;
    }

    @Override
    public float[] getValues() {
        return this.values;
    }

    @Override
    public String getName() {
        return this.name;
    }
}