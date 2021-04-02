package com.example.androidfundamentals022_activity_lifecycle_and_state;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static int  REQ_SECOND_ACT = 2;

    final static String EXTRA_MAIN_ACT_TV1 = "MainActivity.extras.tVItem1.value";
    final static String EXTRA_MAIN_ACT_TV2 = "MainActivity.extras.tVItem2.value";
    final static String EXTRA_MAIN_ACT_TV3 = "MainActivity.extras.tVItem3.value";
    final static String EXTRA_MAIN_ACT_TV4 = "MainActivity.extras.tVItem4.value";
    final static String EXTRA_MAIN_ACT_TV5 = "MainActivity.extras.tVItem5.value";
    final static String EXTRA_MAIN_ACT_TV6 = "MainActivity.extras.tVItem6.value";
    final static String EXTRA_MAIN_ACT_TV7 = "MainActivity.extras.tVItem7.value";
    final static String EXTRA_MAIN_ACT_TV8 = "MainActivity.extras.tVItem8.value";
    final static String EXTRA_MAIN_ACT_TV9 = "MainActivity.extras.tVItem9.value";
    final static String EXTRA_MAIN_ACT_TV10 = "MainActivity.extras.tVItem10.value";


    List<TextView> lTVItems = new ArrayList<>(); // always create a instance of the list we want to populate first, in other way nullptr exception
    TextView tVItem1, tVItem2, tVItem3, tVItem4, tVItem5, tVItem6, tVItem7, tVItem8, tVItem9, tVItem10;
    EditText etLookForStore;
    List<String> lMainActExtraKeys = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tVItem1 = findViewById(R.id.item1);
        tVItem2 = findViewById(R.id.item2);
        tVItem3 = findViewById(R.id.item3);
        tVItem4 = findViewById(R.id.item4);
        tVItem5 = findViewById(R.id.item5);
        tVItem6 = findViewById(R.id.item6);
        tVItem7 = findViewById(R.id.item7);
        tVItem8 = findViewById(R.id.item8);
        tVItem9 = findViewById(R.id.item9);
        tVItem10 = findViewById(R.id.item10);

        lTVItems.add(tVItem1);
        lTVItems.add(tVItem2);
        lTVItems.add(tVItem3);
        lTVItems.add(tVItem4);
        lTVItems.add(tVItem5);
        lTVItems.add(tVItem6);
        lTVItems.add(tVItem7);
        lTVItems.add(tVItem8);
        lTVItems.add(tVItem9);
        lTVItems.add(tVItem10);

        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV1);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV2);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV3);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV4);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV5);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV6);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV7);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV8);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV9);
        lMainActExtraKeys.add(EXTRA_MAIN_ACT_TV10);

        // load after activity is new created and there were items already in the text views before
        if (savedInstanceState != null)
        {
            for (int i = 0; i < 10; i++)
            {
                String extraKey = lMainActExtraKeys.get(i);
                String itemValue = savedInstanceState.getString(extraKey);
                lTVItems.get(i).setText(itemValue);
            }
        }


    }

    // in this overridden function we get the results from intents that we started with startActivityForResult
    // the request code is the key for the activity we started and want to get results from
    // the result code ist to check if the intent has results was successful or canceled
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
        {
            if ( requestCode == REQ_SECOND_ACT && data != null)
            {
                String itemValue;
                itemValue = data.getStringExtra(SecondActivity.EXTRA_ITEM_VALUE);

                // if one of the textviews ist still free add the item to the list
                for ( int i = 0; i < 10; i++)
                {
                   if(lTVItems.get(i).getText().toString().isEmpty())
                   {
                       lTVItems.get(i).setText(itemValue);
                       break;
                   }
                }

            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i = 0; i < 10; i++)
        {
            String itemValue = lTVItems.get(i).getText().toString();

            if (!itemValue.isEmpty())
            {
                outState.putString(lMainActExtraKeys.get(i), itemValue);
            }
        }

        // to save the items when the phones rotation is changed
    }

    public void addItemFromSecondAct(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, REQ_SECOND_ACT);
    }

    // in this onClick method an implicit intent is called to check the location of an store in maps

    public void lookForStore(View view) {

        etLookForStore = findViewById(R.id.etLookForStore);
        String loc = etLookForStore.getText().toString();

        // to get geo data the string has to be parsed to the right uri with a geo search query
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        // now create the implicit intent
        // in an implicit intent an action and the data for this intent is specified
        //Intent.ACTION_* and Uri
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // resolve the intent and check to make sure the intent resolved successfully if so startActivity();
        // with the resolveActivity and getPackageManager it is tried to find a Activity on the phone that can handle the reques
        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }
        else
        {
            Log.d("ImplicitIntents", "Cant't handle this intent!");
            Toast.makeText(this, "Test", Toast.LENGTH_LONG).show();
        }

    }
}