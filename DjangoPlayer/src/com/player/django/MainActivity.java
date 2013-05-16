package com.player.django;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends FragmentActivity implements OnGestureListener,SongsViewFragment.onSongItemSelectedListener{

	ListView mainList;
	SongsRetriever sr;
	NonPlayingFragment f2;
	public static boolean flag=true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.frag_list_container);
		
		getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.frag_anim_1, R.anim.frag_anim_1).commit();
		
		NonPlayingFragment play=new NonPlayingFragment();
		
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment3,play).commit();
		
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onResumeFragments() {
		// TODO Auto-generated method stub
		super.onResumeFragments();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void whichItemselected(int position,SongsRetriever song) {
		Intent i=new Intent(this,MusicPlayerService.class);
		if(flag){
		
		try {
			
			flag=false;
			PlayPanelFrag play=new PlayPanelFrag();
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment2,play).commit();
			i.setAction("com.player.django.action.PLAY");
			i.putExtra("name",position);
			i.putExtra("song", song.songsList.get(position).getURI().toString());
			SongsViewFragment.songPosition=position;
			startService(i);
			
		} catch (Exception e) {
			
			Log.d("Activity"," ".concat(e.toString()));
		}
		
	}
		else{
			i.setAction("com.player.django.action.CHANGE");
			i.putExtra("name",position);
			i.putExtra("song", song.songsList.get(position).getURI().toString());
			SongsViewFragment.songPosition=position;
			startService(i);
		}
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		Log.d("MainAct","Fling");
		return true;
	}


	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d("MainAct","Fling");
		
	}


	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		Log.d("MainAct","Fling");
		return true;
	}
		
	
}
