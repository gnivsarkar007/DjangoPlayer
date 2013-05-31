package com.player.django;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PlayPanelFrag extends Fragment  implements OnClickListener, SongsViewFragment.onSongItemSelectedListener{
View fl;
MainActivity mn;
public static boolean pauseFlag=true;

@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	
	fl=inflater.inflate(R.layout.play_panel, null);
		return fl;
}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	
	super.onActivityCreated(savedInstanceState);
	Button b1=(Button)getActivity().findViewById(R.id.button3);
	b1.setOnClickListener(this);
	mn=(MainActivity) getActivity();
	getActivity().findViewById(R.id.b1).setOnClickListener(this);
	getActivity().findViewById(R.id.button2).setOnClickListener(this);
	getActivity().findViewById(R.id.button4).setOnClickListener(this);
	getActivity().findViewById(R.id.button5).setOnClickListener(this);
	
}

@Override
public void onClick(View v) {
	
	Intent i=new Intent(this.getActivity(),MusicPlayerService.class);
	switch(v.getId()){
	case R.id.button3:if(!pauseFlag){
		i.setAction("com.player.django.action.UNPAUSE");
		pauseFlag=true;
	}
	else {
		i.setAction("com.player.django.action.PAUSE"); 
		pauseFlag=false;
		}
	break;
	case R.id.b1:
	case R.id.button4:
		i.setAction("com.player.django.action.NEXT");
		MainActivity.NOW_PLAYING_POSITION+=1;
		if(MainActivity.NOW_PLAYING_POSITION>mn.sr.songsList.size()) MainActivity.NOW_PLAYING_POSITION=0;
		i.putExtra("name", MainActivity.NOW_PLAYING_POSITION);
		i.putExtra("song", mn.sr.songsList.get(MainActivity.NOW_PLAYING_POSITION).getURI()
				.toString());
		break;
	case R.id.button5:
	case R.id.button2: i.setAction("com.player.django.action.PREVIOUS");
	MainActivity.NOW_PLAYING_POSITION-=1;
	if(MainActivity.NOW_PLAYING_POSITION<0) MainActivity.NOW_PLAYING_POSITION=mn.sr.songsList.size();
	i.putExtra("name", MainActivity.NOW_PLAYING_POSITION);
	i.putExtra("song", mn.sr.songsList.get(MainActivity.NOW_PLAYING_POSITION).getURI()
			.toString());
	break;
	}
	getActivity().startService(i);
}
@Override
public void whichItemselected(int position, SongsRetriever sr) {
	// TODO Auto-generated method stub
	
}


}
