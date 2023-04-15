package hcmute.edu.vn.gk_fn_ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;
public class PlayerActivity extends AppCompatActivity {

    ImageButton btnPlay, btnNext, btnPrev,btnShuffer, btnRepeat;
    TextView txtSong, txtAlbum, txtArtist;
    SeekBar seekBar;
    ImageView imageView;
    String songName;
    int position;
    ArrayList<File> song;
    JcPlayerView jcPlayerView;
    ListAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_player);

//        btnNext = findViewById(R.id.next);
//        btnPlay = findViewById(R.id.play);
//        btnPrev = findViewById(R.id.previous);
//        btnShuffer = findViewById(R.id.shuffle);
//        btnRepeat = findViewById(R.id.repeat);
//
//        txtSong = findViewById(R.id.song);
        txtAlbum = findViewById(R.id.album);
        txtArtist = findViewById(R.id.artist);
//        seekBar = findViewById(R.id.seekBarL);
        imageView = findViewById(R.id.album_art);
        listView = findViewById(R.id.songsList);


        jcPlayerView = findViewById(R.id.jcplayer);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int i = bundle.getInt("pos");
        List<JcAudio> jcAudios = bundle.getParcelableArrayList("audio");
        List<String> songsNameList = bundle.getStringArrayList("songsNameList") ;
        List<String> songsUrlList  = bundle.getStringArrayList("songsUrlList");
        List<String> songsArtistList = bundle.getStringArrayList("songArtistList");
        List<String> songsDurationList = bundle.getStringArrayList("songsDurationList");
        List<String> thumbnail  = bundle.getStringArrayList("thumbnail");
        Picasso.get().load(thumbnail.get(i)).into(imageView);
        txtArtist.setText(songsArtistList.get( jcPlayerView.getCurrentAudio().getPosition()));

//        adapter = new ListAdapter(getApplicationContext(), songsNameList, thumbnail, songsArtistList, songsDurationList);
        jcPlayerView.initPlaylist(jcAudios, null);
        jcPlayerView.playAudio(jcAudios.get(i));
        jcPlayerView.setVisibility(View.VISIBLE);
//        jcPlayerView.createNotification();

//        adapter.notifyDataSetChanged();




    }


}