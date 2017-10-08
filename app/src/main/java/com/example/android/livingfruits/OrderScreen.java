package com.example.android.livingfruits;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class OrderScreen extends AppCompatActivity {

    double price = 0.0;
    int count125 = 0;
    int count400 = 0;
    int count600 = 0;

    boolean delivery = true;
    ImageView deliveryIcon;
    RadioButton deliveryButton;
    RadioButton pickupButton;

//------------------------------------
//      On Create
//------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // F.A.B.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.dollar_sign);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Place Order", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Declare Variables:
        final EditText text125 = (EditText) findViewById(R.id.count_125);
        final EditText text400 = (EditText) findViewById(R.id.count_400);
        final EditText text600 = (EditText) findViewById(R.id.count_600);
        deliveryIcon = (ImageView) findViewById(R.id.deliveryIcon);
        deliveryButton = (RadioButton) findViewById(R.id.deliveryButton);
        pickupButton = (RadioButton) findViewById(R.id.pickupButton);

        // 125 grams -- $ 4.00
        final View button_1 = findViewById(R.id.blueberry_125);
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price += 4.00;
                updatePrice();

                count125 += 1;
                updateCount(count125, text125);
            }
        });

        // 400 grams -- $12.00
        final View button_2 = findViewById(R.id.blueberry_400);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price += 12.00;
                updatePrice();

                count400 += 1;
                updateCount(count400, text400);
            }
        });

        // 600 grams -- $16.00
        final View button_3 = findViewById(R.id.blueberry_600);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                price += 16.00;
                updatePrice();

                count600 += 1;
                updateCount(count600, text600);
            }
        });

        // Clear
        final View clear_button = findViewById(R.id.clear_button);
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear Prices
                price = 0.00;
                updatePrice();

                // Clear Counts
                count125 = 0;
                count400 = 0;
                count600 = 0;
                updateCount(count125, text125);
                updateCount(count400, text400);
                updateCount(count600, text600);
            }
        });

        final ImageView deliveryIcon = (ImageView) findViewById(R.id.deliveryIcon);
        deliveryIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);

        // Delivery
        deliveryIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle Delivery
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updatePrice(){
        TextView price_Number = (TextView) findViewById(R.id.price_number);
        String price_string = String.format(Locale.ENGLISH, "$ %.2f", price);
        price_Number.setText(price_string);
    }

    public void updateCount(int count, EditText e){
        e.setText(String.valueOf(count));
    }

    public void onRadioButtonClicked(View view) {
        // Is the button checked now?
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.deliveryButton:
                if (checked)
                    deliveryIcon.setImageResource(R.drawable.delivery);
                    break;

            case R.id.pickupButton:
                if (checked)
                    deliveryIcon.setImageResource(R.drawable.pickup);
                    break;
        }
    }
}
