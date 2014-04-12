package vill.will.thenewboston;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextToSpeechActivity extends Activity implements OnClickListener
{
	static final String[] texts =
	{ "I want to go for a walk", "Hey there good lookin'" };

	TextToSpeech tts;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_text_to_speech);

		Button btnTextToSpeech = (Button) findViewById(R.id.btnTextToSpeech);

		btnTextToSpeech.setOnClickListener(this);

		tts = new TextToSpeech(this, new TextToSpeech.OnInitListener()
		{
			@Override
			public void onInit(int status)
			{
				// 
				if (status != TextToSpeech.ERROR)
					tts.setLanguage(Locale.CANADA);
			}
		});
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		Random rand = new Random();
		String random = texts[rand.nextInt(texts.length)];
		
		// Speak
		tts.speak(random, TextToSpeech.QUEUE_FLUSH, null);
	}

	@Override
	protected void onPause()
	{
		if (tts != null)
		{
			tts.stop();
			tts.shutdown();
		}
		//
		super.onPause();
	}
}
