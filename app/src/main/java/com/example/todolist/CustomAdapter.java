package com.example.todolist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList noteIdArray, noteTitleArray, noteMessageArray;

    CustomAdapter(Activity activity, Context context, ArrayList noteId, ArrayList noteTitle, ArrayList noteMessage){
        this.activity = activity;
        this.context = context;
        this.noteIdArray = noteId;
        this.noteTitleArray = noteTitle;
        this.noteMessageArray = noteMessage;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.noteId.setText(String.valueOf(noteIdArray.get(position)));
        holder.noteTitle.setText(String.valueOf(noteTitleArray.get(position)));
        holder.noteMessage.setText(String.valueOf(noteMessageArray.get(position)));
        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(noteIdArray.get(position)));
            intent.putExtra("title", String.valueOf(noteTitleArray.get(position)));
            intent.putExtra("message", String.valueOf(noteMessageArray.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return noteIdArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView noteId, noteTitle, noteMessage;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            noteId = itemView.findViewById(R.id.note_id_text);
            noteTitle = itemView.findViewById(R.id.note_title_text);
            noteMessage = itemView.findViewById(R.id.note_message_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
