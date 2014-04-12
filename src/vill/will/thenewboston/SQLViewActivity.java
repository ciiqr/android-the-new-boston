package vill.will.thenewboston;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLViewActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		
		// Content View
		setContentView(R.layout.activity_sql_view);
		
		// References
		TextView tvSQLInfo = (TextView) findViewById(R.id.tvSQLInfo);
		
		HotOrNot info = new HotOrNot(this);
		
		info.open();
		
		String data = info.getData();
		
		info.close();
		
		tvSQLInfo.setText(data);
	}
	

}
