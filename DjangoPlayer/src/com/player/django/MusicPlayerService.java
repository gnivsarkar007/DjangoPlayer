package com.player.django;

 import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MusicPlayerService extends Service implements
		OnCompletionListener, OnPreparedListener, OnErrorListener {

	MediaPlayer myPlayer;
	
	@Override
	public void onCreate() {
		
		myPlayer=new MediaPlayer();
		myPlayer.setOnPreparedListener(this);
		myPlayer.setOnErrorListener(this);
		myPlayer.setOnCompletionListener(this);
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(intent.getAction().equalsIgnoreCase("com.player.django.action.PLAY")){
			try {
				String i=intent.getStringExtra("song");
				myPlayer.setDataSource(getApplicationContext(),Uri.parse(i));
				myPlayer.prepareAsync();
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(intent.getAction().equalsIgnoreCase("com.player.django.action.CHANGE")){
		 myPlayer.reset();
		try {
			String i=intent.getStringExtra("song");
			myPlayer.setDataSource(getApplicationContext(),Uri.parse(i));
			myPlayer.prepareAsync();
		}  catch (Exception e) {
			e.printStackTrace();
		}
		}
		else if(intent.getAction().equalsIgnoreCase("com.player.django.action.PAUSE")){
			if(myPlayer.isPlaying())myPlayer.pause();
		}
		else if(intent.getAction().equalsIgnoreCase("com.player.django.action.UNPAUSE")){
			if(!myPlayer.isPlaying()) myPlayer.start();
		}
		return START_REDELIVER_INTENT;
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onRebind(Intent intent) {
		// TODO Auto-generated method stub
		super.onRebind(intent);
	}

	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		myPlayer.release();
		myPlayer=null;
		super.onDestroy();
	}

	@Override
	public void onPrepared(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		arg0.start();
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		// TODO Auto-generated method stub
		
		Toast.makeText(getApplicationContext(), "Ended", Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onError(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
