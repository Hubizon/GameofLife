package com.example.hubert.gameoflife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hubert.gameoflife.House.Lodging;
import com.example.hubert.gameoflife.House.Transport;
import com.example.hubert.gameoflife.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import static com.example.hubert.gameoflife.Utils.Dialogs.showAlertDialog;
import static com.example.hubert.gameoflife.Utils.Dialogs.showDialogWithChoose;

public class UpdateValues {

    private static JSONArray jsonArray;
    private static Gson gson = new Gson();
    private static JSONObject json = null;

    private static SharedPreferences sharedPreferences;
    private static Context contextThis;

    public static void updateSharedPreferences(Context context, SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        sharedPreferences = sharedPref;
        contextThis = context;

        editor.putInt(context.getResources().getString(R.string.saved_hungry_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_energy_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));

        editor.putInt(context.getResources().getString(R.string.saved_happiness_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness)) - 5));
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) <= 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth)) - 25));


        editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));
        if (sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours) >= 23) {
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), 0);
            editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
            editor.putInt(context.getResources().getString(R.string.saved_day_week_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));

            if(sharedPref.getInt(context.getResources().getString(R.string.saved_day_week_key), SharedPreferencesDefaultValues.DefaultDayWeek) == 1)
                getPayment(context);

            String jsonString = sharedPref.getString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
            Lodging lodging = gson.fromJson(jsonString, Lodging.class);

            if(lodging != null)
                if("rent".equals(lodging.getType()))
                    if(lodging.getRentTime() <= sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_key), 0))
                    {
                        if(sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) <= 18)
                            editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                        else
                            editor.putString(context.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodgingAfter18);

                        editor.putInt(context.getResources().getString(R.string.saved_renting_time_key), 0);
                        showAlertDialog(context,"Your time of rental has ended", "You came to the street!");
                    }
                    else
                        editor.putInt(context.getResources().getString(R.string.saved_renting_time_key), (sharedPref.getInt(context.getResources().getString(R.string.saved_renting_time_key), 0) + 1));

            try {
                jsonArray = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                for (int i = 0; i <= (jsonArray.length() - 1); i++)
                {
                    json  = (JSONObject) jsonArray.get(i);
                    json.put("IsTodaysHomeworkDone", false);
                    jsonArray.put(i, json);
                }
            }
            catch (JSONException e)
            { }
            editor.putString(context.getResources().getString(R.string.saved_subjects_list_key), jsonArray.toString());

            if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays) >= 31) {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays);
                if (sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths) >= 12) {
                    editor.putInt(context.getResources().getString(R.string.saved_date_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_years_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears)) + 1));
                    editor.putInt(context.getResources().getString(R.string.saved_age_days_key), 0);
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths);
                } else
                    editor.putInt(context.getResources().getString(R.string.saved_date_months_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths)) + 1));
            } else
            {
                editor.putInt(context.getResources().getString(R.string.saved_date_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_date_days_key), SharedPreferencesDefaultValues.DefaultDateDays)) + 1));
                editor.putInt(context.getResources().getString(R.string.saved_age_days_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_age_days_key), SharedPreferencesDefaultValues.DefaultAgeDays)) + 1));
            }
        } else
            editor.putInt(context.getResources().getString(R.string.saved_time_hours_key), ((sharedPref.getInt(context.getResources().getString(R.string.saved_time_hours_key), SharedPreferencesDefaultValues.DefaultTimeHours)) + 1));

        if (sharedPref.getInt(context.getResources().getString(R.string.saved_age_years_key), SharedPreferencesDefaultValues.DefaultAgeYears) <= 18) {
            try {
                jsonArray = new JSONArray(sharedPref.getString(context.getResources().getString(R.string.saved_subjects_list_key), SharedPreferencesDefaultValues.DefaultSubjectsList));
                for (int i = 0; i < jsonArray.length(); i++) {
                    json = (JSONObject) jsonArray.get(i);
                    json.put("toAnotherMark", (json.getInt("toAnotherMark") + 1));
                    jsonArray.put(i, json);
                }

            } catch (JSONException e) { }
        }

        randomEvents(context);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_health_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_hungry_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_hungry_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_energy_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_energy_key), 1000);

        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) < 0)
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), 0);
        if(sharedPref.getInt(context.getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 1000)
            editor.putInt(context.getResources().getString(R.string.saved_happiness_key), 1000);

        editor.apply();
    }

    private static void getPayment(final Context context)//from e.g. "make a game", "write a book"
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try
        {
            //TODO co jakis czas tu sie wywala
            JSONArray jsonArrayGames = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_games_key), SharedPreferencesDefaultValues.DefaultGamesList));
            JSONArray jsonArrayDrawings = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_books_key), SharedPreferencesDefaultValues.DefaultBooksList));
            JSONArray jsonArrayBooks = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_drawings_key), SharedPreferencesDefaultValues.DefaultDrawingsList));
            JSONArray jsonArrayMovies = new JSONArray(sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_movies_key), SharedPreferencesDefaultValues.DefaultMoviesList));

            for(int x = 0; x < 5; x++)
            {
                JSONArray jsonArrayThing = null;
                switch (x)
                {
                    case 1:
                        jsonArrayThing = jsonArrayGames;
                        break;

                    case 2:
                        jsonArrayThing = jsonArrayDrawings;
                        break;

                    case 3:
                        jsonArrayThing = jsonArrayBooks;
                        break;

                    case 4:
                        jsonArrayThing = jsonArrayMovies;
                        break;
                }

                if(jsonArrayThing != null)
                {
                    if(jsonArrayThing.length() > 0)
                    {
                        int moneyFromGames = 0;
                        for(int i = 0; i < jsonArrayThing.length(); i++)
                        {
                            JSONArray jsonArray = jsonArrayThing.getJSONArray(i);
                            Random random = new Random();
                            int moneyFromGame = (jsonArray.getInt(0) * random.nextInt(5) + 10);
                            if(jsonArray.getBoolean(1))
                                moneyFromGame *= 2.5;

                            if(jsonArray.getInt(2) >= sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_years_key), SharedPreferencesDefaultValues.DefaultDateYears))
                            {
                                if((jsonArray.getInt(3) + 10) > 12)
                                {
                                    if((jsonArray.getInt(3) + 10) - 12 == sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths))
                                    {
                                        jsonArray.remove(i);
                                        editor.putString(contextThis.getResources().getString(R.string.saved_games_key), jsonArray.toString());
                                        //TODO: sprawdzić to (czy automatycznie się przesuwa, czy będzie trzeba to jakoś zrobić if(!jsonArray.getInt[i].isNull))
                                    }
                                }
                                else
                                {
                                    if((jsonArray.getInt(3) + 10) == sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_date_months_key), SharedPreferencesDefaultValues.DefaultDateMonths))
                                    {
                                        jsonArray.remove(i);
                                        editor.putString(contextThis.getResources().getString(R.string.saved_games_key), jsonArray.toString());
                                        //TODO: sprawdzić to (czy automatycznie się przesuwa, czy będzie trzeba to jakoś zrobić if(!jsonArray.getInt[i].isNull))
                                    }
                                }
                            }

                            moneyFromGames += moneyFromGame;
                        }

                        editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), sharedPreferences.getInt(context.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + moneyFromGames);
                        showAlertDialog(context, "You got payment from your created games!", "You got " + moneyFromGames + "$");
                    }
                }
            }
        }
        catch (JSONException e)
        { }

        editor.apply();
    }

    private static void randomEvents(Context context)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Random rnd = new Random();
        //TODO: zrobic, zeby nie zadzialalo jak ktos wylaczy aplikacje podczas dialogu

        switch (rnd.nextInt(100000))
        {
            case 1:
                //TODO: Michal!!! zastopuj timer tu xd
                drawGoodEvent(context);
                break;

            case 2:
                //TODO Michal!!! zastopuj timer tu xd
                drawBadEvent(context);
                break;
        }
        editor.apply();
    }

    private static void drawGoodEvent(Context context)
    {
        Random rnd = new Random();
        final int karmaPoints = sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        if(rnd.nextInt(100) < karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(7))
            {
                case 1:
                    showDialogWithChoose(sharedPreferences, context,"Some rich man won the lottery and want to give you 25000$!", "Do you want to accept it?", 2);
                    break;

                case 2: case 3: case 4: case 5: case 6:
                    int rndFoundMoney = rnd.nextInt(15) * 100;
                    showAlertDialog(context,"You found " + rndFoundMoney, "");
                    editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + rndFoundMoney));
                    break;
            }
            editor.apply();
        }
        else
            drawBadEvent(context);
    }

    private static void drawBadEvent(Context context)
    {
        Random rnd = new Random();
        String json;
        final int karmaPoints = sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_karma_points_key), SharedPreferencesDefaultValues.DefaultKarmaPoints);
        if(rnd.nextInt(100) > 100 - karmaPoints)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            switch (rnd.nextInt(7))
            {
                case 1: case 2:
                json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                Lodging lodging = gson.fromJson(json, Lodging.class);
                if("Rent a Cheap Flat in Dangerous Neighborhood For a Month".equals(lodging.getName()))
                    showDialogWithChoose(sharedPreferences, context,"You were attacked by criminalist!", "Do you want to go to the hospital for 15 000 or order the grave?", 1);
                break;

                case 3:
                    json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                    gson.fromJson(json, Lodging.class);
                    gson.newBuilder().setLenient().create();

                    Lodging lodging2 = gson.fromJson(json, Lodging.class);
                    if(lodging2 != null )
                    {
                        if(lodging2.getPrice() != 0 && "buy".equals(lodging2.getType()))
                        {
                            showAlertDialog(context, "Your house was burned!", ("You got recompensation" + (lodging2.getPrice() / 2)));
                            editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (lodging2.getPrice() / 2)));
                            editor.putString(contextThis.getResources().getString(R.string.saved_my_lodging_key), null);
                        }
                    }
                    break;

                case 4:
                    json = sharedPreferences.getString(contextThis.getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                    gson.fromJson(json, Transport.class);
                    gson.newBuilder().setLenient().create();

                    Transport transport = gson.fromJson(json, Transport.class);
                    if(transport != null)
                    {
                        if(transport.getPrice() != 0)
                        {
                            showAlertDialog(context, ("Somebody stole your " + transport.getName()), ("You got recompensation" + (transport.getPrice() / 2)));
                            editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) + (transport.getPrice() / 2)));
                            editor.putString(contextThis.getResources().getString(R.string.saved_my_transport_key), null);
                        }
                    }
                    break;



                case 5:
                    showAlertDialog(context,"You've been robbed!", "Somebody stole " + (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$");
                    editor.putInt(contextThis.getResources().getString(R.string.saved_character_money_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) / 2));
                    break;

                case 6:
                    if(sharedPreferences.getBoolean(contextThis.getResources().getString(R.string.saved_have_safe_key), SharedPreferencesDefaultValues.DefaultHaveSafe) )
                    {
                        if(sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) > 0)
                        {
                            showAlertDialog(context, "You've been robbed!", "Somebody stole " + (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoney) / 2) + "$ from your safe!");
                            editor.putInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), (sharedPreferences.getInt(contextThis.getResources().getString(R.string.saved_money_in_safe_key), SharedPreferencesDefaultValues.DefaultMoneyInSafe) / 2));
                        }
                    }
                    break;
            }
            editor.apply();
        }
        else
            drawGoodEvent(context);
    }
}