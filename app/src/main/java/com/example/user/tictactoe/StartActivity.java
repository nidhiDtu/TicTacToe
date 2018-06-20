package com.example.user.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.MeasureSpec.getSize;

public class StartActivity extends AppCompatActivity {

    private Button start;
    private TextView text;
    private RadioButton radioButton;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initialise();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(StartActivity.this,MainActivity.class);

                int selectedSizeOfBoard=radioGroup.getCheckedRadioButtonId();
                int size= getSize(selectedSizeOfBoard);
                radioButton=(RadioButton)findViewById(selectedSizeOfBoard);
                Toast.makeText(StartActivity.this,radioButton.getText().toString()+" size is selected !",Toast.LENGTH_LONG).show();

                intent.putExtra("size_key",size);
                startActivity(intent);
            }
        });
    }

    private int getSize(int id){
        int a=0;
        switch (id){
            case R.id.bt1:
                a=3;
                break;
            case R.id.bt2:
                a=4;
                break;
            case R.id.bt3:
                a=5;
                break;
        }

        return a;
    }

    private void initialise() {
        text=(TextView)findViewById(R.id.text);
        radioGroup=(RadioGroup)findViewById(R.id.radio);
//        button3=(RadioButton) findViewById(R.id.bt1);
//        button4=(RadioButton)findViewById(R.id.bt2);
//        button5=(RadioButton)findViewById(R.id.bt3);
        start=(Button)findViewById(R.id.start);
    }
}
