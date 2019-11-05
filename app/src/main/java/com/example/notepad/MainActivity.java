package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    public EditText typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        typeText = (EditText) findViewById(R.id.typeText);
        saveFile("Note1.txt");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile("Note1.txt");
            }
        });
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
        Intent myIntent = new Intent(MainActivity.this, NoteSelectActivity.class);
        MainActivity.this.startActivity(myIntent);
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void saveFile(String fileName) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(fileName, 0));
            out.write(typeText.getText().toString());
            out.close();
            Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();

        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkFileExists(String name) {
        File file = getBaseContext().getFileStreamPath(name);
        return file.exists();
    }

    public String openFile(String fileName) {
        String content = "";
        if (checkFileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if (in != null) {
                    InputStreamReader tmp = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(tmp);
                    String string;
                    StringBuilder builder = new StringBuilder();
                    while ((string = reader.readLine()) != null) {
                        builder.append(string + "\n");
                    }
                    in.close();
                    content = builder.toString();
                }
            } catch(java.io.FileNotFoundException e){
            } catch(Throwable t){
                    Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }

        }
        return content;

    }
}
