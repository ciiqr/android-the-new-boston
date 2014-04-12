package vill.will.thenewboston;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends Activity implements View.OnClickListener 
{
	/* Declarations */
	
	private EditText personsEmail,
					 intro,
					 personsName,
					 stupidThings,
					 hatefulAction,
					 outro;
	
	private String emailAdd,
				   beginning,
				   name,
				   stupidAction,
				   hatefulAct,
				   out;
	
	private Button sendEmail;
	
	
	/* Methods */
	
	public EmailActivity() {}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_scrollview);
		
		initializeIVars();
		
		sendEmail.setOnClickListener(this);
	}
	
	private void initializeIVars()
	{
		personsEmail = (EditText) findViewById(R.id.etEmailAddress);
		intro = (EditText) findViewById(R.id.etIntro);
		personsName = (EditText) findViewById(R.id.etName);
		stupidThings = (EditText) findViewById(R.id.etThings);
		hatefulAction = (EditText) findViewById(R.id.etAction);
		outro = (EditText) findViewById(R.id.etOutro);
		sendEmail = (Button) findViewById(R.id.btnSendEmail);
	}
	
	
	private void retrieveEditTextText()
	{
		emailAdd = personsEmail.getText().toString();
		beginning = intro.getText().toString();
		name = personsName.getText().toString();
		stupidAction = stupidThings.getText().toString();
		hatefulAct = hatefulAction.getText().toString();
		out = outro.getText().toString();
	}

	@Override
	public void onClick(View v)
	{
		// Retrieve Text From EditText's
		retrieveEditTextText();
		
		String[] emailAddresses = {emailAdd};
		
		String message = "Well hello"
				+ name
				+ " I just wanted to say "
				+ beginning
				+ ". Not only that but I hate when you "
				+ stupidAction
				+ ", that just really makes me crazy. I just want to make you "
				+ hatefulAct
				+ ". Welp, thats all I wanted to chit-chatter about, oh and"
				+ out
				+ ". Oh also if you get bored you should check out www.mybringback.com"
				+ '\n' + "PS. I think I love you...  ";
		
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		emailIntent.setType("plain/text");
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailAddresses);
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "I Hate(Love) You!");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
		startActivity(emailIntent);
	}
}
