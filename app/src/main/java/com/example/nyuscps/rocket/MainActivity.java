package com.example.nyuscps.rocket;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BuildTask buildTask = new BuildTask();
        buildTask.execute();
    }

    private class BuildTask extends AsyncTask<Void, Integer, Void> {

        //This method is executed by the second thread.


        //It gets its arguments from the execute method of GetReadableDatabaseTask.
        //Its return value is passed as an argument to onPostExecute.

        @Override
        protected Void doInBackground(Void... v) {
            for (int i = 0; i <= 100; ++i) {
                publishProgress(i);
                //Sleep for 1/60 of a second.
                try {
                    Thread.sleep(100L);   //milliseconds
                } catch (InterruptedException interruptedException) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView textView = (TextView)findViewById(R.id.build);
            textView.setText(values[0].toString());
        }

        //This method is executed by the UI thread when doInBackground has finished.
        //Its argument is the return value ofDoInBackground.

        @Override
        protected void onPostExecute(Void v) {
            buildCompleted();
        }
    }

    private void buildCompleted() {
        Button button = (Button)findViewById(R.id.launch);
        button.setEnabled(true);
        button.setBackgroundColor(Color.BLUE);

        FuelTask fuelTask = new FuelTask();
        fuelTask.execute();
    }

    private class FuelTask extends AsyncTask<Void, Integer, Void> {

        //This method is executed by the second thread.


        //It gets its arguments from the execute method of GetReadableDatabaseTask.
        //Its return value is passed as an argument to onPostExecute.

        @Override
        protected Void doInBackground(Void... v) {
            for (int i = 0; i <= 100; ++i) {
                publishProgress(i);
                //Sleep for 1/60 of a second.
                try {
                    Thread.sleep(100L);   //milliseconds
                } catch (InterruptedException interruptedException) {
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView textView = (TextView)findViewById(R.id.fuel);
            textView.setText(values[0].toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
