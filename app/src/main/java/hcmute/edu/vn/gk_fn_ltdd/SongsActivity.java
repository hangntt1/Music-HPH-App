package hcmute.edu.vn.gk_fn_ltdd;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SongsActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    ListView listView;
    List<String> songsNameList;
    List<String> songsUrlList;
    List<String> songsArtistList;
    List<String> songsDurationList;
    ListAdapter adapter;
    JcPlayerView jcPlayerView;
    List<JcAudio> jcAudios;
    List<String> thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);
//        progressDialog = new ProgressDialog(this);
//        progressDialog.show();
//        progressDialog.setMessage("Please Wait...");
        listView = findViewById(R.id.songsList);
        songsNameList = new ArrayList<>();
        songsUrlList = new ArrayList<>();
        songsArtistList = new ArrayList<>();
        songsDurationList = new ArrayList<>();
        jcAudios = new ArrayList<>();
        thumbnail = new ArrayList<>();
        jcPlayerView = findViewById(R.id.jcplayer);
        retrieveSongs();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayList<String> songNameList = (ArrayList<String>) songsNameList;
                ArrayList<String> songUrlList = (ArrayList<String>) songsUrlList;
                ArrayList<String> songArtistList = (ArrayList<String>) songsArtistList;
                ArrayList<String> songDurationList = (ArrayList<String>) songsDurationList;
                ArrayList<String> thumbnail1 = (ArrayList<String>) thumbnail;
                ArrayList<JcAudio> audio = (ArrayList<JcAudio>) jcAudios;
                startActivity(new Intent(getApplicationContext(), PlayerActivity.class)
                        .putStringArrayListExtra("songNameList", songNameList)
                        .putStringArrayListExtra("songsUrlList", songUrlList)
                        .putStringArrayListExtra("songArtistList", songArtistList)
                        .putStringArrayListExtra("songsDurationList", songDurationList)
                        .putStringArrayListExtra("thumbnail", thumbnail1)
                        .putParcelableArrayListExtra("audio", (ArrayList<? extends Parcelable>) jcAudios)
                        .putExtra("pos", i));
                jcPlayerView.playAudio(jcAudios.get(i));
                jcPlayerView.setVisibility(View.VISIBLE);
                jcPlayerView.createNotification();
                adapter.notifyDataSetChanged();
            }
        });
    }

    // RETRIEVING THE SONGS FROM THE SERVER
    public void retrieveSongs() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Songs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Song song = ds.getValue(Song.class);
                    songsNameList.add(song.getSongName());
                    songsUrlList.add(song.getSongUrl());
                    songsArtistList.add(song.getSongArtist());
                    songsDurationList.add(song.getSongDuration());
                    thumbnail.add(song.getImageUrl());

                    jcAudios.add(JcAudio.createFromURL(song.getSongName(), song.getSongUrl()));
                }
                adapter = new ListAdapter(getApplicationContext(), songsNameList, thumbnail, songsArtistList, songsDurationList);
                jcPlayerView.initPlaylist(jcAudios, null);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                //progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SongsActivity.this, "FAILED!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}