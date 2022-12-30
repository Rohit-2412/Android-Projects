package com.example.inotes;

import androidx.cardview.widget.CardView;

import com.example.inotes.Models.Notes;

public interface NotesClickListener {

    void onClick(Notes notes);

    void onLongClick(Notes notes, CardView cardView);
}
