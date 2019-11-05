package com.example.notepad;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.MyViewHolder>{

private ArrayList<NotesBuilder> notesList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public View view;
    public TextView title, content;

    public MyViewHolder(View view) {
        super(view);
        this.view = view;
        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);

    }

}

    public NotesAdapter(ArrayList<NotesBuilder> notesList) {
        this.notesList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.life_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final NotesBuilder note = notesList.get(position);
        holder.title.setText(note.getTitle());
        holder.content.setText(note.getContent());

        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Context c = view.getContext();
                Intent intent = new Intent(c, MainActivity.class);
                intent.putExtra("noteTitle", note.getTitle());
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();

    }

}
