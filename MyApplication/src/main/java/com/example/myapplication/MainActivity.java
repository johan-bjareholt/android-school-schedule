package com.example.myapplication;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.GregorianCalendar;

public class MainActivity extends FragmentActivity{

    /**
     * The serialization (saved instance state) Bundle key representing the
     * current dropdown position.
     */
    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the action bar to show a dropdown list.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        android.content.SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        // Get personnr
        String personnr;
        personnr = getPrefs.getString("textbox_personnr", null);

        GregorianCalendar calendar = new GregorianCalendar();

        // Get if daily or weekly
        boolean daily;
        daily = getPrefs.getBoolean("checkbox_daily", false);
        String day = "0";
        if (daily){
            switch(calendar.get(GregorianCalendar.DAY_OF_WEEK)){
                case GregorianCalendar.MONDAY:
                    day = "1";
                    break;
                case GregorianCalendar.TUESDAY:
                    day = "2";
                    break;
                case GregorianCalendar.WEDNESDAY:
                    day = "4";
                    break;
                case GregorianCalendar.THURSDAY:
                    day = "8";
                    break;
                case GregorianCalendar.FRIDAY:
                    day = "16";
                    break;
            }
        }

        // Get which week/period
        String date;
        date = getPrefs.getString("", "Weekly");

        String week="";
        String period="";
        if (date == "P1"){
            period = "P1";
        }
        else if (date == "P2"){
            period = "P2";
        }
        else {
            week = Integer.toString(calendar.get(GregorianCalendar.WEEK_OF_YEAR));
        }

        android.util.Log.w("myApp", "Daily:"+Boolean.toString(daily) + "    Week:" + Integer.toString(GregorianCalendar.WEEK_OF_YEAR) + "   Day of week:"+ Integer.toString(java.util.GregorianCalendar.DAY_OF_WEEK) +"   personnr:" + personnr);

        WebView theWebView = (WebView) findViewById(R.id.webView);
        int width = theWebView.getWidth();
        int height = theWebView.getHeight();
        if (personnr != null){
            theWebView.loadUrl("http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&schoolid=18600/sv-se&id=" + personnr + "&width=" + width + "&height=" + height + "&period=" + period + "&week=" + week + "&day=" + day);
            android.util.Log.w("myApp", "http://www.novasoftware.se/ImgGen/schedulegenerator.aspx?format=png&schoolid=18600/sv-se&id=" + personnr + "&width=" + width + "&height=" + height + "&period=" + period + "&week=" + week + "&day=" + day);
        }
        else {
            String summary = "<html><body><h1>Var vänlig skriv in ditt personnummer i inställningarna!</h1></body></html>";
            theWebView.loadData(summary, "text/html", null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent pref = new Intent("com.example.myapplication.Preferences");
                startActivity(pref);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dummy, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
}
