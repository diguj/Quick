package com.core.cwtailor.measurement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.core.cwtailor.R;

public class Select extends AppCompatActivity {

    public static final String EXTRA_TEXT = "com.core.cwtailor.measurement.EXTRA_TEXT";

    TextView email_id, email;
    GridLayout mainGrid;
    private CardView mens_shirt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        email_id=(TextView)findViewById(R.id.txt_email_id);

        Intent intent =getIntent();

        if(intent.hasExtra("email")){
            email_id.setText(intent.getStringExtra("email"));
        }

        mainGrid= (GridLayout)findViewById(R.id.main_grid);
        mens_shirt=(CardView) findViewById(R.id.mens_shirt);

    //    takemeasurements(mainGrid);
        //toast(mainGrid);
        setSingleEvent(mainGrid);
    }

/*    wait senario ky ahe sanfg

    grid madhe cards show le;et tyat card var click kelyavar activity open vhayla havi ahe/

    private void toast(GridLayout mainGrid){
        for(int i=0;i<mainGrid.getChildCount();i++){
            CardView cardView=(CardView)mainGrid.getChildAt(i);
            final int finali =i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(Select.this, "Clicked at " + finali,Toast.LENGTH_LONG).show();
                }

            });
        }
    }*/


    private void setSingleEvent(GridLayout dashgrid) {
        for (int i = 0; i < dashgrid.getChildCount(); i++) {
            CardView cardView = (CardView) dashgrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Toast.makeText(Select.this, "Clicked at " + finalI,Toast.LENGTH_LONG).show();
                    switch (finalI) {

                        case 0:
                            TextView editText1 = (TextView) findViewById(R.id.txt_email_id);
                            String text = editText1.getText().toString();
                            Intent feedbackintent = new Intent(Select.this, Shirt.class);
                            feedbackintent.putExtra(EXTRA_TEXT,text);
                            feedbackintent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(feedbackintent);


                            finish();
                            break;
                        case 1:
                             TextView editText2 = (TextView) findViewById(R.id.txt_email_id);
                            String text2 = editText2.getText().toString();
                            Intent feedbackintent2 = new Intent(Select.this, Pant.class);
                            feedbackintent2.putExtra(EXTRA_TEXT,text2);
                            feedbackintent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(feedbackintent2);
                            finish();
                            break;

                        /*case 2:
                            Toast.makeText(Dashboard.this, "Workshop not available for your college yet ", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Intent intent = new Intent(Dashboard.this, FeedbackTest.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            break;
                        case 4:
                            Toast.makeText(Dashboard.this, "Project selection phase under confirmation", Toast.LENGTH_SHORT).show();
                            break;
                        case 5:
                            Toast.makeText(Dashboard.this, "Ongoing projects details unknown", Toast.LENGTH_SHORT).show();
                            break;*/
                        default:
                            Toast.makeText(Select.this, "Will get back soon", Toast.LENGTH_SHORT).show();
                        }
                    }
                   /* Intent intent = new Intent(Dashboard.this,
                            studinternship.class);
                    startActivity(intent);
                    finish();*/

            });
        }
    }

}
