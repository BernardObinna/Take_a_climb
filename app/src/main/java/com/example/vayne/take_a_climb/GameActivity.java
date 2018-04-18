package com.example.vayne.take_a_climb;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Random;


public class GameActivity extends Activity implements View.OnClickListener {

    private TextView[][] spots = new TextView[5][5];

    private int round;


    public boolean player1;
    public boolean against_computer;
    public boolean computer_turn = false;


    private int player1Points;
    private int player2Points;

    private TextView txtViewPlayer1;
    private TextView txtViewPlayer2;

    boolean five_by_five = false;
    boolean three_by_three = true;
    boolean ostart;
    boolean xstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        boolean theplayer = extras.getBoolean("player1");
        player1 = theplayer;

        boolean computer_status = extras.getBoolean("Comp_status");

        against_computer = computer_status;


        txtViewPlayer1 = findViewById(R.id.player_1);
        txtViewPlayer2 = findViewById(R.id.player_2);


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String txtID = "spot_" + i + j;
                int resID = getResources().getIdentifier(txtID, "id", getPackageName());
                spots[i][j] = findViewById(resID);
                spots[i][j].setOnClickListener(this);


            }
        }


        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        turn_record();

    }


    boolean playvar = player1;


    public void myDialog(View view) {

        resetGame();

        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);


        builder.setMessage("who starts first??").setCancelable(true);


        final boolean against_computer_status = against_computer;


        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {

                playerx();
                turn_record();




                xstart = true;

                if (xstart) {
                    ostart = false;
                } else if (xstart = false) {
                    ostart = true;
                }
            }

            // }

        });
        builder.setNegativeButton(" O ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                playero();
                turn_record();


                ostart = true;

                if (against_computer) {
                    ostart = false;
                    playerx();
                }


            }
        });

        builder.setNeutralButton("Switch VS P2 and VS Computer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                against_computer = !against_computer;

            }
        });


        builder.show();


    }


    public void playerx() {
        player1 = true;
    }

    public void playero() {
        player1 = false;
    }


    @Override
    public void onClick(View v) {
        if (!((TextView) v).getText().toString().equals("")) {
            return;
        }

        if (player1) {
            ((TextView) v).setText("X");
            ((TextView) v).setTextColor(Color.parseColor("#0000ff"));


        } else {
            ((TextView) v).setText("O");

            ((TextView) v).setTextColor(Color.parseColor("#ff0000"));


        }

        round++;


        if (five_by_five) {


            if (checkForWin()) {
                if (player1) {
                    player1Wins();
                    clear_board3();

                    if (against_computer || ostart) {
                        player1 = !player1;

                    }


                } else {
                    player2Wins();
                    clear_board3();
                    if (against_computer || ostart) {


                        player1 = !player1;
                    }

                }
            } else if (round == 25) {
                draw();
                clear_board3();
            } else {

                player1 = !player1;


            }

        } else if (three_by_three) {


            if (checkForWin3()) {
                if (player1) {
                    player1Wins();
                    clear_board3();

                    if (against_computer || ostart) {
                        player1 = !player1;

                    }


                } else {
                    player2Wins();
                    clear_board3();
                    if (against_computer || ostart) {


                        player1 = !player1;
                    }

                }
            } else if (round == 9) {
                draw();
                clear_board3();
                if (against_computer || ostart) {
                    player1 = !player1;

                }
            } else {

                player1 = !player1;


            }

        }


        turn_record();


        computer_turn = true;


        if (against_computer) {


            computerPlay();


        }


    }


    public void computerPlay() {
        if (computer_turn = true) {


            Random myRand = new Random();


            if (five_by_five) {


                for (int i = 0; i < 25; i++) {
                    playvar = player1;
                    int computer_step1 = myRand.nextInt(5);
                    int computer_step2 = myRand.nextInt(5);


                    if (!(spots[computer_step1][computer_step2]).getText().toString().equals("")) {
                        continue;
                    }

                    if (playvar) {

                        playvar = !player1;
                        spots[computer_step1][computer_step2].setText("X");

                        spots[computer_step1][computer_step2].setTextColor(Color.parseColor("#0000ff"));


                        break;
                    } else if (!playvar) {

                        playvar = !player1;

                        spots[computer_step1][computer_step2].setText("O");
                        spots[computer_step1][computer_step2].setTextColor(Color.parseColor("#ff0000"));

                        break;
                    }


                }


            } else if (three_by_three) {


                for (int i = 0; i < 9; i++) {
                    playvar = player1;
                    int computer_step1 = myRand.nextInt(3);
                    int computer_step2 = myRand.nextInt(3);


                    if (!(spots[computer_step1][computer_step2]).getText().toString().equals("")) {
                        continue;
                    }

                    if (playvar) {

                        playvar = !player1;
                        spots[computer_step1][computer_step2].setText("X");

                        spots[computer_step1][computer_step2].setTextColor(Color.parseColor("#0000ff"));


                        break;
                    } else if (!playvar) {

                        playvar = !player1;

                        spots[computer_step1][computer_step2].setText("O");
                        spots[computer_step1][computer_step2].setTextColor(Color.parseColor("#ff0000"));

                        break;
                    }


                }


            }


        }


        turn_record();


        round++;


        if (five_by_five) {

            if (checkForWin()) {
                if (player1) {
                    player1Wins();
                    clear_board5();
                } else {
                    player2Wins();
                    clear_board5();
                }
            } else if (round == 25) {
                draw();
                clear_board5();
            } else {
                player1 = !player1;
                turn_record();
            }
        } else if (three_by_three) {

            if (checkForWin3()) {
                if (player1) {

                    player1Wins();

                    clear_board3();

                } else {
                    player2Wins();
                    clear_board3();

                }
            } else if (round == 9) {
                draw();
                clear_board3();
            } else {
                player1 = !player1;
                turn_record();
            }


        }
        computer_turn = false;


    }


    public void turn_record() {

        String turn_value;
        if (player1) {
            turn_value = "X";
        } else {
            turn_value = "O";
        }

        TextView turn_checker = (TextView) findViewById(R.id.turn_stat);
        turn_checker.setText(turn_value);

    }


    private boolean checkForWin3() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = spots[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])

                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])

                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }


    private boolean checkForWin() {
        String[][] field = new String[5][5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                field[i][j] = spots[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2]) && field[i][0].equals(field[i][3]) && field[i][0].equals(field[i][4])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        for (int i = 0; i < 5; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && field[0][i].equals(field[3][i]) && field[0][i].equals(field[4][i]) && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && field[0][0].equals(field[3][3])
                && field[0][0].equals(field[4][4])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][4].equals(field[1][3])
                && field[0][4].equals(field[2][2])
                && field[0][4].equals(field[3][1])
                && field[0][4].equals(field[4][0])
                && !field[0][4].equals("")) {
            return true;
        }

        return false;
    }


    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText() {
        txtViewPlayer1.setText("Player 1: " + player1Points);
        txtViewPlayer2.setText("Player 2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                spots[i][j].setText("");
            }
        }

        round = 0;
        player1 = true;


    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
        turn_record();
    }

    public void clear_board3() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                spots[i][j].setText("");
            }
        }
    }


    public void clear_board5() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                spots[i][j].setText("");
            }
        }
    }


    public void board_changer(View view) {

        resetGame();

        if (five_by_five) {
            TextView spot03 = findViewById(R.id.spot_03);
            spot03.setVisibility(View.GONE);

            TextView spot04 = findViewById(R.id.spot_04);
            spot04.setVisibility(View.GONE);

            TextView spot13 = findViewById(R.id.spot_13);
            spot13.setVisibility(View.GONE);

            TextView spot14 = findViewById(R.id.spot_14);
            spot14.setVisibility(View.GONE);


            TextView spot23 = findViewById(R.id.spot_23);
            spot23.setVisibility(View.GONE);

            TextView spot24 = findViewById(R.id.spot_24);
            spot24.setVisibility(View.GONE);

            TextView spot30 = findViewById(R.id.spot_30);
            spot30.setVisibility(View.GONE);


            TextView spot31 = findViewById(R.id.spot_31);
            spot31.setVisibility(View.GONE);

            TextView spot32 = findViewById(R.id.spot_32);
            spot32.setVisibility(View.GONE);

            TextView spot33 = findViewById(R.id.spot_33);
            spot33.setVisibility(View.GONE);


            TextView spot34 = findViewById(R.id.spot_34);
            spot34.setVisibility(View.GONE);

            TextView spot40 = findViewById(R.id.spot_40);
            spot40.setVisibility(View.GONE);

            TextView spot41 = findViewById(R.id.spot_41);
            spot41.setVisibility(View.GONE);

            TextView spot42 = findViewById(R.id.spot_42);
            spot42.setVisibility(View.GONE);


            TextView spot43 = findViewById(R.id.spot_43);
            spot43.setVisibility(View.GONE);


            TextView spot44 = findViewById(R.id.spot_44);
            spot44.setVisibility(View.GONE);

            LinearLayout layout1 = findViewById(R.id.extra_layout1);
            layout1.setVisibility(View.GONE);


            LinearLayout layout2 = findViewById(R.id.extra_layout2);
            layout2.setVisibility(View.GONE);


            five_by_five = false;
            three_by_three = true;
        } else {
            TextView spot03 = findViewById(R.id.spot_03);
            spot03.setVisibility(View.VISIBLE);


            TextView spot04 = findViewById(R.id.spot_04);
            spot04.setVisibility(View.VISIBLE);

            TextView spot13 = findViewById(R.id.spot_13);
            spot13.setVisibility(View.VISIBLE);

            TextView spot14 = findViewById(R.id.spot_14);
            spot14.setVisibility(View.VISIBLE);


            TextView spot23 = findViewById(R.id.spot_23);
            spot23.setVisibility(View.VISIBLE);

            TextView spot24 = findViewById(R.id.spot_24);
            spot24.setVisibility(View.VISIBLE);


            TextView spot30 = findViewById(R.id.spot_30);
            spot30.setVisibility(View.VISIBLE);


            TextView spot31 = findViewById(R.id.spot_31);
            spot31.setVisibility(View.VISIBLE);

            TextView spot32 = findViewById(R.id.spot_32);
            spot32.setVisibility(View.VISIBLE);

            TextView spot33 = findViewById(R.id.spot_33);
            spot33.setVisibility(View.VISIBLE);


            TextView spot34 = findViewById(R.id.spot_34);
            spot34.setVisibility(View.VISIBLE);


            TextView spot40 = findViewById(R.id.spot_40);
            spot40.setVisibility(View.VISIBLE);

            TextView spot41 = findViewById(R.id.spot_41);
            spot41.setVisibility(View.VISIBLE);

            TextView spot42 = findViewById(R.id.spot_42);
            spot42.setVisibility(View.VISIBLE);


            TextView spot43 = findViewById(R.id.spot_43);
            spot43.setVisibility(View.VISIBLE);


            TextView spot44 = findViewById(R.id.spot_44);
            spot44.setVisibility(View.VISIBLE);


            LinearLayout layout1 = findViewById(R.id.extra_layout1);
            layout1.setVisibility(View.VISIBLE);


            LinearLayout layout2 = findViewById(R.id.extra_layout2);
            layout2.setVisibility(View.VISIBLE);


            five_by_five = true;
            three_by_three = false;
        }

    }


}
