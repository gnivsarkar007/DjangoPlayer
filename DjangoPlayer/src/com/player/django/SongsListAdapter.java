package com.player.django;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils.TruncateAt;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SongsListAdapter extends ArrayAdapter<String>{

	Context mContext;
	int res_id;
	SongsRetriever myRetriever;
	List<String>songs;
	public ItemSelector clickSelector;
	public SongsListAdapter(Context context, int resource,
			int textViewResourceId, List<String> objects,SongsRetriever sr) {
		
		super(context, resource, textViewResourceId, objects);
		mContext=context;
		
	myRetriever= sr;
		
		songs=new ArrayList<String>();
		for(int i=0;i<objects.size();i++)
			songs.add(objects.get(i));
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
			
		ViewHolder vh = new ViewHolder();
		
			
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item_layout, null);
		
		
			vh.txtSongName= (TextView) convertView
					.findViewById(R.id.text_song_name);

			vh.txtArtist= (TextView) convertView
					.findViewById(R.id.text_artist_name);
			
			vh.imgPhoto= (Button) convertView
					.findViewById(R.id.playButton1);
			
			convertView.setTag(vh);
		
		
		
		vh.txtSongName.setText(myRetriever.songsList.get(position).title.trim());
		vh.txtSongName.setSingleLine();
		vh.txtSongName.setEllipsize(TruncateAt.END);
		vh.txtArtist.setText(myRetriever.songsList.get(position).artist);
		vh.txtArtist.setSingleLine();
		convertView.setId(position);
		
		//convertView.setOnClickListener(clickSelector);
		
		Log.d("Inside adapter","Sent view "+myRetriever.songsList.get(position).title);
		return  convertView; //super.getView(position, convertView, parent);
	}

	static class ViewHolder {
		TextView txtSongName;
		TextView txtArtist;
		Button imgPhoto;
	}

	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}


}

