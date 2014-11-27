package de.luhmer.owncloudnewsreader.reader.GoogleReaderApi;

import android.app.Activity;
import android.os.AsyncTask;

import java.util.ArrayList;

import de.luhmer.owncloudnewsreader.model.RssFile;
import de.luhmer.owncloudnewsreader.reader.OnAsyncTaskCompletedListener;

public class AsyncTask_GetFeeds extends AsyncTask<Object, Void, ArrayList<RssFile>> {

		private Activity context;
		private int task_id;
		private OnAsyncTaskCompletedListener listener;

		public AsyncTask_GetFeeds(final int task_id, final Activity context, final OnAsyncTaskCompletedListener listener) {
			  super();

			  this.context = context;
			  this.task_id = task_id;
			  this.listener = listener;
		}

		//Activity meldet sich zurueck nach OrientationChange
		public void attach(final Activity context, final OnAsyncTaskCompletedListener listener) {
			this.context = context;
			this.listener = listener;
		}

		//Activity meldet sich ab
		public void detach() {
			if (context != null) {
				context = null;
			}

			if (listener != null) {
				listener = null;
			}
		}


		@Override
		protected ArrayList<RssFile> doInBackground(Object... params) {
			String username = (String) params[0];
			String password = (String) params[1];
			String _TAG_LABEL = (String) params[2];

			try {
				//String authKey = AuthenticationManager.getGoogleAuthKey(username, password);
				return GoogleReaderMethods.getFeeds(username, password, _TAG_LABEL);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	    @Override
	    protected void onPostExecute(ArrayList<RssFile> values) {

	    	listener.onAsyncTaskCompleted(task_id, values);

			detach();
	    }
}
