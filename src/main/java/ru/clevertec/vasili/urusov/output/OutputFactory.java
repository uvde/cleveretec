package ru.clevertec.vasili.urusov.output;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OutputFactory {

    private static volatile OutputFactory instance;

    private Properties properties;
    private CheckOutput outputConsole;
    private CheckOutput outputFile;

    public OutputFactory(){
        properties = new Properties();
        try {
            properties.load(new FileReader("src/main/java/ru/clevertec/vasili/urusov/output/msf.properties"));
            String outputConsoleClass = properties.getProperty("outputConsole.class");
            String outputFileClass = properties.getProperty("outputFile.class");

            outputConsole = (CheckOutput)Class.forName(outputConsoleClass).newInstance();
            outputFile = (CheckOutput)Class.forName(outputFileClass).newInstance();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public CheckOutput getCheckRender(String name) {
        OutputList output = OutputList.valueOf(name.toUpperCase());
        switch (output){
            case FILE:
                return outputFile;
            case CONSOLE:
                return outputConsole;
            default:
                return outputFile;
        }
    }
    public static OutputFactory getInstance() {
        OutputFactory localInstance = instance;
        if (localInstance == null) {
            synchronized (OutputFactory.class) {
                localInstance = instance;
                if (localInstance == null) {
                  instance = localInstance = new OutputFactory();
                }
            }
        }
        return localInstance;
    }
}
