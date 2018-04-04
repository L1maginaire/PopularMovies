package com.example.guest.popularmovies.db;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.guest.popularmovies.db.MoviesContract.Entry;
import com.example.guest.popularmovies.mvp.model.SingleMovie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.guest.popularmovies.db.MoviesContract.Entry.*;

/**
 * Created by l1maginaire on 3/22/18.
 */

public class MoviesDbHelper extends SQLiteOpenHelper {
    private static final String TAG = MoviesDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "movdb.db";
    private static final int DATABASE_VERSION = 1;

    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public List<SingleMovie> getSavedMovies() {
        List<SingleMovie> movies = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        do {
                            SingleMovie movie = new SingleMovie();
                            ArrayList<Integer> genreIds = new ArrayList<>();
                            List<String> list = Arrays.asList(cursor.getString(cursor.getColumnIndex(COLUMN_GENRE_IDS)).split("\\s*,\\s*"));
                            for (String s : list) {
                                genreIds.add(Integer.valueOf(s));
                            }
                            movie.setGenreIds(genreIds);
                            movie.setId(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_MOV_ID))));
                            movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(COLUMN_BACKDROP_PATH)));
                            movie.setOriginalTitle(cursor.getString(cursor.getColumnIndex(COLUMN_ORIGINAL_TITLE)));
                            movie.setOverview(cursor.getString(cursor.getColumnIndex(COLUMN_OVERVIEW)));
                            movie.setPopularity(Double.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_POPULARITY))));
                            movie.setPosterPath(cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH)));
                            movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE)));
                            movie.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
                            movie.setVoteAverage(Double.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE))));
                            movie.setVoteCount(Integer.valueOf(cursor.getString(cursor.getColumnIndex(COLUMN_VOTE_COUNT))));
                            movies.add(movie);
                        } while (cursor.moveToNext());
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (SQLException e) {
            Log.d(TAG, e.getMessage());
        }
        return movies;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " + Entry.TABLE_NAME + " (" +
                Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + //todo necessity
                COLUMN_MOV_ID + " INTEGER, " +
                COLUMN_BACKDROP_PATH + " TEXT, " +
                COLUMN_GENRE_IDS + " TEXT, " +
                COLUMN_ORIGINAL_TITLE + " TEXT, " +
                COLUMN_OVERVIEW + " TEXT, " +
                COLUMN_POPULARITY + " DOUBLE, " +
                COLUMN_POSTER_PATH + " TEXT, " +
                COLUMN_RELEASE_DATE + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_VOTE_AVERAGE + " DOUBLE, " +
                COLUMN_VOTE_COUNT + " INTEGER" +
                "); ";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Entry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}