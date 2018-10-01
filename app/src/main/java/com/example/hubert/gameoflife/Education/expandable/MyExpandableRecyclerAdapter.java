package com.example.hubert.gameoflife.Education.expandable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hubert.gameoflife.Education.EduFragment;
import com.example.hubert.gameoflife.Education.LearnInHomeActivity;
import com.example.hubert.gameoflife.R;
import com.example.hubert.gameoflife.Utils.MyDialogFragment;
import com.example.hubert.gameoflife.Utils.SharedPreferencesDefaultValues;
import com.example.hubert.gameoflife.Work.FindJobActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class MyExpandableRecyclerAdapter extends ExpandableRecyclerViewAdapter<MyParentViewHolder,MyChildViewHolder> {

    private static final String EDU_DIALOG_TAG = "edu_dialog_tag";

    private static final int INDEX_SCHOOL = 0;
    private static final int INDEX_WORK = 1;
    private static final int INDEX_CRIMINAL = 2;

    private static final int go_to_school_index = 0;
    private static final int learn_hard_index = 1;
    private static final int hang_around_index = 2;
    private static final int learn_at_home_index = 3;
    private static final int give_up_school_index = 4;

    private static final int get_new_friends_index = 0;
    private static final int steal_stuff_index = 1;
    private static final int sell_drugs_index = 2;
    private static final int threat_teacher_index = 3;

    private static final int start_working_index = 0;
    private static final int work_hard_index = 1;
    private static final int give_up_work_index = 2;

    Context mContext;

    public MyExpandableRecyclerAdapter(List<ParentList> groups, Context context) {
        super(groups);
        mContext = context;
    }

    @Override
    public MyParentViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_parent_item, parent, false);
        return new MyParentViewHolder(view);
    }

    @Override
    public MyChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_child_item, parent, false);
        return new MyChildViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(MyChildViewHolder holder, final int flatPosition, final ExpandableGroup group, final int childIndex) {

        final ChildList childItem = ((ParentList) group).getItems().get(childIndex);
        holder.onBind(childItem.getTitle());
        final String titleChild = group.getTitle();
        holder.listChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(mContext, titleChild, Toast.LENGTH_SHORT);
                toast.show();

                SharedPreferences sharedPref = mContext.getSharedPreferences(mContext.getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                Intent intent = null;
                DialogFragment newDialog = null;
                Random r = new Random();
                int view_id = view.getId();
                if (group.getTitle().equals(EduFragment.TITLE_SCHOOL)) {
                    switch (childIndex) {
                        case go_to_school_index:
                            newDialog = MyDialogFragment.newInstance(view_id);
                            break;
                        case learn_hard_index:
                            intent = new Intent(mContext.getApplicationContext(), LearnInHomeActivity.class);
                            break;
                         case hang_around_index:
                             newDialog = MyDialogFragment.newInstance(view_id);
                            break;
                        case learn_at_home_index:
                            intent = new Intent(mContext.getApplicationContext(), LearnInHomeActivity.class);
                            break;
                        case give_up_school_index:
                            editor.putBoolean(mContext.getString(R.string.saved_is_in_school_now_key), false);
                            intent = new Intent(mContext.getApplicationContext(), FindJobActivity.class);
                            break;
                    }
                } else if (group.getTitle().equals(EduFragment.TITLE_CRIMINAL)) {
                    switch (childIndex) {
                        case get_new_friends_index:
                            break;
                        case steal_stuff_index:
                            if(r.nextInt(25) == 1) {
                                editor.putInt(mContext.getString(R.string.saved_character_money_key), 0);
                                Toast.makeText(mContext.getApplicationContext(), ("You got busted! You lost all your money."), Toast.LENGTH_LONG).show();
                                if(r.nextInt(25) == 1)
                                    if(sharedPref.getBoolean(mContext.getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe))
                                    {
                                        editor.putInt(mContext.getString(R.string.saved_money_in_safe_key), 0);
                                        Toast.makeText(mContext.getApplicationContext(), ("Policeman found your safe! You lost all your money in safe."), Toast.LENGTH_LONG).show();
                                    }
                            }
                            else
                            {
                                editor.putInt(mContext.getString(R.string.saved_character_money_key), ((sharedPref.getInt(mContext.getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + 50));
                                Toast.makeText(mContext.getApplicationContext(), ("You got 50 money!"), Toast.LENGTH_LONG).show();
                            }
                            break;
                        case sell_drugs_index:
                            newDialog = MyDialogFragment.newInstance(view_id);
                            break;
                        case threat_teacher_index:
                            if(r.nextInt(5) == 1)
                            {
                                try {
                                    JSONArray jsonArray = new JSONArray(sharedPref.getString(mContext.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                                    JSONObject jsonObject = (JSONObject)(jsonArray.get(jsonArray.length() - 1));
                                    if(jsonObject.getInt("subjectMark") <= 1)
                                    {
                                        Toast.makeText(mContext.getApplicationContext(), ("Teacher reported this and you were expelled from school! You can not come back untill" + (sharedPref.getInt(mContext.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2) + "year"), Toast.LENGTH_LONG).show();
                                        editor.putBoolean(mContext.getResources().getString(R.string.saved_can_go_to_school_key), false);
                                        editor.putInt(mContext.getResources().getString(R.string.saved_year_when_can_go_to_school_key), (sharedPref.getInt(mContext.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) + 2));
                                    }
                                    else
                                    {
                                        Toast.makeText(mContext.getApplicationContext(), ("Teacher reported this and you got 1 from Behavior! Next time you will be expelled from school."), Toast.LENGTH_LONG).show();
                                        jsonObject.put("subjectMark", 1);
                                    }
                                    jsonArray.put(jsonArray.length() - 1, jsonObject);
                                    editor.putString(mContext.getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());
                                }
                                catch (JSONException e)
                                { }
                            }
                            else
                            {
                                try
                                {
                                    JSONArray jsonArray = new JSONArray(sharedPref.getString(mContext.getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                                    int rnd = r.nextInt(jsonArray.length());
                                    JSONObject jsonObject = (JSONObject)jsonArray.get(rnd);
                                    jsonObject.put("subjectMark", (jsonObject.getInt("subjectMark") + 1 ));
                                    if(jsonObject.getInt("subjectMark") >= 6)
                                        Toast.makeText(mContext.getApplicationContext(), ("You already have 6 from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                                    else
                                        Toast.makeText(mContext.getApplicationContext(), ("You have now " + jsonObject.get("subjectMark") + " from " + jsonObject.getString("subjectName")), Toast.LENGTH_LONG).show();
                                    jsonArray.put(rnd, jsonObject);
                                    editor.putString(mContext.getString(R.string.saved_subjects_list_key), jsonArray.toString());
                                }
                                catch (JSONException e)
                                { }
                            }
                            break;
                    }
                } else if (group.getTitle().equals(EduFragment.TITLE_WORK)) {
                    switch (childIndex) {
                        case start_working_index:
                            newDialog = MyDialogFragment.newInstance(view_id);
                            break;
                        case work_hard_index:
                            newDialog = MyDialogFragment.newInstance(view_id);
                            break;
                        case give_up_work_index:
                            editor.putString(mContext.getString(R.string.saved_my_job_key), null);

                            Intent intentWork = new Intent(mContext.getApplicationContext(), FindJobActivity.class);
                            mContext.startActivity(intentWork);
                            break;
                    }
                }

                if (intent != null) mContext.startActivity(intent);
                    else if (newDialog != null) newDialog.show(((AppCompatActivity) mContext).getSupportFragmentManager(), EDU_DIALOG_TAG);

                editor.apply();
            }

        });

    }

    @Override
    public void onBindGroupViewHolder(MyParentViewHolder holder, int flatPosition, final ExpandableGroup group) {
        holder.setParentTitle(group);
    }

}