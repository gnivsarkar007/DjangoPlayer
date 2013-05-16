package com.player.django;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class ItemSelector implements OnClickListener{

	@Override
	public void onClick(View v) {
		Log.d("Item selected", "Item "+v.getId());
		v.setFocusableInTouchMode(true);
		//v.setBackgroundColor(Color.TRANSPARENT);
		
	}
	
}


