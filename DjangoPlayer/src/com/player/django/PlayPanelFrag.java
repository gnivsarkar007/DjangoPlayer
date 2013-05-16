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
	// TODO Auto-generated method stub
	fl=inflater.inflate(R.layout.play_panel, null);
	
	return fl;
}
@Override
public void onActivityCreated(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onActivityCreated(savedInstanceState);
	//fl.setOnClickListener(this);
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
	// TODO Auto-generated method stub
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
	case R.id.button5:
	case R.id.button2: break;
	}
	getActivity().startService(i);
}
@Override
public void whichItemselected(int position, SongsRetriever sr) {
	// TODO Auto-generated method stub
	
}


}
