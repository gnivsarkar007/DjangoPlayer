package com.player.django;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SongsViewFragment extends ListFragment{

	ListView ls;
	SongsRetriever sr;
	public static int songPosition;
	public interface onSongItemSelectedListener{
		public void whichItemselected(int position,SongsRetriever sr);
	}

	onSongItemSelectedListener clickListener;
	
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Toast.makeText(getActivity(), " Attached ", Toast.LENGTH_SHORT).show();
		 try {
	            clickListener = (onSongItemSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnHeadlineSelectedListener");
	        }
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		sr=new SongsRetriever(getActivity().getContentResolver());
		sr.prepareAudioContent();	
		final ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < sr.songsList.size(); ++i) {
			list.add(" ");
		}
		SongsListAdapter adapter= new SongsListAdapter(getActivity(), R.layout.list_item_layout, R.id.text_song_name, list, sr);
		Log.d("Adapter","GGGG"+list.size());
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		super.onActivityCreated(savedInstanceState);
	}
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	    // TODO Auto-generated method stub
	    
	    clickListener.whichItemselected(position,sr);

	}
}
