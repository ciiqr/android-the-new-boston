package vill.will.thenewboston;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBarNotifications extends Activity implements OnClickListener
{
	NotificationManager notifManager;
	static final int UNIQUE_ID = 13849294;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status_notifications);
		
		Button btnNotif = (Button) findViewById(R.id.btnStatusNotify);
		btnNotif.setOnClickListener(this);
		
		
		notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		// Clear Previous Notifications
		notifManager.cancel(UNIQUE_ID);
		
	}
	
	
	@SuppressWarnings("deprecation") // Notification should be replaced with Notification.Builder
	@Override
	public void onClick(View v)
	{
		// This
		Intent intent = new Intent(this, StatusBarNotifications.class);
		// 
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		String body = "OMG I'm just about done with this tut";
		String title = "The New Boston";
		Notification notif = new Notification(R.drawable.star_shadow, body, System.currentTimeMillis());
		
		notif.setLatestEventInfo(this, title, body, pendingIntent);
		
		notif.defaults = Notification.DEFAULT_ALL;
		
		notifManager.notify(UNIQUE_ID, notif);
		
		// So as to prevent Multiple Instances
		finish();
	}
}
