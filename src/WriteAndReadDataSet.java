import java.io.*;

public class WriteAndReadDataSet {
    public static void main(String[] args) {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream

        OutputStream os = null;
        boolean done = false;
        String filename = "testFile.txt";
        while (!done) {
            try {
                os = new FileOutputStream(filename);
                done = true;
            } catch (FileNotFoundException ex) {
                System.err.println("couldn’t open file - fatal");
            }
        }

        DataOutputStream dos = new DataOutputStream(os);
        try {
            for(int i = 0; i<values.length;i++){
                for(int j = 0; j<values[i].length; j++){
                    dos.writeFloat(values[i][j]);
                }
            }

        } catch (IOException ex) {
            System.err.println("couldn’t write data (fatal)");
            System.exit(0);
        }


        // read data from file and print to System.out
        // TODO: your job use DataInputStream / FileInputStream
        InputStream is = null;
        try {
            is = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn’t open file - fatal");
        }

        DataInputStream dis = new DataInputStream(is);
        try {
            for(int i = 0; i<values.length;i++) {
                for (int j = 0; j < values[i].length; j++) {
                    float readFloatValue = dis.readFloat();
                    System.out.println(readFloatValue);
                }
            }

        } catch (IOException ex) {
            System.err.println("couldn’t read data (fatal)");
            System.exit(0);
        }
    }
}