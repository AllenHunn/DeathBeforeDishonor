package com.moonlite.dbd;

import android.app.Activity;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

public class MainActivity extends Activity {
    public static final int MIN_VALUE = -30;
    public static final int MAX_VALUE = 50;
    private int meValue = 0;
    private int oppValue = 0;
    private WheelView wv;
    private WheelView wvo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            meValue = savedInstanceState.getInt("meValue");
            oppValue = savedInstanceState.getInt("oppValue");
        }
        try{
            wv = (WheelView) findViewById(R.id.wheel_me);
            NumericWheelAdapter nwa = new NumericWheelAdapter(this, MIN_VALUE, MAX_VALUE);
            wv.setViewAdapter(nwa);
            setMeValue(meValue);

            wvo = (WheelView) findViewById(R.id.wheel_opponent);
            NumericWheelAdapter nwao = new NumericWheelAdapter(this, MIN_VALUE, MAX_VALUE);
            wvo.setViewAdapter(nwao);
            setOppValue(oppValue);
        }
        catch(Exception ex){
            Log.d("DBD", ex.toString());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("meValue", getMeValue());
        outState.putInt("oppValue", getOppValue());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_reset){
            setMeValue(0);
            setOppValue(0);
        }
        return super.onOptionsItemSelected(item);
    }

    public int getMeValue() {
        meValue = ((NumericWheelAdapter)wv.getViewAdapter()).getValueAt(wv.getCurrentItem());
        return meValue;
    }

    public int getOppValue() {
        oppValue = ((NumericWheelAdapter)wvo.getViewAdapter()).getValueAt(wvo.getCurrentItem());
        return oppValue;
    }

    public void setMeValue(int value) {
        meValue = value;
        wv.setCurrentItem(((NumericWheelAdapter)wv.getViewAdapter()).getItemIndex(meValue));
    }

    public void setOppValue(int value) {
        oppValue = value;
        wvo.setCurrentItem(((NumericWheelAdapter)wvo.getViewAdapter()).getItemIndex(oppValue));
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MainFragment extends Fragment {
        public static final int MIN_VALUE = -30;
        public static final int MAX_VALUE = 50;
        private int meValue;
        private int oppValue;
        private WheelView wv;
        private WheelView wvo;

        public MainFragment(int meValue, int oppValue) {
            this.meValue = meValue;
            this.oppValue = oppValue;
        }

        public MainFragment(){}

        @Override
        public void onSaveInstanceState(Bundle outstate){
            outstate.putInt("me", getMeValue());
            outstate.putInt("opp", getOppValue());
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            if (savedInstanceState != null){
                meValue = savedInstanceState.getInt("me");
                oppValue = savedInstanceState.getInt("opp");
            }
            try{
                wv = (WheelView) rootView.findViewById(R.id.wheel_me);
                NumericWheelAdapter nwa = new NumericWheelAdapter(getActivity(), MIN_VALUE, MAX_VALUE);
                wv.setViewAdapter(nwa);
                wv.setCurrentItem(nwa.getItemIndex(getMeValue()));

                wvo = (WheelView) rootView.findViewById(R.id.wheel_opponent);
                NumericWheelAdapter nwao = new NumericWheelAdapter(getActivity(), MIN_VALUE, MAX_VALUE);
                wvo.setViewAdapter(nwao);
                wvo.setCurrentItem(nwao.getItemIndex(getOppValue()));
            }
            catch(Exception ex){
                Log.d("DBD", ex.toString());
            }
            return rootView;
        }

        public int getMeValue() {
            meValue = ((NumericWheelAdapter)wv.getViewAdapter()).getValueAt(wv.getCurrentItem());
            return meValue;
        }

        public int getOppValue() {
            oppValue = ((NumericWheelAdapter)wvo.getViewAdapter()).getValueAt(wvo.getCurrentItem());
            return oppValue;
        }
    }

}
