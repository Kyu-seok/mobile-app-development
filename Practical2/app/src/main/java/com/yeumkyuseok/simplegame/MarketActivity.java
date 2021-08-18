package com.yeumkyuseok.simplegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MarketActivity extends AppCompatActivity {
    private static final String TAG = "MarketActivity";

    TextView textItemName;
    TextView textItemPrice;
    TextView textItemMass;
    TextView textListNum;
    TextView textMarketCash;
    TextView textMarketHealth;
    TextView textMarketMass;


    Button btnBuy;
    Button btnNext;
    Button btnPrev;
    Button btnLeave;
    Button btnMyEquipment;

    GameMap gameMap;
    Player player;
    boolean isMarket;
    int currNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        Intent intent = getIntent();
        gameMap = (GameMap) intent.getSerializableExtra("gameMap");
        player = (Player) intent.getSerializableExtra("player");


        textItemName = findViewById(R.id.textItemName);
        textItemPrice = findViewById(R.id.textItemPrice);
        textItemMass = findViewById(R.id.textItemMass);
        textListNum = findViewById(R.id.textListNum);
        textMarketCash = findViewById(R.id.textMarketCash);
        textMarketHealth = findViewById(R.id.textMarketHealth);
        textMarketMass = findViewById(R.id.textMarketMass);


        btnBuy = findViewById(R.id.btnBuy);
        btnNext = findViewById(R.id.btnNext);
        btnPrev = findViewById(R.id.btnPrev);
        btnLeave = findViewById(R.id.btnLeave);
        btnMyEquipment = findViewById(R.id.btnMyEquipment);

        updateAllDisplay();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCurrItem() != null) {
                    Item currItem = getCurrItem();
                    if (player.getCash() >= currItem.getValue()) {
                        player.setCash(player.getCash() - currItem.getValue());
                        if (getCurrItem() instanceof Equipment) {
                            player.addPlayerItem((Equipment) getCurrItem());
                            gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().remove(currNumber);
                            //  currNumber -= 1; MIGHT NEED TO ALTER currNumber VALUE AFTER PURCHASE
                        } else {
                            player.setHealth(player.getHealth() + ((Food) currItem).getHealth());
                            gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().remove(currNumber);
                        }
                        updateAllDisplay();
                    } else {
                        Toast.makeText(MarketActivity.this, "Not enough money", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MarketActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MarketActivity.this, MainActivity.class);
                intent.putExtra("gameMap", gameMap);
                intent.putExtra("player", player);

                Bundle bundle = new Bundle();
                bundle.putBoolean("hasValue", true);

                startActivity(intent, bundle);
            }
        });


        //  TODO: implement Prev & Next btn functionality

        //  TODO: implement My Equipment btn functionality
    }



    public void updateAllDisplay() {
        if (!gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().isEmpty()) {
            Item currItem = gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().get(currNumber);
            boolean isEquipment = (currItem instanceof Equipment);
            textItemName.setText(currItem.getDescription());
            textListNum.setText((currNumber + 1) + "/" + gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().size());
            textItemPrice.setText("Price: " + currItem.getValue());

            if (isEquipment) {
                textItemMass.setText("Mass: " + ((Equipment) gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().get(currNumber)).getMass());
            } else {
                textItemMass.setText("Health: " + ((Food) gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().get(currNumber)).getHealth());
            }

        } else {
            textItemName.setText("Empty");
            textListNum.setText("-/-");
            textItemPrice.setText("Price: -");
            textItemMass.setText("Mass: -");
        }

        textMarketCash.setText("Cash: " + player.getCash());
        DecimalFormat df3 = new DecimalFormat("#.##");
        textMarketHealth.setText("Health: " + df3.format(player.getHealth()));
        textMarketMass.setText("Equipment Mass: " + player.getEquipmentMass());
    }

    public Item getCurrItem() {

        if (gameMap.getArea(player.getColLocation(), player.getRowLocation()).checkIsEmpty()) {
            return null;
        } else {
            return gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().get(currNumber);
        }

    }
}