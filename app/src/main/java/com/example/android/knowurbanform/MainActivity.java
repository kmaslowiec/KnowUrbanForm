package com.example.android.knowurbanform;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private CheckBox one;
    private CheckBox two;
    private CheckBox three;
    private CheckBox four;
    private RadioGroup gr;
    private EditText txt;
    private Button submitButton;
    private Button resetButton;


    int point = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main); // object should be always initialized below the method
        String s = getResources().getString(R.string.good_luck);
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        this.submitButton = findViewById(R.id.submit_button);
        this.resetButton = findViewById(R.id.reset_button);
        resetButton.setClickable(false);



    }



    public void counter(boolean answer) {

        if (answer) {
            this.point++;
        } else {
            this.point--;
        }


    }

    public void firstQuestion() {
        this.gr = findViewById(R.id.radio_group_question_one);
        RadioButton rb = findViewById(R.id.ans_one_three_radio_button);
        int id = rb.getId();

        boolean result = gr.getCheckedRadioButtonId() == id;
        counter(result);

    }

    public void secondQuestion() {
        // this enables to call the instance objects !!!
        this.one = findViewById(R.id.que_two_ans_one);
        this.two = findViewById(R.id.que_two_ans_two);
        this.three = findViewById(R.id.que_two_ans_three);
        this.four = findViewById(R.id.que_two_ans_four);

        if (one.isChecked() && three.isChecked()) {
            counter(true);
        } else
            counter(false);

    }

    public void threeQuestion() {
        this.txt = findViewById(R.id.text_q_three);


        String answer = txt.getText().toString().toLowerCase();

        if (answer.equals("object") || answer.equals("object ") || answer.equals("object class") ) {
            counter(true);
        } else
            counter(false);


    }

    public void displayResults(View v) {

        firstQuestion();
        secondQuestion();
        threeQuestion();
        TextView txt = findViewById(R.id.display);
        String s = String.format(Locale.CANADA, "%s: %d", getResources().getString(R.string.score), point);
        txt.setText(s);
        submitButton.setClickable(false);
        resetButton.setClickable(true);

    }

    public void reset(View v) {
        this.point = 0;
        unCheckAll();
        TextView txt = findViewById(R.id.display);
        String s = String.format(Locale.CANADA, "%s: %d", getResources().getString(R.string.score), point);
        txt.setText(s);
        submitButton.setClickable(true);
        resetButton.setClickable(false);
    }

    public void unCheckAll() {

        CheckBox[] allBoxes = {one, two, three, four};
        gr.clearCheck();
        txt.setText(null);


        for (CheckBox unCheck : allBoxes) {
            if (unCheck.isChecked()) {
                unCheck.performClick();
            }
        }

    }
}
