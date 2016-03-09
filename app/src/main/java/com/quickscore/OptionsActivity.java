package com.quickscore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quickscore.scoring.ScoringActivity;

/**
 * Created by Softapt on 08/03/2016.
 */
public class OptionsActivity extends BaseActivity {
    private TextView watchScoring;
    private TextView startScoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        initializeViews();
        setListeners();
    }

    private void initializeViews() {
        startScoring = (TextView) findViewById(R.id.start_scoring);
        watchScoring = (TextView) findViewById(R.id.watch_score);
    }

    private void setListeners() {
        startScoring.setOnClickListener(optionClickListener);
        watchScoring.setOnClickListener(optionClickListener);
    }

    protected View.OnClickListener optionClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.start_scoring:
                    Intent startScoreIntent = new Intent(OptionsActivity.this, ScoringActivity.class);
                    startActivity(startScoreIntent);
                    break;
                case R.id.watch_score:
                    break;
            }
        }
    };
}
