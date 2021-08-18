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

    TextView textMarketName;
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
    boolean isMarket = true;
    int currNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        Intent intent = getIntent();
        gameMap = (GameMap) intent.getSerializableExtra("gameMap");
        player = (Player) intent.getSerializableExtra("player");


        textMarketName = findViewById(R.id.textMarket);
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
                if (isMarket) {
                    if (getCurrItem() != null) {
                        Item currItem = getCurrItem();
                        if (player.getCash() >= currItem.getValue()) {
                            player.setCash(player.getCash() - currItem.getValue());
                            if (getCurrItem() instanceof Equipment) {
                                player.addPlayerItem((Equipment) getCurrItem());
                                gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().remove(currNumber);
                            } else {
                                player.setHealth(player.getHealth() + ((Food) currItem).getHealth());
                                gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().remove(currNumber);
                            }
                            if (currNumber != 0) {
                                currNumber -= 1;
                            }
                            updateAllDisplay();
                        } else {
                            Toast.makeText(MarketActivity.this, "Not enough money", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MarketActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    };
                } else {
                    if (!player.getEquipment().isEmpty()) {
                        Equipment currEquipment = player.getEquipment().get(currNumber);
                        player.setCash(player.getCash() + (currEquipment.getValue() / 2) );
                        gameMap.getArea(player.getColLocation(), player.getRowLocation()).addItems(currEquipment);
                        player.getEquipment().remove(currNumber);
                        if (currNumber != 0) {
                            currNumber--;
                        }
                        updateAllPlayerDisplay();
                    } else {
                        Toast.makeText(MarketActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    }
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Code below may cause error when there is no items on the market to display.
                if (isMarket) {
                    int itemsSize = gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().size();
                    if ((!gameMap.getArea(player.getColLocation(), player.getRowLocation()).checkIsEmpty()) && itemsSize > 1) {
                        if (currNumber < itemsSize - 1) {
                            currNumber++;
                            updateAllDisplay();
                        }
                    }
                } else {
                    if (player.getEquipment().size() > 1) {
                        if (currNumber < player.getEquipment().size() - 1) {
                            currNumber++;
                            updateAllPlayerDisplay();
                        }
                    }
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMarket) {
                    int itemsSize = gameMap.getArea(player.getColLocation(), player.getRowLocation()).getItems().size();
                    if ((!gameMap.getArea(player.getColLocation(), player.getRowLocation()).checkIsEmpty()) && itemsSize > 1) {
                        if (currNumber > 0) {
                            currNumber--;
                            updateAllDisplay();
                        }
                    }
                } else {
                    if (player.getEquipment().size() > 1) {
                        if (currNumber > 0) {
                            currNumber --;
                            updateAllPlayerDisplay();
                        }
                    }
                }
            }
        });

        btnMyEquipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMarket) {
                    isMarket = false;
                    updateAllPlayerDisplay();
                } else {
                    currNumber = 0;
                    isMarket = true;
                    updateAllDisplay();
                }
            }
        });



    }



    public void updateAllDisplay() {
        textMarketName.setText("Market");
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

        btnBuy.setText("Buy");
        btnMyEquipment.setText("My equipment");
        textMarketCash.setText("Cash: " + player.getCash());
        DecimalFormat df3 = new DecimalFormat("#.##");
        textMarketHealth.setText("Health: " + df3.format(player.getHealth()));
        textMarketMass.setText("Equipment Mass: " + player.getEquipmentMass());
    }

    public void updateAllPlayerDisplay() {
        textMarketName.setText("My Equipment");
        if (!player.getEquipment().isEmpty()) {
            Equipment currEquipment = player.getEquipment().get(currNumber);
            textItemName.setText(currEquipment.getDescription());
            textListNum.setText((currNumber + 1) + "/" + player.getEquipment().size());
            textItemPrice.setText("Price: " + (currEquipment.getValue()) );
            textItemMass.setText("Mass: " + currEquipment.getMass());

        } else {
            textItemName.setText("Empty");
            textListNum.setText("-/-");
            textItemPrice.setText("Price: -");
            textItemMass.setText("Mass: -");
        }

        btnBuy.setText("Sell");
        btnMyEquipment.setText("Market");
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