package com.howky.hubert.gameoflife.girlboyfriend;

import android.content.Context;
import android.content.SharedPreferences;

import com.howky.hubert.gameoflife.MainActivity;
import com.howky.hubert.gameoflife.R;
import com.howky.hubert.gameoflife.utils.SharedPreferencesDefaultValues;

public class Love  {
    private final String name;
   // private int age;

    public Love(String name)
    {
        this.name = name;
    }

    public String getName() { return name; }
  //  public int getAge() { return age; }

    public static void BreakUp(Context context)
    {
        SharedPreferences sharedPref = MainActivity.userSharedPref;
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getResources().getString(R.string.saved_love_key), null);
        editor.putInt(context.getResources().getString(R.string.saved_love_relations_key), SharedPreferencesDefaultValues.DefaultLoveRelations);
        editor.putInt(context.getResources().getString(R.string.saved_love_relationship_level_key), SharedPreferencesDefaultValues.DefaultLoveRelationshipLevel);
        editor.putBoolean(context.getResources().getString(R.string.saved_is_sad_key), true);
        editor.putInt(context.getResources().getString(R.string.saved_how_long_will_be_sad_key), 100);
        editor.apply();
    }
}