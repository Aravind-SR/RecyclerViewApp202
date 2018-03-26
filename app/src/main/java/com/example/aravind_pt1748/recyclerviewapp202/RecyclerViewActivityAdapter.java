package com.example.aravind_pt1748.recyclerviewapp202;

/**
 * Created by aravind-pt1748 on 26/03/18.
 */

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewActivityAdapter extends RecyclerView.Adapter<RecyclerViewActivityAdapter.MyViewHolder>{

    private String TAG = "TagAdapter";
    private Context context;
    Cursor mCursor = null;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title,genre,year;
        ImageView image;
        public MyViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.titleText);
            genre = view.findViewById(R.id.gameGenreText);
            year = view.findViewById(R.id.releaseYear);
            image = view.findViewById(R.id.gameImage);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public RecyclerViewActivityAdapter(Context context){
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: in Adapter callded");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_detail,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(mCursor!=null){
            if(mCursor.moveToPosition(position)) {
                String title = (mCursor.getString(mCursor.getColumnIndex(GamesContract.GamesMetaData.GAME_NAME)));
                String genre = (mCursor.getString(mCursor.getColumnIndexOrThrow(GamesContract.GamesMetaData.GENRE_NAME)));
                String year = (mCursor.getString(mCursor.getColumnIndexOrThrow(GamesContract.GamesMetaData.RELEASE_DATE)));
                holder.title.setText(title);
                holder.genre.setText(genre);
                holder.year.setText(year);
                int index = (mCursor.getColumnIndex(GamesContract.GamesMetaData.IMAGE_GAME));
                Bitmap bMap = BitmapFactory.decodeByteArray(mCursor.getBlob(index),0,mCursor.getBlob(index).length);
                holder.image.setImageBitmap(bMap);
                Log.d(TAG, "onBindViewHolder: "+title+" : "+genre+" : "+year+" : "+holder.getAdapterPosition()+" : ByteSize ="+bMap.getByteCount()/8);
            }
            else{
                Log.d(TAG, "onBindViewHolder: No words in position");
            }
        }

        /*
        Glide.with(context)
                .load(game.getImage())
                .override(200, 200)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);
           */

    }

    @Override
    public int getItemCount() {
        if(mCursor!=null) {
            return mCursor.getCount();
        }
        else{
            return 0;
        }
    }

    public void setData(Cursor data){
        data.moveToFirst();
        Log.d(TAG, "setData: "+data.getString(data.getColumnIndex(GamesContract.GamesMetaData.GAME_NAME)));
        mCursor=data;
        notifyDataSetChanged();
    }
}