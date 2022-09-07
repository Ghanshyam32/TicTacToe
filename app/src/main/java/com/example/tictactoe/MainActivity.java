package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<int[]> combinationList = new ArrayList<>();

    private int[] boxpositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    private int playerTurn = 1;
    private int totalSelectBoxes = 1;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationList.add(new int[]{0, 1, 2});
        combinationList.add(new int[]{3, 4, 5});
        combinationList.add(new int[]{6, 7, 8});
        combinationList.add(new int[]{0, 3, 6});
        combinationList.add(new int[]{0, 4, 7});
        combinationList.add(new int[]{2, 5, 8});
        combinationList.add(new int[]{2, 4, 6});
        combinationList.add(new int[]{0, 4, 8});

        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(0)) {
                    performAction((ImageView) view, 0);
                }

            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(1)) {
                    performAction((ImageView) view, 1);
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(2)) {
                    performAction((ImageView) view, 2);
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(3)) {
                    performAction((ImageView) view, 3);
                }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(4)) {
                    performAction((ImageView) view, 4);
                }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(5)) {
                    performAction((ImageView) view, 5);
                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(6)) {
                    performAction((ImageView) view, 6);
                }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isBoxSelectable(7)) {
                    performAction((ImageView) view, 7);
                }
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)) {
                    performAction((ImageView) view, 8);
                }
            }
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPositions) {
        boxpositions[selectedBoxPositions] = playerTurn;
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross);
            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this, playerOneName.getText().toString() + " has Won the match", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (totalSelectBoxes == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "It's a Draw", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.o);
            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this, playerTwoName.getText().toString() + " has Won the match", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (selectedBoxPositions == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "It is Draw", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        } else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }


    private boolean checkPlayerWin() {
        for (int i = 0; i < combinationList.size(); i++) {
            final int[] combination = combinationList.get(i);
            if (boxpositions[combination[0]] == playerTurn && boxpositions[combination[1]] == playerTurn && boxpositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;
        if (boxpositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }

    public void restartMatch() {
        boxpositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        playerTurn = 1;
        totalSelectBoxes = 1;
        image1.setImageResource(R.drawable.blue_trans);
        image2.setImageResource(R.drawable.blue_trans);
        image3.setImageResource(R.drawable.blue_trans);
        image4.setImageResource(R.drawable.blue_trans);
        image5.setImageResource(R.drawable.blue_trans);
        image6.setImageResource(R.drawable.blue_trans);
        image7.setImageResource(R.drawable.blue_trans);
        image8.setImageResource(R.drawable.blue_trans);
        image9.setImageResource(R.drawable.blue_trans);

    }
}
