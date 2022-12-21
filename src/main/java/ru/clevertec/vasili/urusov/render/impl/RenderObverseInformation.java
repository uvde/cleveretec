package ru.clevertec.vasili.urusov.render.impl;

import ru.clevertec.vasili.urusov.render.Render;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RenderObverseInformation implements Render {

    private final RenderCheck renderCheck;

    public RenderObverseInformation(RenderCheck renderCheck) {
        this.renderCheck = renderCheck;
    }

    @Override
    public String render() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy\n  HH:mm:ss");
        String time = formatter.format(calendar.getTime());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------------------\r\n");
        stringBuilder.append("CHECK RECEIPT\r\n");
        stringBuilder.append("---------------------\r\n");
        stringBuilder.append(time).append("\r\n");
        stringBuilder.append("---------------------\r\n");
        stringBuilder.append(renderCheck.render());
        stringBuilder.append("---------------------\r\n");
        stringBuilder.append("Thank you for shopping\r\n");
        return stringBuilder.toString();
    }
}
