package com.player.django;

import java.util.ArrayList;
import java.util.Locale;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class SongsRetriever {

	public static Uri songURI;
	public static ContentResolver myAudioFileResolver;
	public ArrayList<MusicItem> songsList;
	public String[] projections={MediaStore.Audio.Media.TITLE,MediaStore.Audio.Media.ALBUM,MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media._ID,MediaStore.Audio.Media.DURATION,MediaStore.Audio.Media.DATA};
	public Cursor readSongs;
	public SongsRetriever(ContentResolver _cr) {
		myAudioFileResolver = _cr;
		songURI = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		songsList= new ArrayList<MusicItem>();
			}

	public void prepareAudioContent() {
		 readSongs = myAudioFileResolver.query(songURI, projections,
				MediaStore.Audio.Media.IS_MUSIC + " = 01", null,projections[0]+"_KEY");
		
		
		int titleColumn,albumColumn,artistColumn,idColumn,durColumn;
		if (readSongs == null) {
			Log.d("SongsRetriever", "Database error.");
		} else {
			 titleColumn = readSongs
					.getColumnIndex(MediaStore.Audio.Media.TITLE);
			 albumColumn = readSongs
					.getColumnIndex(MediaStore.Audio.Media.ALBUM);
			 artistColumn = readSongs
					.getColumnIndex(MediaStore.Audio.Media.ARTIST);
			 idColumn = readSongs.getColumnIndex(MediaStore.Audio.Media._ID);
			 durColumn = readSongs
					.getColumnIndex(MediaStore.Audio.Media.DURATION);

			if (!readSongs.moveToFirst())
				Log.d("SongsRetriever",
					"Did not read any songs from the external storage.");
			else {
				do {
					MusicItem newItem = new MusicItem();
					newItem.album = readSongs.getString(albumColumn);
					newItem.artist = readSongs.getString(artistColumn);
					newItem.id = readSongs.getLong(idColumn);
					newItem.title = readSongs.getString(titleColumn);
					newItem.duration = readSongs.getLong(durColumn);
					newItem.songPath=songURI.getPath()+"/"+readSongs.getString(titleColumn)+".mp3";
					songsList.add(newItem);
					
				}while (readSongs.moveToNext());

			}
		}

	}

	public static Uri getURI(){
		return android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
	}
	public static ContentResolver getContentResolver() {
		return myAudioFileResolver;
	}
}
