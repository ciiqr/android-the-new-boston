package vill.will.thenewboston;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class VoiceActivity extends Activity implements OnClickListener
{
	static final int CHECK = 10101;
	
	ListView lvVoiceReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_voice);
		
		lvVoiceReturn = (ListView) findViewById(R.id.lvVoiceReturn);
		
		Button btnVoice = (Button) findViewById(R.id.btnVoice);
		btnVoice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		// Speech Intent
		Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		// Language
		i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		// Prompt
		i.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak up.");
		
		startActivityForResult(i, CHECK);
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == RESULT_OK && requestCode == CHECK)
		{
			ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			
			lvVoiceReturn.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results));
		}
		
		// Super
		super.onActivityResult(requestCode, resultCode, data);
	}

	

}
