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


public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";

    TextView textMap;
    TextView textGrid;
    TextView textCash;
    TextView textHealth;
    TextView textEquipmentMass;
    private GameMap gameMap = new GameMap();
    private Player player = new Player();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Log.d(TAG, "onCreate: Activity cycle onCreated Created!!!");

        try {
            Intent intent = getIntent();
            if (intent.hasExtra("gameMap")) {
                Log.d(TAG, "onCreate: INTENT HAS EXTRA gameMap!!!");
                this.gameMap = (GameMap) intent.getSerializableExtra("gameMap");
            }
            if (intent.hasExtra("player")) {
                this.player = (Player) intent.getSerializableExtra("player");
            }
            Log.d(TAG, "onCreate: SUCESSFULLY LOADED INTENT!!!!!");
        } catch (Exception e) {
            Log.d(TAG, "onCreate: NO BUNDLE");
        }
            //  Intent intent = getIntent();
            //  this.gameMap = (GameMap) intent.getSerializableExtra("gameMap");
            //  this.player = (Player) intent.getSerializableExtra("player");



        Button btnNorth = (Button) findViewById(R.id.btnNorth);
        Button btnSouth = (Button) findViewById(R.id.btnSouth);
        Button btnEast = (Button) findViewById(R.id.btnEast);
        Button btnWest = (Button) findViewById(R.id.btnWest);
        Button btnRestart = (Button) findViewById(R.id.btnRestart);
        Button btnOption = (Button) findViewById(R.id.btnOption);

        textMap = (TextView) findViewById(R.id.textMap);
        textGrid = (TextView) findViewById(R.id.textGrid);
        textCash = (TextView) findViewById(R.id.textCash);
        textHealth = (TextView) findViewById(R.id.textHealth);
        textEquipmentMass = (TextView) findViewById(R.id.textEquipmentMass);

        //  set initial textMap, textGrid, textCash, textHealth, textEquipmentMass
        displayNewMap();
        displayNewGrid();
        displayNewCash();
        displayNewHealth();
        displayNewEquipmentMass();


        View.OnClickListener controllerOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkLoseCondition() ) {
                    Toast.makeText(MainActivity.this, "LOSE!!!", Toast.LENGTH_SHORT).show();
                    MainActivity.this.getIntent().removeExtra("gameMap");
                    MainActivity.this.getIntent().removeExtra("player");
                    MainActivity.this.recreate();
                }
                switch (view.getId()) {
                    case R.id.btnNorth:
                        if (0 == player.getColLocation()) {
                            Toast.makeText(MainActivity.this, "Player is at MAX Column", Toast.LENGTH_SHORT).show();
                        } else {
                            player.setColLocation(player.getColLocation() - 1);
                            displayNewGrid();
                            displayNewMap();
                            playerMove();
                        }
                        break;

                    case R.id.btnSouth:
                        if (2 == player.getColLocation()) {
                            Toast.makeText(MainActivity.this, "Player is at MIN Column", Toast.LENGTH_SHORT).show();
                        } else {
                            player.setColLocation(player.getColLocation() + 1);
                            displayNewGrid();
                            displayNewMap();
                            playerMove();
                        }
                        break;

                    case R.id.btnEast:
                        if (2 == player.getRowLocation()) {
                            Toast.makeText(MainActivity.this, "Player is at MAX Row", Toast.LENGTH_SHORT).show();
                        } else {
                            player.setRowLocation(player.getRowLocation() + 1);
                            displayNewGrid();
                            displayNewMap();
                            playerMove();
                        }
                        break;

                    case R.id.btnWest:
                        if (0 == player.getRowLocation()) {
                            Toast.makeText(MainActivity.this, "Player is at MIN Row", Toast.LENGTH_SHORT).show();
                        } else {
                            player.setRowLocation(player.getRowLocation() - 1);
                            displayNewGrid();
                            displayNewMap();
                            playerMove();
                        }
                        break;
                }
            }
        };

        btnNorth.setOnClickListener(controllerOnClickListener);
        btnSouth.setOnClickListener(controllerOnClickListener);
        btnEast.setOnClickListener(controllerOnClickListener);
        btnWest.setOnClickListener(controllerOnClickListener);

        View.OnClickListener restartOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.getIntent().removeExtra("gameMap");
                MainActivity.this.getIntent().removeExtra("player");
                MainActivity.this.recreate();
                Toast.makeText(MainActivity.this, "RESTART", Toast.LENGTH_SHORT).show();
            }
        };

        btnRestart.setOnClickListener(restartOnClickListener);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gameMap.getArea(player.getColLocation(), player.getRowLocation()).isTown()) {
                    Intent intent = new Intent(MainActivity.this, MarketActivity.class);
                    intent.putExtra("gameMap", gameMap);
                    intent.putExtra("player", player);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, WildernessActivity.class);
                    intent.putExtra("gameMap", gameMap);
                    intent.putExtra("player", player);
                    startActivity(intent);
                }
            }
        });

    }

    public void displayNewMap() {
        Area currentArea = gameMap.getArea(player.getColLocation(), player.getRowLocation());
        String location = (currentArea.isTown()) ? "Market" : "Wilderness";
        textMap.setText(location);
    }

    public void displayNewGrid() {
        this.textGrid.setText("[" + player.getColLocation() + "] [" + player.getRowLocation() + "]");
    }

    public void displayNewCash() {
        this.textCash.setText("Cash: " + player.getCash());
    }

    public void displayNewHealth() {
        DecimalFormat df3 = new DecimalFormat("#.##");
        this.textHealth.setText("Health: " + df3.format(player.getHealth()));
    }

    public void displayNewEquipmentMass() {
        DecimalFormat df3 = new DecimalFormat("#.##");
        this.textEquipmentMass.setText("Equipment Mass: " + df3.format(player.getEquipmentMass()));
    }

    public void playerMove() {
        double health = player.getHealth();
        double equipmentMass = player.getEquipmentMass();
        player.setHealth(Math.max(0.0, health - 5.0 - (equipmentMass / 2.0)));
        displayNewHealth();
    }

    public boolean checkLoseCondition() {
        return player.getHealth() <= 0 ? true : false;
    }



}