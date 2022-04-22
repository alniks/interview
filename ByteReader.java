package com.alniks.interview;

import java.io.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * A singleton object for reading large files. Can be used for progress
 * notification. Instances of the class can be used from multiple threads.
 */
public class ByteReader {

    private Executor executor;
    private static ByteReader instance;

    private ByteReader() {
        super();
	executor = Executors.newFixedThreadPool(1);
    }

    public static ByteReader getInstance() {
        if (instance == null)
            instance = new ByteReader();
        return instance;
    }

    public synchronized void read(String fileName, Runnable progressCallback) {
        try {
            InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(fileName)));
            int data;
            int counter = 0;
            while ((data = inputStream.read()) != -1) {
                if (++counter % 1024 == 0) {
                    executor.execute(progressCallback);
                }
                process(data);
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }        
    }

    private void process(int data) {
// do something useful.
    }
}
