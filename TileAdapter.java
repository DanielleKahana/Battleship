package com.example.ran.battleship;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.ran.battleship.Logic.Board;
import com.example.ran.battleship.Logic.Tile;

/**
 * Created by ran on 06/12/2017.
 */

public class TileAdapter extends BaseAdapter {
    private Context mContext;
    private Board mBoard;
    private int mSize;
    private AnimationDrawable anim = null;


    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.none, R.drawable.ship, R.drawable.hit, R.drawable.sunk
    };

    public TileAdapter(Context applicationContext, Board board, int size) {
        mContext = applicationContext;
        mBoard = board;
        this.mSize = size;
    }

    @Override
    public int getCount() {
        return mBoard.getBoardSize() * mBoard.getBoardSize();

    }

    @Override
    public Object getItem(int position) {
        int row = position / mBoard.getBoardSize();
        int col = position % mBoard.getBoardSize();
        return mBoard.getTile(row, col);
    }

    @Override
    public long getItemId(int position) {
        return position / mBoard.getBoardSize();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int row = position / mBoard.getBoardSize();
        int col = position % mBoard.getBoardSize();


        ImageView imageView;
         anim = null;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(mSize, mSize));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(0, 0, 0, 0);

        } else {
            imageView = (ImageView) convertView;
        }

        Tile.Status status = mBoard.getTile(row, col).getTileStatus();

        switch (status) {
            case NONE:
                imageView.setImageResource(mThumbIds[0]);
                break;

            case NONE_X:
                imageView.setImageResource(mThumbIds[0]);
                break;

            case HIT:
                imageView.setImageResource(R.drawable.fire_animation);
                anim = (AnimationDrawable) imageView.getDrawable();
                imageView.setBackgroundResource(mThumbIds[2]);

                //anim.start();
                break;

            case MISS:
               // imageView.setImageResource(R.drawable.miss_animation);
                imageView.setImageResource(R.drawable.miss_animation);
                anim = (AnimationDrawable) imageView.getDrawable();
               // imageView.setBackgroundResource();
                //imageView.setVisibility(View.INVISIBLE);
                //imageView.setBackgroundResource(R.drawable.transperent);
                imageView.setBackgroundColor(Color.TRANSPARENT);

                break;

            case SHIP:
                if (parent.getId() == R.id.player_grid)
                    imageView.setImageResource(mThumbIds[1]);
                if (parent.getId() == R.id.enemy_grid)
                    imageView.setImageResource(mThumbIds[0]);
                break;

            case SUNK:
                imageView.setImageResource(R.drawable.fire_animation);
                anim = (AnimationDrawable) imageView.getDrawable();
                imageView.setImageResource(mThumbIds[3]);
                break;


        }

        if (anim != null)
            anim.start();

        return imageView;
    }





/*
        if (status == Tile.Status.NONE ||status == Tile.Status.NONE_X)
            imageView.setImageResource(mThumbIds[0]);

        if (status == Tile.Status.HIT){
            imageView.setImageResource(R.drawable.fire_animation);
            anim = (AnimationDrawable)imageView.getDrawable();
            imageView.setBackgroundResource(mThumbIds[2]);
            anim.start();

        }

        if (status == Tile.Status.MISS){

            imageView.setImageResource(R.drawable.miss_animation);
            anim2 = (AnimationDrawable)imageView.getDrawable();
            //imageView.setVisibility(View.INVISIBLE);
            anim2.start();
        }


        if (status == Tile.Status.SHIP){
            if (parent.getId() == R.id.player_grid)
                imageView.setImageResource(mThumbIds[1]);
            if (parent.getId() == R.id.enemy_grid)
            imageView.setImageResource(mThumbIds[0]);
        }
        if (status == Tile.Status.SUNK)
            imageView.setImageResource(mThumbIds[3]);

       if (anim != null)
           anim.start();

        return imageView;
    }

*/


}

