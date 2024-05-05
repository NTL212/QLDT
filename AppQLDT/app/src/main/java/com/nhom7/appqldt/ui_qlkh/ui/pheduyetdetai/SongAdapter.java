package com.nhom7.appqldt.ui_qlkh.ui.pheduyetdetai;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder>{
    List<SongModel> songList;
    Context context;

    public SongAdapter(List<SongModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.song_item, null);
        return new SongViewHolder(null);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongModel song = songList.get(position);
        holder.id.setText(song.getId());
        holder.name.setText(song.getName());

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class SongViewHolder extends RecyclerView.ViewHolder {
        public TextView id,name;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
//            id = itemView.findViewById(R.id.id);
//            name = itemView.findViewById(R.id.name);
        }

    }
}
