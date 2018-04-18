package com.example.vayne.take_a_climb;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;





public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);


    }

    public boolean player1 = true;

    public boolean against_computer = false;


    public void playerx() {
        player1 = true;
    }

    public void playero() {
        player1 = false;
    }


    public void myDialog(View view) {


        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);


        builder.setMessage("who starts first??").setCancelable(true);
        builder.setPositiveButton("X", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {


                playerx();

                Intent i = new Intent(MainActivity.this, GameActivity.class);
                i.putExtra("player1", player1);
                i.putExtra("Comp_status", against_computer);
                startActivity(i);


            }

        });
        builder.setNegativeButton("O", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                playero();

                Intent i = new Intent(MainActivity.this, GameActivity.class);
                i.putExtra("player1", player1);
                i.putExtra("Comp_status", against_computer);
                startActivity(i);


            }
        });


        builder.show();


    }


    public void setComputer(View view) {
        against_computer = true;
    }

    public void setpvp(View view) {
        against_computer = false;
    }


}
