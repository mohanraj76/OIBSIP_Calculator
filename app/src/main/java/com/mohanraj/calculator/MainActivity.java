package com.mohanraj.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultText, solutionText ;
    MaterialButton clearButton, openBracletButton, closeBracketButton;
    MaterialButton button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    MaterialButton divisionButton, multiplicationButton, additionButton, substractionButton;
    MaterialButton allclearButton, dotButton, equalstoButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = findViewById(R.id.text_view_result);
        solutionText = findViewById(R.id.text_view_solution);

        assignId(clearButton, R.id.clear_button);
        assignId(openBracletButton, R.id.left_bracket_button);
        assignId(closeBracketButton, R.id.right_bracket_button);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(divisionButton, R.id.division_button);
        assignId(multiplicationButton, R.id.multiplication_button);
        assignId(additionButton, R.id.addition_button);
        assignId(substractionButton, R.id.substraction_button);
        assignId(allclearButton, R.id.allclear_button);
        assignId(dotButton, R.id.dot_button);
        assignId(equalstoButton, R.id.equal_button);

    }

    void assignId(MaterialButton btn, int id){

        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String CalculateData = solutionText.getText().toString();

        if(buttonText.equals("AC")){
            solutionText.setText("");
            resultText.setText(" ");
            return;
        }
        if(buttonText.equals("=")){
            solutionText.setText(resultText.getText());
            return;
        }
        if(buttonText.equals("C")){
            CalculateData = CalculateData.substring(0, CalculateData.length()-1);
        }else {

            CalculateData = CalculateData+buttonText;
        }


        solutionText.setText(CalculateData);

        String finalresult = getResult(CalculateData);

        if (!finalresult.equals("err")){
            resultText.setText(finalresult);
        }

    }

    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalresult = context.evaluateString(scriptable, data, "Javascript", 1 , null).toString();
            if (finalresult.endsWith(".0")){
                finalresult = finalresult.replace(".0","");
            }
            return finalresult;

        }catch (Exception e){
            return "err";
        }
    }
}