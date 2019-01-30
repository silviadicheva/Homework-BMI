package com.example.silviq.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {
    @NotEmpty
    @Min(10)
    @Max(300)
    EditText etWeight;
    @NotEmpty
    @Min(50)
    @Max(225)
    EditText etHeight;
    TextView results;
    Button btnCalculate;
    ImageView image1;
    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validator = new Validator(this);
        validator.setValidationListener(this);

        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);
        results = (TextView) findViewById(R.id.resultText);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        image1 = (ImageView) findViewById(R.id.image1);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();

            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        double weight, height, BMI;
        weight = Double.parseDouble(etWeight.getText().toString());
        height = Double.parseDouble(etHeight.getText().toString()) / 100;

        //BMI=weight(kg)/height*height(cm)
        BMI = weight / Math.pow(height, 2);

        /*
         * Under weight   < 18,50
         * Normal       18,5 - 24,99
         * Overweight     ≥ 25,00
         * */

        int situation = 0;
        if (BMI <= 18.5) {
            situation = 1;
        }
        if (BMI > 18.5 && BMI < 24.99) {
            situation = 2;
        } else {
            if (BMI > 25) {
                situation = 3;
            }
        }
        switch (situation) {
            case 1:
                Picasso.with(getApplicationContext()).load("https://st2.depositphotos.com/8776448/11699/i/950/depositphotos_116992322-stock-photo-cartoon-underweight-woman.jpg").into(image1);
                Crouton.makeText(MainActivity.this, "Underweight", Style.INFO).show();
                break;
            case 2:
                Picasso.with(getApplicationContext()).load("https://www.realmenrealstyle.com/wp-content/uploads/2012/01/images3.jpg").into(image1);
                Crouton.makeText(MainActivity.this, "Normal weight", Style.CONFIRM).show();
                break;
            case 3:
                Picasso.with(getApplicationContext()).load("https://st.depositphotos.com/1292351/3979/v/950/depositphotos_39798659-stock-illustration-cartoon-overweight-man-holding-two.jpg").into(image1);
                Crouton.makeText(MainActivity.this, "Overweight", Style.ALERT).show();
                break;
        }

        results.setText("" + BMI);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view ==etHeight) {
                ((EditText) view).setError("Please enter a valid height");
            }
             else if(view ==etWeight) {
                ((EditText) view).setError("Please enter a valid weight");
            }else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }
}



