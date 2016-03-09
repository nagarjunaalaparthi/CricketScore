package com.quickscore.scoring;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Softapt on 08/03/2016.
 */
public class BaseScoringFragment extends Fragment {
    protected ScoringActivity scoringActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        scoringActivity = (ScoringActivity)getActivity();
    }

    public boolean onBackPressed(){
        return true;
    }
}
