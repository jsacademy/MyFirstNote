package com.example.carll.myfirstnote.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class NoteData {

    private SharedPreferences notePreferences;

    public NoteData(Context context) {

        notePreferences = context.getSharedPreferences("notePreferences", Context.MODE_PRIVATE);
    }

    public List<NoteItem> findAll() {

        Map<String, ?> notesMap = notePreferences.getAll();
        SortedSet<String> keys = new TreeSet<>(notesMap.keySet());

        List<NoteItem> notesList = new ArrayList<>();
        for (String key : keys) {
            NoteItem noteItem = new NoteItem();
            noteItem.setKey(key);
            noteItem.setValue((String) notesMap.get(key));
            notesList.add(noteItem);
        }

        return notesList;
    }

    public boolean update(NoteItem noteItem) {
        SharedPreferences.Editor editor = notePreferences.edit();
        editor.putString(noteItem.getKey(), noteItem.getValue());
        editor.apply();
        return true;
    }

    public boolean remove(NoteItem noteItem) {
        if (notePreferences.contains(noteItem.getKey())) {
            SharedPreferences.Editor editor = notePreferences.edit();
            editor.remove(noteItem.getKey());
            editor.apply();
        }
        return true;
    }

}
