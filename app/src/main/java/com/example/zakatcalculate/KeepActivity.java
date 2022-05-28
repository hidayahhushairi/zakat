package com.example.zakatcalculate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class KeepActivity extends AppCompatActivity implements View.OnClickListener {
    TextView etGram;
    TextView etValue;
    Button btnCalculate, btnReset;
    TextView tvOutput;
    float zakatPayable;
    float totalZakat;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keep);

        etGram= (EditText) findViewById(R.id.editTextGram);
        etValue= (EditText) findViewById(R.id.editTextGoldValue);
        btnCalculate=(Button) findViewById(R.id.btnCalculate);
        btnReset=(Button) findViewById(R.id.btnReset);
        tvOutput=(TextView) findViewById(R.id.tvOutput);

        btnCalculate.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        sharedPref = this.getSharedPreferences("zakatPayable", Context.MODE_PRIVATE );
        sharedPref = this.getSharedPreferences("totalZakat", Context.MODE_PRIVATE );

        //load the data
        float zakatPayable = sharedPref.getFloat("zakatPayable", 0.0F);
        float totalZakat = sharedPref.getFloat("totalZakat", 0.0F);
    }

    @Override
    public void onClick(View k) {

        try{
            switch (k.getId()){
                case R.id.btnCalculate:
                    calculate();
                    break;

                case R.id.btnReset:
                    reset();
                    etGram.setText(" ");
                    etValue.setText(" ");
                    tvOutput.setText(" ");
                    break;
            }
        }
        catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        } catch (Exception exp) {
            Toast.makeText(this, "Unknown Exception" + exp.getMessage(), Toast.LENGTH_SHORT).show();

            Log.d("Exception", exp.getMessage());
        }
    }
    public void calculate(){

        DecimalFormat df = new DecimalFormat("#,##0.00");
        String variable = etGram.getText().toString();
        String variable2 = etValue.getText().toString();

        float gram = Float.parseFloat(variable);
        float value = Float.parseFloat(variable2);

        float totalGoldValue = gram * value;
        float deductUruf = gram - 85;

        if (deductUruf >= 0) {
            zakatPayable = (gram - 85) * value;
            totalZakat = (float) (zakatPayable * 0.025);
            tvOutput.setText("Total Gold Value: RM " + df.format(totalGoldValue) + "\nZakat Payable: RM " + df.format(zakatPayable) + "\nTotal Zakat: RM " + df.format(totalZakat));
        } else {
            zakatPayable = (float) 0.00;
            totalZakat = (float) 0.00;
            tvOutput.setText("Total Gold Value: RM " + df.format(totalGoldValue) + "\nZakat Payable: RM " + df.format(zakatPayable) + "\nTotal Zakat: RM " + df.format(totalZakat));
        }
    }
    public void reset()
    {
        zakatPayable=(float) 0.00;
        totalZakat=(float) 0.00;
        tvOutput.setText(""+zakatPayable);
        tvOutput.setText(""+totalZakat);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putFloat("reset", (float) zakatPayable);
        editor.putFloat("reset", (float) totalZakat);
        editor.apply();
    }
}