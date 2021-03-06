package com.example.android.knowurbanform;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int point = 0;
    private CheckBox one;
    private CheckBox two;
    private CheckBox three;
    private CheckBox four;
    private RadioGroup gr;
    private RadioGroup gr2;
    private EditText txt;
    private Button submitButton;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // object should be always initialized below the line
        this.gr = findViewById(R.id.radio_group_question_one);
        this.gr2 = findViewById(R.id.radio_group_question_four);
        this.submitButton = findViewById(R.id.submit_button);
        this.resetButton = findViewById(R.id.reset_button);
        resetButton.setClickable(false);

    }

    /**
     * connects ImageView with the web browser
     *
     * @param v
     */

    public void website(View v) {

        Uri webpage = Uri.parse(getResources().getString(R.string.web));
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * increase the points if the answer is correct. Decrease if it is wrong.
     *
     * @param answer
     */

    public void counter(boolean answer) {

        if (answer) {
            this.point++;
        } else {
            this.point--;
        }

    }

    /**
     * logic for the first question
     */
    public void firstQuestion() {

        RadioButton rb = findViewById(R.id.ans_one_two_radio_button);
        int id = rb.getId();

        boolean result = gr.getCheckedRadioButtonId() == id;
        counter(result);

    }

    /**
     * logic for the second question
     */
    public void secondQuestion() {
        // this enables to call the instance objects !!!
        this.one = findViewById(R.id.que_two_ans_one);
        this.two = findViewById(R.id.que_two_ans_two);
        this.three = findViewById(R.id.que_two_ans_three);
        this.four = findViewById(R.id.que_two_ans_four);

        if (one.isChecked() && three.isChecked() && four.isChecked()) {
            counter(true);
        } else
            counter(false);

    }

    /**
     * logic for the third question
     */
    public void threeQuestion() {
        this.txt = findViewById(R.id.text_q_three);
        String answer = txt.getText().toString().toLowerCase();

        if (answer.trim().equalsIgnoreCase("szczecin")){
            counter(true);
        } else
            counter(false);


    }

    /**
     * logic for the four question
     */
    public void fourQuestion() {
        RadioButton rb = findViewById(R.id.ans_four_four_radio_button);
        int id = rb.getId();

        boolean result = gr2.getCheckedRadioButtonId() == id;
        counter(result);

    }

    /**
     * counts the points
     *
     * @param v
     */
    public void displayResults(View v) {

        firstQuestion();
        secondQuestion();
        threeQuestion();
        fourQuestion();
        submitButton.setClickable(false);
        resetButton.setClickable(true);
        submitting();

    }

    /**
     * resets the app
     *
     * @param v
     */
    public void reset(View v) {
        this.point = 0;
        unCheckAll();
        submitButton.setClickable(true);
        resetButton.setClickable(false);
    }

    /**
     * unchecks all questions
     */
    public void unCheckAll() {
        CheckBox[] allBoxes = {one, two, three, four};
        gr.clearCheck();
        gr2.clearCheck();
        txt.setText(null);

        for (CheckBox unCheck : allBoxes) {
            if (unCheck.isChecked()) {
                unCheck.performClick();
            }
        }

    }

    /**
     * prints Toast based on the points
     */
    public void submitting() {

        if (point == 4) {
            Toast.makeText(this, getResources().getString(R.string.great_job), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, getResources().getString(R.string.you_can_better), Toast.LENGTH_SHORT).show();
            unCheckAll();
            submitButton.setClickable(true);
            resetButton.setClickable(false);
        }

    }

}
