package com.example.carll.myfirstnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.carll.myfirstnote.data.NoteData;
import com.example.carll.myfirstnote.data.NoteItem;
import com.example.carll.myfirstnote.utility.DisplayUtility;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String LOGTAG = "Main Activity: Note Key";
    public static final int REQUEST_CODE = 1001;
    public static final int MENU_DELETE_ID = 1002;

    private NoteData noteData;
    private List<NoteItem> notesList;
    private int currentNoteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO 1.4 기본화면 Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO Floating Action Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNote();
            }
        });

        // Notes
        noteData = new NoteData(this);
        refreshDisplay();
    }

    private void refreshDisplay() {
        notesList = noteData.findAll();
        ArrayAdapter<NoteItem> adapter = new ArrayAdapter<>(this, R.layout.list_item_layout, notesList);
        ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(adapter);
        registerForContextMenu(list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NoteItem noteItem = notesList.get(position);
                displayDetail(noteItem);
            }
        });
    }

    private void displayDetail(NoteItem noteItem) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("key", noteItem.getKey());
        intent.putExtra("value", noteItem.getValue());
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void createNote() {
        NoteItem noteItem = NoteItem.getNew();
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("key", noteItem.getKey());
        intent.putExtra("value", noteItem.getValue());
        startActivityForResult(intent, 1001);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK ) {
            NoteItem noteItem = new NoteItem();
            noteItem.setKey(data.getStringExtra("key"));
            noteItem.setValue(data.getStringExtra("value"));
            noteData.update(noteItem);
            refreshDisplay();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            DisplayUtility displayUtility = new DisplayUtility(this);
            String screen = "Density: " + displayUtility.getDensity() + ", Width: "
                    + displayUtility.getWidth() + " dp, Height: "
                    + displayUtility.getHeight() + " dp";
            Toast.makeText(this, screen, Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        currentNoteId = (int) info.id;
        menu.add(0, MENU_DELETE_ID, 0, R.string.delete);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        if (item.getItemId() == MENU_DELETE_ID) {
            NoteItem noteItem = notesList.get(currentNoteId);
            noteData.remove(noteItem);
            refreshDisplay();
        }
        return super.onContextItemSelected(item);
    }
}
