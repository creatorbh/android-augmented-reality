package com.scholtz.aor.activity;

import com.scholtz.aor.R;
import com.scholtz.aor.util.GlobalApp;
import com.scholtz.aor.util.XmlParser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;

/**
 * MainActivity - application starts here
 * Call LoadingTask to parse XML database
 * @author Mike
 *
 */
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new LoadingTask().execute();
	}

	private class LoadingTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			GlobalApp gApp = ((GlobalApp) getApplicationContext());
			XmlParser xml = new XmlParser(getApplicationContext());

			Log.d("aor.load", "Starting XML parser.");
			gApp.setStops(xml.parse());
			Log.d("aor.load", "XML parsing finished.");

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			Intent cameraActivity = new Intent(getApplicationContext(), CameraActivity.class);
			startActivity(cameraActivity);

			finish();
		}
	}

}
