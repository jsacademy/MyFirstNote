package com.example.carll.myfirstnote;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.carll.myfirstnote.data.NoteItem;

public class DetailActivity extends AppCompatActivity {

    private NoteItem noteItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = this.getIntent();
        noteItem = new NoteItem();
        noteItem.setKey(intent.getStringExtra("key"));
        noteItem.setValue(intent.getStringExtra("value"));

        EditText editText = (EditText) findViewById(R.id.noteText);
        editText.setText(noteItem.getValue());

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void saveAndFinish() {
        EditText editText = (EditText) findViewById(R.id.noteText);
        String noteText = editText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("key", noteItem.getKey());
        intent.putExtra("value", noteText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            saveAndFinish();
        }
        return false;
    }
}
