package vill.will.thenewboston;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HTTPExample extends Activity
{
	TextView tvHTTPStuff;
	GetMethodEx test;

	HttpClient client;

	private static final String URL = "http://api.aerisapi.com/sunmoon/moonphases/toronto,on,ca?client_id=HHyE3BkJUrVNR25yvkaTI&client_secret=913m5WSw72be9BU1VGHAs3bhKLW6STp8yRMERzPC";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Super
		super.onCreate(savedInstanceState);

		// Content View
		setContentView(R.layout.activity_http_example);

		// References
		tvHTTPStuff = (TextView) findViewById(R.id.tvHTTP);

		client = new DefaultHttpClient();

		async();

		// // Create HTTP Get Example Class
		// test = new GetMethodEx();
		//
		// new Timer().schedule(new TimerTask()
		// {
		// @Override
		// public void run()
		// {
		// // Retrieve Data
		// try
		// {
		// final String result = test.getInternetData();
		//
		// runOnUiThread(new Runnable()
		// {
		// @Override
		// public void run()
		// {
		// // Retrieve Data From GetMethodEx Object
		// try
		// {
		// // If we got something, set it
		// if (result != null)
		// tvHTTPStuff.setText(result);
		// else
		// // Display Error
		// tvHTTPStuff
		// .setText("Error Retrieving Data");
		// }
		//
		// catch (Exception e)
		// {
		// e.printStackTrace();
		// }
		// }
		// });
		// }
		//
		// catch (Exception e1)
		// {
		// e1.printStackTrace();
		// }
		// }
		// }, 0);
	} // onCreate

	private void async()
	{
		// Create HTTP Get Example Class
		test = new GetMethodEx();

		new Timer().schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				// Retrieve Data
				try
				{
					final JSONObject result = lastItem();

					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							// Retrieve Data From GetMethodEx Object
							try
							{
								// If we got something, set it
								if (result != null)
									tvHTTPStuff.setText(result
											.getString("name"));
								else
									// Display Error
									tvHTTPStuff
											.setText("Error Retrieving Data");
							}

							catch (Exception e)
							{
								e.printStackTrace();
								tvHTTPStuff
								.setText("Error Retrieving Data");
							}
						}
					});
				}

				catch (Exception e1)
				{
					e1.printStackTrace();
				}
			}
		}, 0);
	}

	public JSONObject lastItem() throws ClientProtocolException, IOException,
			JSONException
	{
		StringBuilder urlBuilder = new StringBuilder(URL);

		HttpGet get = new HttpGet(urlBuilder.toString());

		HttpResponse response = client.execute(get);

		int status = response.getStatusLine().getStatusCode();
		if (status == 200)
		{
			HttpEntity httpEntity = response.getEntity();
			
			String data = EntityUtils.toString(httpEntity);
			
			JSONObject jsonObject = new JSONObject(data);
			
			JSONObject responseItem = jsonObject.getJSONArray("response").getJSONObject(0);
			
			return responseItem;
		}

		return null;
	}
} // HTTPExample
