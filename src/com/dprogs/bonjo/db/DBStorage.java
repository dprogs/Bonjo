package com.dprogs.bonjo.db;

import java.util.List;

import com.dprogs.bonjo.utils.ALog;

import android.content.Context;
import android.util.Log;

public class DBStorage {
	private final static String TAG = "DBStorage";
	
	final static String format1 = "%2d%17s%22s%18s%16s%16s";
	final static String format2 = "%2s%16s%22s%18s%16s%16s";

	private static SQLiteMyHelper database;
	
	public static SQLiteMyHelper getDatabase() {
		return database;
	}
	
	public static void initDatabase(Context context) {
		database = new SQLiteMyHelper(context, DBAppData.DB_NAME, null, 1);
        database.onCreate(database.getWritableDatabase());
 		Log.i(TAG, "Database init");
	}
	
    public static void readSongs() {
    	ALog.w(TAG, "# Read songs table");
    	
        List<SongFile> songList = database.getAllSongs();
        Log.w("MyTag", "[SONGS TABLE]");
        Log.i("MyTag", String.format(format2, "ID", "FOLDER", "FILE", "SONG", "ARTIST", "ALBUM"));
        for (SongFile sf : songList) {
        	Log.i("MyTag", String.format(format1, sf.getId(), sf.getDestFolder(), sf.getFileName(), sf.getSong(), sf.getArtist(), sf.getAlbum()));
        }
    }

}