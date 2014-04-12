package vill.will.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLiteExample extends Activity implements OnClickListener
{
	private Button btnSQLUpdate, btnSQLView, btnSQLModify, btnSQLGetInfo,
			btnSQLDelete;
	private EditText etSQLName, etSQLHotness, etSQLRow;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Content View
		setContentView(R.layout.sqlite_example);

		// References
		btnSQLUpdate = (Button) findViewById(R.id.btnSQLUpdate);
		btnSQLView = (Button) findViewById(R.id.btnSQLOpenView);
		btnSQLModify = (Button) findViewById(R.id.btnSQLEditEntry);
		btnSQLGetInfo = (Button) findViewById(R.id.btnSQLGetInfo);
		btnSQLDelete = (Button) findViewById(R.id.btnSQLDeleteEntry);

		etSQLName = (EditText) findViewById(R.id.etSQLName);
		etSQLHotness = (EditText) findViewById(R.id.etSQLHotness);
		etSQLRow = (EditText) findViewById(R.id.etSQLRowInfo);

		// Event Handlers
		btnSQLUpdate.setOnClickListener(this);
		btnSQLView.setOnClickListener(this);
		btnSQLModify.setOnClickListener(this);
		btnSQLGetInfo.setOnClickListener(this);
		btnSQLDelete.setOnClickListener(this);

	}

	@Override
	public void onClick(View v)
	{
		//
		switch (v.getId())
		{
		case R.id.btnSQLUpdate:
		{
			boolean successful = true;
			try
			{
				String name = etSQLName.getText().toString();
				String hotness = etSQLHotness.getText().toString();

				HotOrNot entry = new HotOrNot(this);
				entry.open();

				entry.createEntry(name, hotness);

				entry.close();
			}

			catch (Exception e)
			{
				successful = false;
			}

			finally
			{
				if (successful)
				{
					// Create Dialog
					Dialog dialog = new Dialog(this);
					// Set Title
					dialog.setTitle("Yeah!");

					// Create Message Text View
					TextView tvMessage = new TextView(this);
					tvMessage.setText("Success");

					// Set Dialog View
					dialog.setContentView(tvMessage);

					// Show Dialog
					dialog.show();
				}
			}

			break;
		}

		case R.id.btnSQLOpenView:
		{
			Intent switchToDatabaseView = new Intent(
					"vill.will.thenewboston.SQLITE_VIEW");
			startActivity(switchToDatabaseView);
			break;
		}
		case R.id.btnSQLGetInfo:
		{
			try
			{
				String s = etSQLRow.getText().toString();
				long l = Long.parseLong(s);

				HotOrNot hon = new HotOrNot(this);
				hon.open();

				String returnedName = hon.getName(l);
				String returnedHotness = hon.getHotness(l);

				hon.close();

				etSQLName.setText(returnedName);
				etSQLHotness.setText(returnedHotness);
			}

			catch (Exception e)
			{
				// Create Dialog
				Dialog dialog = new Dialog(this);
				// Set Title
				dialog.setTitle("Error Retrieving Entry");

				// Create Message Text View
				TextView tvMessage = new TextView(this);
				tvMessage.setText(e.getMessage());

				// Set Dialog View
				dialog.setContentView(tvMessage);

				// Show Dialog
				dialog.show();
			}

			break;
		}

		case R.id.btnSQLEditEntry:
		{
			boolean successful = true;
			try
			{
				String s = etSQLRow.getText().toString();
				long l = Long.parseLong(s);
				String newName = etSQLName.getText().toString();
				String newHotness = etSQLHotness.getText().toString();

				HotOrNot hon = new HotOrNot(this);
				hon.open();

				hon.updateEntry(l, newName, newHotness);

				hon.close();
			}

			catch (Exception e)
			{
				successful = false;
			}

			finally
			{
				if (successful)
				{
					// Create Dialog
					Dialog dialog = new Dialog(this);
					// Set Title
					dialog.setTitle("Entry Updated");

					// Create Message Text View
					TextView tvMessage = new TextView(this);
					tvMessage.setText("Success");

					// Set Dialog View
					dialog.setContentView(tvMessage);

					// Show Dialog
					dialog.show();
				}
			}
			break;
		}

		case R.id.btnSQLDeleteEntry:
		{
			String s = etSQLRow.getText().toString();
			long l = Long.parseLong(s);

			HotOrNot hon = new HotOrNot(this);
			hon.open();

			hon.deleteEntry(l);

			hon.close();
			break;
		}

		default:
			break;
		}
	}
}
