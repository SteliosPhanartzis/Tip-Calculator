package edu.qc.seclass.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    //Variables
    private EditText checkAmountEdit;
    private EditText partySizeEdit;
    private Button computeAmount;
    private TextView tipText15;
    private TextView tipText20;
    private TextView tipText25;
    private TextView totalText15;
    private TextView totalText20;
    private TextView totalText25;
    private Float checkAmount;
    private Integer partySize;
    private String checkStr, partyStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAmountEdit = findViewById(R.id.checkAmountValue);
        partySizeEdit = findViewById(R.id.partySizeValue);
        computeAmount = findViewById(R.id.buttonCompute);
        tipText15 = findViewById(R.id.fifteenPercentTipValue);
        tipText20 = findViewById(R.id.twentyPercentTipValue);
        tipText25 = findViewById(R.id.twentyfivePercentTipValue);
        totalText15 = findViewById(R.id.fifteenPercentTotalValue);
        totalText20 = findViewById(R.id.twentyPercentTotalValue);
        totalText25 = findViewById(R.id.twentyfivePercentTotalValue);
        //Disable output fields, allow value copy
        tipText15.setKeyListener(null);
        tipText15.setTextIsSelectable(true);
        tipText20.setKeyListener(null);
        tipText20.setTextIsSelectable(true);
        tipText25.setKeyListener(null);
        tipText25.setTextIsSelectable(true);
        totalText15.setKeyListener(null);
        totalText15.setTextIsSelectable(true);
        totalText20.setKeyListener(null);
        totalText20.setTextIsSelectable(true);
        totalText25.setKeyListener(null);
        totalText25.setTextIsSelectable(true);
        //Create listener for compute button
        computeAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }
    public void calculate(){
        Float result = 0.0f;
        checkStr = checkAmountEdit.getText().toString();
        partyStr = partySizeEdit.getText().toString();
        if(checkStr.matches("^[\\d]+(.[\\d]{1,2})?$") && partyStr.matches("^[\\d]+$") && Integer.parseInt(partyStr) > 0 && Double.parseDouble(checkStr) > 0){
            checkAmount = Float.parseFloat(checkAmountEdit.getText().toString());
            partySize = Integer.parseInt(partySizeEdit.getText().toString());
            result = checkAmount / partySize;
            //Round to nearest integer
            tipText15.setText(String.valueOf(Math.round((result * 15) / 100)));
            tipText20.setText(String.valueOf(Math.round((result * 20) / 100)));
            tipText25.setText(String.valueOf(Math.round((result * 25) / 100)));
            totalText15.setText(String.valueOf(Math.round((result * 115) / 100)));
            totalText20.setText(String.valueOf(Math.round((result * 120) / 100)));
            totalText25.setText(String.valueOf(Math.round((result * 125) / 100)));
        }else if(partyStr.matches("^[\\d]+$") && !checkStr.matches("^[\\d]+(.[\\d]{1,2})?$")){
            Toast.makeText(this,"Please enter a valid check amount", Toast.LENGTH_SHORT).show();
        }else if(checkStr.matches("^[\\d]+(.[\\d]{1,2})?$") && !partyStr.matches("^[\\d]+$")){
            Toast.makeText(this,"Please enter a valid party size", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Please enter a valid input", Toast.LENGTH_SHORT).show();
        }
    }
}
