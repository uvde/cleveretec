package ru.clevertec.vasili.urusov.output;

public class ConsoleCheckOutput implements CheckOutput {
    private String check;

    @Override
    public void output() {
        System.out.println(this.check);
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
