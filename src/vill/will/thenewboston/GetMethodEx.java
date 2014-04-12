package vill.will.thenewboston;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class GetMethodEx
{
	public String getInternetData() throws Exception
	{
		BufferedReader in = null;
		String result;

		try
		{
			// Create Request (With URI)
			HttpGet request = new HttpGet(new URI("http://www.mybringback.com"));

			// Create Client
			HttpClient client = new DefaultHttpClient();

			// Get Response
			HttpResponse response = client.execute(request);

			// Create Buffered Reader (with data)
			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			// Variables For Reading From Buffered Reader into String
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			String newLine = System.getProperty("line.separator");

			// Read All Lines
			while ((line = in.readLine()) != null)
				stringBuilder.append(line + newLine);

			// Get String
			result = stringBuilder.toString();
			
			return result;
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				}

				catch (Exception e2)
				{
					e2.printStackTrace();
				}
			}
		}
		
		return null;

	} // getInternetData

} // GetMethodEx
