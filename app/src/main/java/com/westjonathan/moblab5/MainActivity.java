package com.westjonathan.moblab5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Declare android elements
    LifecycleCounts cycleCounts;
    SharedPreferences sharedPreferences;
    String[] events;
    GridLayout grid;
    TextView[] currCounts;
    TextView[] allTimeCounts;
    @Override
    protected void onStart() {
        super.onStart();
        // Count for current run:
        int count = cycleCounts.getStarts();
        cycleCounts.setStarts(++count);
        currCounts[1].setText("" + count);
        // All time count:
        count = sharedPreferences.getInt("onStart", 0);
        sharedPreferences.edit().putInt("onStart", ++count).apply();
        allTimeCounts[1].setText(""+count);
    }
    @Override
    protected void onResume() {
        super.onResume();
        int count = cycleCounts.getResumes();
        cycleCounts.setResumes(++count);
        currCounts[2].setText("" + count);
        // All time count:
        count = sharedPreferences.getInt("onResume", 0);
        sharedPreferences.edit().putInt("onResume", ++count).apply();
        allTimeCounts[2].setText(""+count);
    }
    @Override
    protected void onPause(){
        super.onPause();
        int count = cycleCounts.getPauses();
        cycleCounts.setPauses(++count);
        currCounts[3].setText(""+count);
        // All time count:
        count = sharedPreferences.getInt("onPause", 0);
        sharedPreferences.edit().putInt("onPause", ++count).apply();
        allTimeCounts[3].setText(""+count);
    }
    @Override
    protected void onStop(){
        super.onStop();
        int count = cycleCounts.getStops();
        cycleCounts.setStops(++count);
        currCounts[4].setText(""+count);
        // All time count:
        count = sharedPreferences.getInt("onStop", 0);
        sharedPreferences.edit().putInt("onStop", ++count).apply();
        allTimeCounts[4].setText(""+count);
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        int count = cycleCounts.getRestarts();
        cycleCounts.setRestarts(++count);
        currCounts[5].setText(""+count);
        // All time count:
        count = sharedPreferences.getInt("onRestart", 0);
        sharedPreferences.edit().putInt("onRestart", ++count).apply();
        allTimeCounts[5].setText(""+count);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("test:destroyed");
        int count = cycleCounts.getDestroys();
        cycleCounts.setDestroys(++count);
        currCounts[6].setText(""+count);
        // All time count:
        count = sharedPreferences.getInt("onDestroy", 0);
        sharedPreferences.edit().putInt("onDestroy", ++count).apply();
        allTimeCounts[6].setText(""+count);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup SharedPreferences
        sharedPreferences = getSharedPreferences("act_cycle_counts", MODE_PRIVATE);

        // Count current onCreate call
        SharedPreferences.Editor editor = sharedPreferences.edit();
        cycleCounts = new LifecycleCounts();// counts for current run
        cycleCounts.setCreates(1);
        int count = sharedPreferences.getInt("onCreate", 0);
        editor.putInt("onCreate", ++count).apply();

        // Setup counter display
        currCounts = new TextView[7];
        allTimeCounts = new TextView[7];
        events = new String[]{"onCreate", "onStart","onResume","onPause","onStop","onRestart","onDestroy"};
        grid = (GridLayout)findViewById(R.id.grid);
        grid.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        grid.setColumnCount(3);
        grid.setRowCount(8);

        // Create Top/Title Row of GridLayout:
        TextView tempName = new TextView(this);
        tempName.setText("Lifecycle Events");
        tempName.setTextSize(16);
        GridLayout.LayoutParams param =new GridLayout.LayoutParams();
        param.height = GridLayout.LayoutParams.MATCH_PARENT;
        param.width = GridLayout.LayoutParams.MATCH_PARENT;
        param.setMargins(5,5,5,5);
        param.rowSpec = GridLayout.spec(0, 1);
        param.columnSpec = GridLayout.spec(0, 1);
        tempName.setLayoutParams(param);
        grid.addView(tempName);

        TextView tempCount = new TextView(this);
        tempCount.setText("Current Run");
        tempCount.setTextSize(16);
        GridLayout.LayoutParams paramCount =new GridLayout.LayoutParams();
        paramCount.height = GridLayout.LayoutParams.MATCH_PARENT;
        paramCount.width = GridLayout.LayoutParams.MATCH_PARENT;
        paramCount.setMargins(5,5,5,5);
        paramCount.rowSpec = GridLayout.spec(0,1);
        paramCount.columnSpec = GridLayout.spec(1, 1);
        tempCount.setLayoutParams(paramCount);
        grid.addView(tempCount);

        TextView allTimeCount = new TextView(this);
        allTimeCount.setText("All Time");
        allTimeCount.setTextSize(16);
        GridLayout.LayoutParams paramCount2 =new GridLayout.LayoutParams();
        paramCount2.height = GridLayout.LayoutParams.MATCH_PARENT;
        paramCount2.width = GridLayout.LayoutParams.MATCH_PARENT;
        paramCount2.setMargins(5,5,5,5);
        paramCount2.rowSpec = GridLayout.spec(0,1);
        paramCount2.columnSpec = GridLayout.spec(2, 1);
        allTimeCount.setLayoutParams(paramCount2);
        grid.addView(allTimeCount);

        // Display all counters in GridLayout
        for(int i = 0; i < events.length; i++){
            tempName = new TextView(this);
            tempName.setText(events[i]);
            tempName.setTextSize(16);
            // Setup Paramss
            param =new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.MATCH_PARENT;
            param.width = GridLayout.LayoutParams.MATCH_PARENT;
            param.setMargins(5,5,5,5);
            param.rowSpec = GridLayout.spec(i + 1, 1);
            param.columnSpec = GridLayout.spec(0, 1);
            tempName.setLayoutParams(param);
            grid.addView(tempName);

            currCounts[i] = new TextView(this);
            String tCount = cycleCounts.getSpecCount(i)+"";
            currCounts[i].setId(2000 + i);
            currCounts[i].setText(tCount);
            currCounts[i].setTextSize(16);
            paramCount =new GridLayout.LayoutParams();
            paramCount.height = GridLayout.LayoutParams.MATCH_PARENT;
            paramCount.width = GridLayout.LayoutParams.MATCH_PARENT;
            paramCount.setMargins(5,5,5,5);
            paramCount.rowSpec = GridLayout.spec(i+1,1);
            paramCount.columnSpec = GridLayout.spec(1, 1);
            currCounts[i].setLayoutParams(paramCount);
            grid.addView(currCounts[i]);

            allTimeCounts[i] = new TextView(this);
            String teCount = sharedPreferences.getInt(events[i], 0)+"";
            allTimeCounts[i].setId(3000 + i);
            allTimeCounts[i].setText(teCount);
            allTimeCounts[i].setTextSize(16);
            paramCount2 =new GridLayout.LayoutParams();
            paramCount2.height = GridLayout.LayoutParams.MATCH_PARENT;
            paramCount2.width = GridLayout.LayoutParams.MATCH_PARENT;
            paramCount2.setMargins(5,5,5,5);
            paramCount2.rowSpec = GridLayout.spec(i+1,1);
            paramCount2.columnSpec = GridLayout.spec(2, 1);
            allTimeCounts[i].setLayoutParams(paramCount2);
            grid.addView(allTimeCounts[i]);
        }
    }

    public void resetCounts(View view) {
        cycleCounts = new LifecycleCounts(); // reset counts for current run
        sharedPreferences.edit().clear().apply(); // reset all-time counts
        // Reset displays:
        for(TextView tv : allTimeCounts){
            tv.setText("0");
        }
        for(TextView tv : currCounts){
            tv.setText("0");
        }
    }
}