package com.example.cacli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView Res_tv,Sol_tv;
    MaterialButton Bc,BOpenP,BCloseP;
    MaterialButton BDiv,BMul,BSum,BSub,BEq;
    MaterialButton B1,B2,B3,B4,B5,B6,B7,B8,B9,B0;
    MaterialButton BDot,Bsqrt;
    private Object data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Res_tv=findViewById(R.id.result_tv);
        Sol_tv=findViewById(R.id.solution_tv);

        assignId(Bc,R.id.button_c);
        assignId(B0,R.id.button_Zero);
        assignId(B1,R.id.button_on1);
        assignId(B2,R.id.button_two2);
        assignId(B3,R.id.button_Three3);
        assignId(B4,R.id.button_Four);
        assignId(B5,R.id.button_Five);
        assignId(B6,R.id.button_Six);
        assignId(B7,R.id.button_seven);
        assignId(B8,R.id.button_eight);
        assignId(B9,R.id.button_Nine);
        assignId(BDiv,R.id.button_Div);
        assignId(BSum,R.id.button_SumP);
        assignId(BSub,R.id.button_Sub);
        assignId(BMul,R.id.button_Mul);
        assignId(BDot,R.id.button_Dot_);
        assignId(Bsqrt,R.id.button_sqrt);
        assignId(BEq,R.id.button_Eql);
        assignId(BOpenP,R.id.button_opParen);
        assignId(BCloseP,R.id.button_clParen);
    }

    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();
        String dataToCal=Sol_tv.getText().toString();

        if(buttonText.equals("C"))
        {
            Sol_tv.setText("");
            Res_tv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            Sol_tv.setText(Res_tv.getText());
            return;
        }

        else
        {
            dataToCal=dataToCal+buttonText;
        }
        Sol_tv.setText(dataToCal);

        String finalres = getResult(dataToCal);
        if(!finalres.equals("Error"))
        {
            Res_tv.setText(finalres);
        }

    }



    String getResult(String data) {


        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalres = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalres.endsWith(".0"))
            {
                finalres=finalres.replace(".0","");
            }
            return finalres;

        } catch (Exception e) {
            return "Error";

        }
    }
}