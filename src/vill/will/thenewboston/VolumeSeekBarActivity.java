package vill.will.thenewboston;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class VolumeSeekBarActivity extends Activity implements OnSeekBarChangeListener
{
	SeekBar sbVolume;
	MediaPlayer mediaPlayer;
	AudioManager audioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Layout
		setContentView(R.layout.activity_volume_seek_bar);
		
		// Media Player
		mediaPlayer = MediaPlayer.create(this, R.raw.tranquil);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		
		// Audio Manager
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		
		// References
		sbVolume = (SeekBar) findViewById(R.id.seekBarVolume);
		sbVolume.setMax(maxVolume);
		sbVolume.setProgress(currVolume);
		sbVolume.setOnSeekBarChangeListener(this);
	}

	@Override
	protected void onPause()
	{
		// Super
		super.onPause();
		
		// Media Player
		mediaPlayer.pause();
	}
	
	@Override
	protected void onResume()
	{
		// Super
		super.onResume();
		
		// Media Player
		mediaPlayer.start();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser)
	{
		// Change Volume
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar)
	{
		// TODO Auto-generated method stub
		
	}
	
	
}
