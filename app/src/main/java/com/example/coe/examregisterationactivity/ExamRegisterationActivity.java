package com.example.coe.examregisterationactivity;

import static com.example.coe.examactivity.ExamsAdapter.clicked_exam_fee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.example.coe.payment.FeePaymentOptionActivity;


public class ExamRegisterationActivity extends AppCompatActivity {
    EditText amount,name,reg_no;
    Button reg;
    ImageView reset;
    public static String usr_name = null,register = null;
    @Override
    public void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);

        setContentView(R.layout.activity_exam_registration_form);
        amount = (EditText) findViewById(R.id.listFeeDetails);
        name = (EditText) findViewById(R.id.txtName);
        reg_no = (EditText) findViewById(R.id.txtRegisterNo);
        amount.setText(clicked_exam_fee);
        reg = (Button) findViewById(R.id.btnPayment);
        reset = (ImageView)findViewById(R.id.btnPrevious_reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr_name = name.getText().toString();
                register = reg_no.getText().toString();
               // Toast.makeText(ExamRegisterationActivity.this,"Under Developement",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ExamRegisterationActivity.this, FeePaymentOptionActivity.class));
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
