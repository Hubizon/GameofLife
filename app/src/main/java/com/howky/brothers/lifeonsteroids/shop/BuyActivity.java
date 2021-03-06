package com.howky.brothers.lifeonsteroids.shop;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.howky.brothers.lifeonsteroids.MainActivity;
import com.howky.brothers.lifeonsteroids.MyApplication;
import com.howky.brothers.lifeonsteroids.R;
import com.howky.brothers.lifeonsteroids.house.Fun;
import com.howky.brothers.lifeonsteroids.house.Lodging;
import com.howky.brothers.lifeonsteroids.utils.Dialogs;
import com.howky.brothers.lifeonsteroids.utils.SharedPreferencesDefaultValues;
import com.howky.brothers.lifeonsteroids.house.Transport;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import static com.howky.brothers.lifeonsteroids.MyApplication.userSharedPref;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.foodList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.funList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.lodgingList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.lotteryList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.medicinesList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.transportList;
import static com.howky.brothers.lifeonsteroids.utils.Arrays.weaponList;

public class BuyActivity extends AppCompatActivity implements RecyclerViewShopBuyAdapter.ItemClickListener, Dialogs.OnResumeDialogInteractionListener {

    private static final int PAGE_NUMBER = 2;

    private int id;
    private View view;


    private Handler mHandler;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
                switch (id)
                {
                    case R.id.cardview_food:
                        itemBuyProgress.setProgress(MyApplication.userSharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
                        break;

                    case R.id.cardview_medicines:
                        itemBuyProgress.setProgress(MyApplication.userSharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
                        break;

                    case R.id.cardview_fun:
                        itemBuyProgress.setProgress(MyApplication.userSharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
                        break;
                    default:
                        break;
                }

            mHandler.postDelayed(mRunnable, 1000);
            }
    };

    private ProgressBar itemBuyProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        setTitle(R.string.title_shop);
        view = new View(this);

        mHandler = new Handler();
        itemBuyProgress = findViewById(R.id.progressBar_item_shop_buy);

        ArrayList<String> itemsNames = new ArrayList<>();
        ArrayList<String> itemsPrices = new ArrayList<>();

        SharedPreferences sharedPref = userSharedPref;
        ((TextView)(findViewById(R.id.money_buy))).setText(getString(R.string.money_formatted, getString(R.string.currency),
                sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));

        id = getIntent().getIntExtra(getResources().getString(R.string.send_shop_click_id), R.id.cardview_food);
        switch (id)
        {
            case R.id.cardview_food:
                for (Food food : foodList) {
                    itemsNames.add(food.getName());
                    itemsPrices.add(food.getPrice() + getResources().getString(R.string.currency));
                }
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.hungry));
                setTitle(getResources().getString(R.string.buy_food));
                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
                break;

