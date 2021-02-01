package com.example.musiclist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musiclist.R;
import com.example.musiclist.model.MusicModel;
import com.example.musiclist.model.MusicResult;
import com.example.musiclist.util.IItemClickListener;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<MusicModel> musicList;
    private int rowLayout;
    private Context mContext;
    public String price;

    public MusicAdapter(MusicResult musicList, int rowLayout, Context mContext) {
        this.musicList = musicList.getResults();
        this.rowLayout = rowLayout;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MusicModel mm = musicList.get(position);

        price = mm.getTrackPrice() <= 0 ? "Free" : Double.toString(mm.getTrackPrice());

        holder.trackName.setText(mm.getTrackName());
        holder.artistName.setText(mm.getArtistName());
        holder.trackPrice.setText(price);

        Glide.with(mContext)
                .load(mm.getArtworkUrl100())
                .into(holder.artworkImage100);
        holder.setClickListener((view, i, isLongClick) -> {
            if (isLongClick) {
                Toast.makeText(mContext, "#" + i + " - " + mm.getTrackName() + " (Long click)", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "#" + i + " - " + mm.getTrackName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList == null ? 0 : musicList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        public TextView trackName;
        public TextView artistName;
        public TextView trackPrice;
        public ImageView artworkImage100;

        private IItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);

            trackName = (TextView) itemView.findViewById(R.id.txtTrackName);
            artistName = (TextView) itemView.findViewById(R.id.txtArtName);
            trackPrice = (TextView) itemView.findViewById(R.id.txtTrackPrice);
            artworkImage100 = (ImageView) itemView.findViewById(R.id.artistImg100);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(IItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition(), true);
            return false;
        }
    }
}
