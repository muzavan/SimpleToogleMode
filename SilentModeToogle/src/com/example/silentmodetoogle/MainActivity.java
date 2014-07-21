package com.example.silentmodetoogle;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private AudioManager mAudioManager;
	private boolean mPhoneIsSilent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageView image = (ImageView) findViewById(R.id.phone_image);
		Button toogleButton = (Button) findViewById(R.id.toggleButton);

		mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		checkIfPhoneIsSilent();
		toogleButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (mPhoneIsSilent) {
					// Change back to normal mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
					mPhoneIsSilent = false;
				} else {
					// Change to silent mode
					mAudioManager
							.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					mPhoneIsSilent = true;
				}
				// Now toggle the UI again
				toggleUi();
			}
		});
	}

	/**
	 * Checks to see if the phone is currently in Silent mode.
	 */
	private void checkIfPhoneIsSilent() {
		int ringerMode = mAudioManager.getRingerMode();
		if (ringerMode == AudioManager.RINGER_MODE_SILENT) {
			mPhoneIsSilent = true;
		} else {
			mPhoneIsSilent = false;
		}
	}

	/**
	 * Toggles the UI images from silent to normal and vice versa.
	 */
	private void toggleUi() {
		ImageView imageView = (ImageView) findViewById(R.id.phone_image);
		Drawable newPhoneImage;
		if (mPhoneIsSilent) {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_off);
		} else {
			newPhoneImage = getResources().getDrawable(R.drawable.phone_on);
		}
		imageView.setImageDrawable(newPhoneImage);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		checkIfPhoneIsSilent();
		toggleUi();
	}

}
