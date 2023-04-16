package hcmute.edu.vn.gk_fn_ltdd;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jean.jcplayer.model.JcAudio;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {

    Context context;
    List<String> songNames;
    List<String> thumbnails;


    public GridAdapter(Context context, List<String> songNames, List<String> thumbnails) {
        this.context = context;
        this.songNames = songNames;
        this.thumbnails = thumbnails;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.card_item, null);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder holder, int i) {
        Picasso.get().load(thumbnails.get(i)).into(holder.imgSong);
        holder.nameSong.setText(songNames.get(i));
    }

    @Override
    public int getItemCount() {
        return songNames.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgSong;
        private TextView nameSong;


        public GridViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSong = itemView.findViewById(R.id.imgSongCard);
            nameSong = itemView.findViewById(R.id.nameSongCard);
        }
    }

}
