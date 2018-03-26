package com.example.aravind_pt1748.recyclerviewapp202;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private String TAG = "MainActivity";
    private List<Game> gameList = new ArrayList<>();
    String permission = "com.example.aravind_pt1748.cpapp202.READ_GAME_DATA";
    boolean publicAccess = false;
    RecyclerViewActivityAdapter adapter;
    String[] projection = new String[]{GamesContract.GamesMetaData.GAME_NAME,GamesContract.GamesMetaData.GENRE_NAME,GamesContract.GamesMetaData.RELEASE_DATE,GamesContract.GamesMetaData.IMAGE_GAME};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: in MainActivity called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission();
        if(publicAccess) {
            getSupportLoaderManager().initLoader(0,null,this);
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        adapter = new RecyclerViewActivityAdapter(this);
        recyclerView.setAdapter(adapter);
    }
/*
    public void prepData(){
        Log.d(TAG, "prepData: called which means permission granted");
        Cursor c = getContentResolver().query(GamesContract.GamesMetaData.CONTENT_URI,projection,null,null,null);
        if(c!=null){
            c.moveToFirst();
            Log.d(TAG, "onCreate: c is not null");
        }
        do{
            String game = c.getString(c.getColumnIndex(GamesContract.GamesMetaData.GAME_NAME));
            String genre = c.getString(c.getColumnIndexOrThrow(GamesContract.GamesMetaData.GENRE_NAME));
            String releaseDate = c.getString(c.getColumnIndexOrThrow(GamesContract.GamesMetaData.RELEASE_DATE));
            Log.d(TAG, "prepData : "+game+" : "+genre+" : "+releaseDate);
            Game newGame = new Game(game, genre, releaseDate);
            gameList.add(newGame);
        }while (c.moveToNext());
    }
*/

    public void checkPermission(){
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyhavePermission()) {
                requestForSpecificPermission();
            }
        }
    }

    private boolean checkIfAlreadyhavePermission() {
        int result = ContextCompat.checkSelfPermission(this, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            publicAccess=true;
            return true;
        } else {
            publicAccess=false;
            return false;
        }
    }

    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{permission}, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this,"Permission granted to read game data from another app",Toast.LENGTH_SHORT).show();
                    //writeData(fileGlobal,dataGlobal);
                    getSupportLoaderManager().initLoader(0,null,this);
                    publicAccess=true;
                } else {
                    Toast.makeText(this,"Permission not granted to read game data from another app",Toast.LENGTH_SHORT).show();
                    publicAccess=false;
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader: called in MainActivity");
        return new CursorLoader(this,GamesContract.GamesMetaData.CONTENT_URI,projection,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d(TAG, "onLoadFinished: in MainActivity called");
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "onLoaderReset: in MainActivity called");
        adapter.setData(null);
    }
}

