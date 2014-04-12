package vill.will.thenewboston;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener, OnLongClickListener
{
	/* Declarations */
	SoundPool soundPool;
	MediaPlayer mediaPlayer;
	int explosion = 0;

	/* Methods */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Create View
		View v = new View(this);
		v.setOnClickListener(this);
		v.setOnLongClickListener(this);

		// Set Convent View
		setContentView(v);

		// Setup Sound Pool
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);

		// Sound to Play (Explosion)
		explosion = soundPool.load(this, R.raw.explosion, 1);
		
		// Media Player (Long Click Listener)
		mediaPlayer = MediaPlayer.create(this, R.raw.explosion);
	}

	@Override
	public void onClick(View v)
	{
		if (explosion != 0)
			// Play Explosion
			soundPool.play(explosion, 1, 1, 0, 0, 1);
	}

	@Override
	public boolean onLongClick(View v)
	{
		// Play Sound
		mediaPlayer.start();
		
		// 
		return false;
	}
}
