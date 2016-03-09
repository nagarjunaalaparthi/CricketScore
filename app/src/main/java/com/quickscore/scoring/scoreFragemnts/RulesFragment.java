package com.quickscore.scoring.scoreFragemnts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickscore.R;
import com.quickscore.scoring.BaseScoringFragment;

/**
 * Created by Softapt on 08/03/2016.
 */
public class RulesFragment extends BaseScoringFragment {

    protected RelativeLayout rootView;
    protected RelativeLayout oversLayout;
    protected RelativeLayout teamLayout;
    protected RelativeLayout rulesLayout;
    protected EditText noOfOvers;
    private ImageView oversNext;
    private ImageView teamNext;
    private EditText team1;
    private EditText team2;
    private ImageView powerPlay, byes, legByes, lbw;
    private EditText powerPlayOvers;
    private TextView nextRules;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = (RelativeLayout) inflater.inflate(R.layout.fragment_rules, container, false);
            initializeViews();
            setListeners();
        }
        return rootView;
    }

    private void initializeViews() {
        oversLayout = (RelativeLayout) rootView.findViewById(R.id.overs_layout);
        teamLayout = (RelativeLayout) rootView.findViewById(R.id.team_layout);
        rulesLayout = (RelativeLayout) rootView.findViewById(R.id.rules_layout);
        noOfOvers = (EditText) rootView.findViewById(R.id.overs_edit_text);
        oversNext = (ImageView) rootView.findViewById(R.id.next_overs);
        team1 = (EditText) rootView.findViewById(R.id.team1);
        team2 = (EditText) rootView.findViewById(R.id.team2);
        powerPlayOvers = (EditText) rootView.findViewById(R.id.powerplayovers);
        teamNext = (ImageView) rootView.findViewById(R.id.next_teams);
        powerPlay = (ImageView) rootView.findViewById(R.id.powerplay);
        byes = (ImageView) rootView.findViewById(R.id.byes);
        legByes = (ImageView) rootView.findViewById(R.id.legByes);
        lbw = (ImageView) rootView.findViewById(R.id.lbw);
        nextRules = (TextView) rootView.findViewById(R.id.rules_next);
    }

    private void setListeners() {
        oversNext.setOnClickListener(clickListener);
        teamNext.setOnClickListener(clickListener);
        powerPlay.setOnClickListener(clickListener);
        byes.setOnClickListener(clickListener);
        legByes.setOnClickListener(clickListener);
        lbw.setOnClickListener(clickListener);
    }

    protected View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.next_overs:
                    String oversText = noOfOvers.getText().toString();
                    if(oversText.length() > 0){
                        String powerplayOvers = ((Integer.parseInt(oversText)*30)/100) + " OVERS";
                        powerPlayOvers.setText(powerplayOvers);
                        animateViews(oversLayout, teamLayout);
                    }else{
                        Toast.makeText(scoringActivity,"Please enter no of overs",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.next_teams:
                    String firstTeam = team1.getText().toString();
                    String secondTeam = team2.getText().toString();
                    if(firstTeam.length() > 0 && secondTeam.length() > 0) {
                        animateViews(teamLayout, rulesLayout);
                    }else {
                        Toast.makeText(scoringActivity,"Please enter the teams",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.powerplay:
                    if(powerPlay.isSelected()){
                        powerPlay.setSelected(false);
                        powerPlayOvers.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_bottom_to_top));
                        powerPlayOvers.setVisibility(View.INVISIBLE);
                    }else{
                        powerPlay.setSelected(true);
                        powerPlayOvers.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_top_to_bottom));
                        powerPlayOvers.setVisibility(View.VISIBLE);
                    }
                    break;
                case R.id.byes:
                    if(byes.isSelected()){
                        byes.setSelected(false);
                    }else{
                        byes.setSelected(true);
                    }
                    break;
                case R.id.legByes:
                    if(legByes.isSelected()){
                        legByes.setSelected(false);
                    }else{
                        legByes.setSelected(true);
                    }
                    break;
                case R.id.lbw:
                    if(lbw.isSelected()){
                        lbw.setSelected(false);
                    }else{
                        lbw.setSelected(true);
                    }
                    break;
            }
        }
    };

    public void animateViews(RelativeLayout viewInvisible, RelativeLayout viewVisible) {
        viewInvisible.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_left_to_right));
        viewInvisible.setVisibility(View.GONE);
        viewVisible.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_right_to_left));
        viewVisible.setVisibility(View.VISIBLE);
    }

    public void reverseAnimateViews(RelativeLayout viewInvisible, RelativeLayout viewVisible) {
        viewInvisible.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_rev_right_to_left));
        viewInvisible.setVisibility(View.GONE);
        viewVisible.startAnimation(AnimationUtils.loadAnimation(scoringActivity, R.anim.anim_rev_left_to_right));
        viewVisible.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onBackPressed() {
        if (oversLayout.getVisibility() == View.VISIBLE) {
            return true;
        } else {
            if (rulesLayout.getVisibility() == View.VISIBLE) {
                reverseAnimateViews(rulesLayout, teamLayout);
            } else if (teamLayout.getVisibility() == View.VISIBLE) {
                reverseAnimateViews(teamLayout, oversLayout);
            }
        }
        return true;
    }
}
