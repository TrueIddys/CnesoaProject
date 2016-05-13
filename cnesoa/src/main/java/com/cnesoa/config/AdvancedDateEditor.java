package com.cnesoa.config;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by Maxime on 04/05/2016.
 * Used to edit the advanced date format (with minutes and secondes)
 */
public class AdvancedDateEditor extends PropertyEditorSupport {

    private static final String format = "yyyy/MM/dd hh:mm";

    public String getAsText(){
        return getValue() != null
                ? new SimpleDateFormat(format).format(getValue())
                : null;
    }

    public void setAsText(final String value){
        try {
            setValue(new SimpleDateFormat(format).parse(value));
        }
        catch(final ParseException e){
            System.out.println(e.getMessage());
        }
    }
}