package hcmute.edu.vn.gk_fn_ltdd;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

    List<String> songNames;
    List<String> thumbnails;
    List<String> songArtist;
    List<String> songDuration;
    Context context;
    JcPlayerView jcPlayerView;
    List<JcAudio> jcAudios;



    int pos;

    public GridAdapter(List<String> songNames, List<String> thumbnails, List<String> songArtist, List<String> songDuration, Context context, JcPlayerView jcPlayerView, List<JcAudio> jcAudios) {
        this.songNames = songNames;
        this.thumbnails = thumbnails;
        this.songArtist = songArtist;
        this.songDuration = songDuration;
        this.context = context;
        this.jcPlayerView = jcPlayerView;
        this.jcAudios = jcAudios;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.card_item, null);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int position) {
        Picasso.get().load(thumbnails.get(position)).into(holder.imgSong);
        holder.nameSong.setText(songNames.get(position));
        holder.jcAudio = jcAudios;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(jcPlayerView != null){
                    jcPlayerView.playAudio(holder.jcAudio.get(holder.getAdapterPosition()));
                    jcPlayerView.setVisibility(View.VISIBLE);
                    jcPlayerView.createNotification();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return songNames.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSong;
        private TextView nameSong;
        List<JcAudio> jcAudio;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSong = itemView.findViewById(R.id.imgSongCard);
            nameSong = itemView.findViewById(R.id.nameSongCard);
        }
    }

}
