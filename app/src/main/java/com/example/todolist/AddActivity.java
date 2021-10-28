package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText messageEditText, titleEditText;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        messageEditText = findViewById(R.id.message_editText);
        titleEditText = findViewById(R.id.title_editText);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(view -> {
             Database db = new Database(AddActivity.this);
             db.addNote(titleEditText.getText().toString(), messageEditText.getText().toString());
        });
    }
}