            case R.id.cardview_medicines:
                for (Medicines medicines : medicinesList) {
                    itemsNames.add(medicines.getName());
                    itemsPrices.add(medicines.getPrice() + getResources().getString(R.string.currency));
                }
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.health));
                setTitle(getResources().getString(R.string.buy_medicines));
                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
                break;

            case R.id.cardview_fun:
                for (Fun fun : funList) {
                    itemsNames.add(fun.getName());
                    itemsPrices.add(fun.getPrice() + getResources().getString(R.string.currency));
                }
                ((TextView)(findViewById(R.id.itemToBuy_shop_buy))).setText(getResources().getString(R.string.happiness));
                setTitle(getResources().getString(R.string.buy_fun_items));
                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
                break;

            case R.id.buyLotteries:
                for (Lottery lottery : lotteryList) {
                    itemsNames.add(lottery.getName());
                    itemsPrices.add(lottery.getPrice() + getResources().getString(R.string.currency));
                }
                setTitle(getResources().getString(R.string.buy_lotteries));
                itemBuyProgress.setVisibility(View.GONE);
                break;

            case R.id.cardview_blackmarket:
                JSONArray jsonArray;
                JSONObject jsonObject;
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));
                    for(int i = 0; i < jsonArray.length(); i++)
                    {
                        jsonObject = jsonArray.getJSONObject(i);
                        if(!jsonObject.getBoolean("isBought"))
                        {
                            itemsNames.add(weaponList[i].getName());
                            itemsPrices.add(weaponList[i].getPrice() + getResources().getString(R.string.currency));
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setTitle(getResources().getString(R.string.black_market));
                itemBuyProgress.setVisibility(View.GONE);
                break;

            case R.id.cardview_house:
                for (Lodging lodging : lodgingList) {
                    itemsNames.add(lodging.getName());
                    itemsPrices.add((lodging.getPrice() * 25) + getResources().getString(R.string.currency));
                }
                setTitle(getResources().getString(R.string.buy_houses));
                itemBuyProgress.setVisibility(View.GONE);
                //lodgingObjects = lodgingList;
                break;

            case R.id.cardview_transport:
                for (Transport transport : transportList) {
                    itemsNames.add(transport.getName());
                    itemsPrices.add((transport.getPrice() * 25) + getResources().getString(R.string.currency));
                }
                setTitle(getResources().getString(R.string.buy_transport));
                itemBuyProgress.setVisibility(View.GONE);
                break;

            default:
                break;
        }

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewShopBuy);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewShopBuyAdapter adapter = new RecyclerViewShopBuyAdapter(this, itemsNames, itemsPrices);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {

        SharedPreferences sharedPref = userSharedPref;
        SharedPreferences.Editor editor = sharedPref.edit();
        Gson gson = new Gson();
        String json;

        switch (id)
        {
            case R.id.cardview_food:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= foodList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - foodList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_hungry_key), (sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) + foodList[position].getGivenFood()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + foodList[position].getGivenHealth()));
                    editor.putInt(getString(R.string.saved_energy_key), (sharedPref.getInt(getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) + foodList[position].getGivenEnergy()));
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), 750) + foodList[position].getGivenHappiness()));
                    editor.apply();
                }
                else
                    Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry));
                ((TextView)(findViewById(R.id.money_buy))).setText(getString(R.string.money_formatted, getString(R.string.currency),
                        sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));
                break;

            case R.id.cardview_medicines:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= medicinesList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - medicinesList[position].getPrice()));
                    editor.putInt(getString(R.string.saved_health_key), (sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) + medicinesList[position].getGivenHealth()));
                }
                else
                    Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                editor.apply();
                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth));
                ((TextView)(findViewById(R.id.money_buy))).setText(getString(R.string.money_formatted, getString(R.string.currency),
                        sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));
                break;

            case R.id.cardview_fun:
                /*String computerName = null;
                String phoneName = null;
                String tvName = null;
                int computerPrice = 0;
                int phonePrice = 0;
                int tvPrice = 0;

                if(funComputer != null)
                {
                    computerName = funComputer.getName();
                    computerPrice = funComputer.getPrice();
                }
                if(funPhone != null)
                {
                    phoneName = funPhone.getName();
                    phonePrice = funPhone.getPrice();
                }
                if(funTv != null)
                {
                    tvName = funTv.getName();
                    tvPrice = funTv.getPrice();
                }*/
                String jsonComputer = sharedPref.getString(getResources().getString(R.string.saved_my_computer_key), SharedPreferencesDefaultValues.DefaultMyComputer);
                String jsonPhone = sharedPref.getString(getResources().getString(R.string.saved_my_phone_key), SharedPreferencesDefaultValues.DefaultMyPhone);
                String jsonTv = sharedPref.getString(getResources().getString(R.string.saved_my_tv_key), SharedPreferencesDefaultValues.DefaultMyTv);
                Fun funComputer = gson.fromJson(jsonComputer, Fun.class);
                Fun funPhone = gson.fromJson(jsonPhone, Fun.class);
                Fun funTv = gson.fromJson(jsonTv, Fun.class);

                if("Exit".equals(funList[position].getType()))
                    buyFun(position);

                else if("Computer".equals(funList[position].getType()) && funComputer == null)
                    buyFun(position);
                else if("Computer".equals(funList[position].getType()))
                    alertDialogSellItem(  funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                else if( funList[position].getType().equals("Phone") && funPhone == null)
                    buyFun(position);
                else if ("Phone".equals( funList[position].getType()))
                    alertDialogSellItem( funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                else if( funList[position].getType().equals("TV") && funTv == null)
                    buyFun(position);
                else if ("TV".equals( funList[position].getType()))
                    alertDialogSellItem(funList[position].getPrice(),  funList[position].getName(), "buy","fun", position);

                itemBuyProgress.setProgress(sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness));
                break;
            case R.id.buyLotteries:
                if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= lotteryList[position].getPrice())
                {
                    editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - lotteryList[position].getPrice()));

                    Random random = new Random();
                    if(random.nextInt( lotteryList[position].getChanceToWin()) == 1)
                    {
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) +  lotteryList[position].getPrize()));
                        Toast.makeText(view.getContext(), getResources().getString(R.string.won) + " " +  lotteryList[position].getPrize() + getResources().getString(R.string.in_lottery), Toast.LENGTH_LONG).show();
                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) +  lotteryList[position].getPrize()));
                    }
                    else
                        Toast.makeText(view.getContext(), getResources().getString(R.string.didnt_win_lottery), Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(view.getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                break;

            case R.id.cardview_blackmarket:
            {
                JSONArray jsonArray;
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonArray = new JSONArray(sharedPref.getString(getResources().getString(R.string.saved_weapons_list_key), SharedPreferencesDefaultValues.DefaultWeapons));
                    for(int s = 0; s < jsonArray.length(); s++)
                    {
                        jsonObject = jsonArray.getJSONObject(s);
                        if(((TextView)view.findViewById(R.id.shopBuyItemName)).getText().equals(jsonObject.getString("name")))
                            break;
                    }
                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= jsonObject.getInt("price"))
                    {
                        if (!jsonObject.getBoolean("isBought")) {
                            jsonObject.put("isBought", true);
                            jsonArray.put(position, jsonObject);
                            editor.putString(getResources().getString(R.string.saved_weapons_list_key), jsonArray.toString());
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) - jsonObject.getInt("price"));
                            Toast.makeText(this, getResources().getString(R.string.successful_bought)+ " " + jsonObject.getString("name") + "!", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(this, getResources().getString(R.string.already_had_it), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            }

            case R.id.cardview_house:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_lodging_key), SharedPreferencesDefaultValues.DefaultMyLodging);
                // zobaczyc czy dziala bez tego
                gson.fromJson(json, Lodging.class);
                gson.newBuilder().setLenient().create();

                double lodgingPriceDouble =  lodgingList[position].getPrice();
                if(getResources().getString(R.string.rent_for_a_month).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    lodgingPriceDouble = lodgingPriceDouble * 3.5;
                else if(getResources().getString(R.string.buy).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    lodgingPriceDouble = lodgingPriceDouble * 25.0;
                int lodgingPrice = (int)Math.round(lodgingPriceDouble);

                // showDialogWithChoose(sharedPref, view.getContext(), "Buying lodging", "Are you sure you want to buy ", 6);
                alertDialogSellItem(lodgingPrice, lodgingList[position].getName(), String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem()),"lodging", position);
                break;

            case R.id.cardview_transport:
                json = sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport);
                Transport transport = gson.fromJson(json, Transport.class);

                double transportPriceDouble =  transportList[position].getPrice();
                if(getResources().getString(R.string.rent_for_a_month).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 3.5;
                else if(getResources().getString(R.string.buy).equals(String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem())))
                    transportPriceDouble *= 25.0;
                int transportPrice = (int)Math.round(transportPriceDouble);

                alertDialogSellItem(transportPrice, transport.getName(), String.valueOf(((Spinner)view.findViewById(R.id.buy_method_spinner)).getSelectedItem()),"transport", position);
//
//                if(sharedPref.getString(getResources().getString(R.string.saved_my_transport_key), SharedPreferencesDefaultValues.DefaultMyTransport) == null || json.equals(SharedPreferencesDefaultValues.DefaultMyTransport))
//                {
//                    if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= transportPrice)
//                    {
//                        editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - transportPrice));
//                        json = gson.toJson( transportList[position]);
//                        editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
//                    }
//                    else
//                        Toast.makeText(view.getContext(), "Unfortunately, you don't have enough money to buy this thing.", Toast.LENGTH_LONG).show();
//                } else
//                    alertDialogSellItem( transportPrice,  transportList[position].getName(), "transport", position);
//                ((TextView)(findViewById(R.id.money_buy))).setText("$ " + sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney));
                break;

            default:
                break;
        }
        if(sharedPref.getInt(getResources().getString(R.string.saved_hungry_key), SharedPreferencesDefaultValues.DefaultHungry) > 1000)
            editor.putInt(getString(R.string.saved_hungry_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_health_key), SharedPreferencesDefaultValues.DefaultHealth) > 1000)
            editor.putInt(getString(R.string.saved_health_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_energy_key), SharedPreferencesDefaultValues.DefaultEnergy) > 1000)
            editor.putInt(getString(R.string.saved_energy_key), 1000);
        if(sharedPref.getInt(getResources().getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) > 1000)
            editor.putInt(getString(R.string.saved_happiness_key), 1000);

        editor.apply();

        ((TextView)(findViewById(R.id.money_buy))).setText(getString(R.string.money_formatted, getString(R.string.currency),
                sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));
    }

    private void alertDialogSellItem(/*String boughtItemName, final int boughtItemPrice,*/ final int itemPrice, String itemName, final String purchaseMethod, final String itemType, final int position)
    {
        final SharedPreferences sharedPref = userSharedPref;
        //final SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getResources().getString(R.string.buying) + " " + itemType)
                //.setIcon(R.drawable.ic_launcher)
                .setMessage(getResources().getString(R.string.sure_to) + " " + purchaseMethod + " " + itemName + " " + getResources().getString(R.string.for_string) + " " + itemPrice + getResources().getString(R.string.currency) + "?")
                .setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                         dialoginterface.cancel();
                     }})
                .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                      //  editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) + (boughtItemPrice / 2)));
                       // editor.apply();

                        if(sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >= itemPrice)
                        {
                            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - itemPrice));
                            Gson gson = new Gson();
                            String json;
                            String[] buyMethods = getResources().getStringArray(R.array.buy_method_array);
                            switch (itemType)
                            {
                                case "fun":
                                    buyFun(position);
                                    break;
                                case "lodging":
                                    if(purchaseMethod.equals(buyMethods[1]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_lodging_key), 7);
                                    else if(purchaseMethod.equals(buyMethods[2]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_lodging_key), 31);

                                    json = gson.toJson( lodgingList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_lodging_key), json);
                                    editor.apply();
                                    break;
                                case "transport":
                                    if(purchaseMethod.equals(buyMethods[1]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_transport_key), 7);
                                    else if(purchaseMethod.equals(buyMethods[2]))
                                        editor.putInt(getResources().getString(R.string.saved_renting_time_transport_key), 31);

                                    json = gson.toJson( transportList[position]);
                                    editor.putString(getResources().getString(R.string.saved_my_transport_key), json);
                                    break;
                            }
                        }
                        else
                        {
                            Toast.makeText(view.getContext(), getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
                          //  editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) - (boughtItemPrice / 2)));
                        }
                        editor.apply();

                        ((TextView)(findViewById(R.id.money_buy))).setText(getString(R.string.money_formatted, getString(R.string.currency),
                                sharedPref.getInt(getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)));
                    }
                }).show();
    }

    private void buyFun(int position)
    {
        SharedPreferences sharedPref = userSharedPref;
        //SharedPreferences sharedPref = getSharedPreferences(getResources().getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        if (sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney) >=  funList[position].getPrice())
        {
            editor.putInt(getResources().getString(R.string.saved_character_money_key), ((sharedPref.getInt(getResources().getString(R.string.saved_character_money_key), SharedPreferencesDefaultValues.DefaultMoney)) -  funList[position].getPrice()));
            Gson gson = new Gson();
            String json = gson.toJson( funList[position]);
            switch ( funList[position].getType()) {
                //  create enum with those types
                case "Exit":
                    editor.putInt(getString(R.string.saved_happiness_key), (sharedPref.getInt(getString(R.string.saved_happiness_key), SharedPreferencesDefaultValues.DefaultHappiness) +  funList[position].getGivenFun()));
                    break;
                case "TV":
                    editor.putString(getResources().getString(R.string.saved_my_tv_key), json);
                    break;
                case "Computer":
                    editor.putString(getResources().getString(R.string.saved_my_computer_key), json);
                    break;
                case "Phone":
                    editor.putString(getResources().getString(R.string.saved_my_phone_key), json);
            }
        } else
            Toast.makeText(this, getResources().getString(R.string.not_enough_money), Toast.LENGTH_LONG).show();
        editor.apply();
    }


    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.CurrentContext = this;
        mHandler.post(mRunnable);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(BuyActivity.this, MainActivity.class);
        intent.putExtra(MainActivity.INTENT_PAGE, PAGE_NUMBER);
        startActivity(intent);
        finish();
    }

    @Override
    public void onDialogResume(MenuItem item) {
        Log.e("TODO", "TODO");
    }
}
