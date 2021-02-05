package com.example.kotlinmusic.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinmusic.model.MusicModel
import com.example.kotlinmusic.model.MusicResult
import com.example.kotlinmusic.util.IItemClickListener
import kotlinx.android.synthetic.main.music_row.view.*


class MusicAdapter(
    musicResult: MusicResult,
    private val rowLayout: Int,
    private var mContext: Context
): RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private var musicList: List<MusicModel> = musicResult.results

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context).inflate(rowLayout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mm: MusicModel = musicList[position]

        if (mm.trackName.isNullOrBlank()) holder.trackName.text = "Unavailable"
        else holder.trackName.text = mm.trackName

        if (mm.trackPrice!! <= 0) holder.trackPrice.text = "Free"
        else holder.trackPrice.text = mm.trackPrice.toString()

        holder.artistName.text = mm.artistName

        Glide.with(mContext)
            .load(mm.artworkUrl100)
            .into(holder.artworkImage100)
        holder.setClickListener(object : IItemClickListener {
            override fun onClick(view: View?, position: Int, isLongClick: Boolean) {
                if (isLongClick) {
                    Toast.makeText(
                        mContext,
                        "#" + position + " - " + mm.trackName + " (Long click)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        mContext,
                        "#" + position + " - " + mm.trackName,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        })
    }

    override fun getItemCount(): Int {
        return if (musicList == null) 0 else musicList.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        init {
            itemView.tag = itemView
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        val trackName: TextView = itemView.txtTrackName
        val artistName: TextView = itemView.txtArtName
        val trackPrice: TextView = itemView.txtTrackPrice
        val artworkImage100: ImageView = itemView.artistImg100
        private var clickListener: IItemClickListener? = null

        fun setClickListener(itemClickListener: IItemClickListener?) {
            clickListener = itemClickListener
        }

        override fun onClick(v: View?) {
            clickListener!!.onClick(v, position, false)
        }

        override fun onLongClick(v: View?): Boolean {
            clickListener!!.onClick(v, position, true)
            return false
        }
    }
}