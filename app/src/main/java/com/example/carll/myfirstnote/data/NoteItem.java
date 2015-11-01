package com.example.carll.myfirstnote.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteItem {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static NoteItem getNew() {

        NoteItem noteItem = new NoteItem();
        String key = String.valueOf(System.currentTimeMillis());
        noteItem.setKey(key);
        noteItem.setValue("");

        return noteItem;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
