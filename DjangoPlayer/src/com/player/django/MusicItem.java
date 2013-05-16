package com.player.django;

import android.content.ContentUris;
import android.net.Uri;

public class MusicItem {

	public long id;
	public String artist;
	public String title;
	public String album;
	public long duration;
	public String songPath;

public MusicItem(long _id,String _art,String _name,String _alb,long _dur,String _path){
	id=_id;
	artist=_art;
	title=_name;
	album=_alb;
	duration=_dur;
	songPath=_path;
}

public MusicItem() {
	// TODO Auto-generated constructor stub
}

public Uri getURI() {
    return ContentUris.withAppendedId(
            android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id);
}
}
