package ru.clevertec.vasili.urusov.output;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCheckOutput implements CheckOutput {

    private String check;

    @Override
    public void output() {
        try {
            FileOutputStream outputStream = new FileOutputStream("data/check.txt");
            byte[] buffer = check.getBytes();
            outputStream.write(buffer, 0, buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setCheck(String check) {
        this.check = check;
    }

    @Override
    public String getCheck() {
        return check;
    }
}
