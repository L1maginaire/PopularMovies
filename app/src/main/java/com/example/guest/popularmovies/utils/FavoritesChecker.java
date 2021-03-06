package com.example.guest.popularmovies.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.example.guest.popularmovies.mvp.model.SingleMovie;

import static com.example.guest.popularmovies.db.MoviesContract.Entry.COLUMN_MOV_ID;
import static com.example.guest.popularmovies.db.MoviesContract.Entry.CONTENT_URI;

/**
 * Created by l1maginaire on 4/3/18.
 */

public class FavoritesChecker {
    public static Integer isFavorite(Context context, SingleMovie movie) {
        ContentResolver contentResolver = context.getContentResolver();
        Cursor c = null;
        if (movie.getId() != 0) {
            c = contentResolver.query(CONTENT_URI,null,COLUMN_MOV_ID + " = ?",
                    new String[]{String.valueOf(movie.getId())},null);
        }
        if (c != null) {
            c.moveToFirst();
            if (c.getCount() > 0 && c.getInt(c.getColumnIndex(COLUMN_MOV_ID)) == movie.getId()) {
                c.close();
                return 1;
            }
            c.close();
        }
        return 0;
    }
}
