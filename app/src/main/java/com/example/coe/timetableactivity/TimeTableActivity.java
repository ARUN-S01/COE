package com.example.coe.timetableactivity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coe.R;
import com.example.coe.adminactivity.admincreateexamactivity.CreateExamActivity;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeTableActivity extends AppCompatActivity {
    private Button mPickDate;
    int endDay, endYear, endMonth;
    final Calendar myCalendar= Calendar.getInstance();
    Button mPickStartDateButton,mPickEndDateButton;
    TextView mShowSelectedStartDateText,mShowSelectedEndDateText;
    String startdate_txt = null,enddate_lext = null;
    static final int DATE_DIALOG_ID = 0;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        TextView day1_1st = (TextView) findViewById(R.id.day1_1st_hr);
        TextView day1_2nd = (TextView) findViewById(R.id.day1_2nd_hr);
        TextView day1_3rd = (TextView) findViewById(R.id.day1_3rd_hr);
        TextView day1_4th = (TextView) findViewById(R.id.day1_4th_hr);
        TextView day1_5th = (TextView) findViewById(R.id.day1_5th_hr);
        TextView day1_6th = (TextView) findViewById(R.id.day1_6th_hr);

        TextView day2_1st = (TextView) findViewById(R.id.day2_1st_hr);
        TextView day2_2nd = (TextView) findViewById(R.id.day2_2nd_hr);
        TextView day2_3rd = (TextView) findViewById(R.id.day2_3rd_hr);
        TextView day2_4th = (TextView) findViewById(R.id.day2_4th_hr);
        TextView day2_5th = (TextView) findViewById(R.id.day2_5th_hr);
        TextView day2_6th = (TextView) findViewById(R.id.day2_6th_hr);

        TextView day3_1st = (TextView) findViewById(R.id.day3_1st_hr);
        TextView day3_2nd = (TextView) findViewById(R.id.day3_2nd_hr);
        TextView day3_3rd = (TextView) findViewById(R.id.day3_3rd_hr);
        TextView day3_4th = (TextView) findViewById(R.id.day3_4th_hr);
        TextView day3_5th = (TextView) findViewById(R.id.day3_5th_hr);
        TextView day3_6th = (TextView) findViewById(R.id.day3_6th_hr);

        TextView day4_1st = (TextView) findViewById(R.id.day4_1st_hr);
        TextView day4_2nd = (TextView) findViewById(R.id.day4_2nd_hr);
        TextView day4_3rd = (TextView) findViewById(R.id.day4_3rd_hr);
        TextView day4_4th = (TextView) findViewById(R.id.day4_4th_hr);
        TextView day4_5th = (TextView) findViewById(R.id.day4_5th_hr);
        TextView day4_6th = (TextView) findViewById(R.id.day4_6th_hr);

        TextView day5_1st = (TextView) findViewById(R.id.day5_1st_hr);
        TextView day5_2nd = (TextView) findViewById(R.id.day5_2nd_hr);
        TextView day5_3rd = (TextView) findViewById(R.id.day5_3rd_hr);
        TextView day5_4th = (TextView) findViewById(R.id.day5_4th_hr);
        TextView day5_5th = (TextView) findViewById(R.id.day5_5th_hr);
        TextView day5_6th = (TextView) findViewById(R.id.day5_6th_hr);
        back = (ImageView) findViewById(R.id.imageView_1);
        String[] items = new String[]{"Semester 1", "Semester 2", "Semester 3", "Semester 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        AutoCompleteTextView dropdown = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getApplicationContext(), "Semester 1 Selected", Toast.LENGTH_SHORT).show();
                } else if (i == 1) {
                    Toast.makeText(getApplicationContext(), "Semester 2 Selected", Toast.LENGTH_SHORT).show();
                } else if (i == 2) {
                    Toast.makeText(getApplicationContext(), "Semester 3 Selected", Toast.LENGTH_SHORT).show();
                } else if (i == 3) {
                    day1_1st.setText("ELECT");
                    day1_2nd.setText("LATMATH");
                    day1_3rd.setText("ADP");
                    day1_4th.setText("DBMS");
                    day1_5th.setText("LAB");
                    day1_6th.setText("LAB");

                    day2_1st.setText("ADP");
                    day2_2nd.setText("PROENG");
                    day2_3rd.setText("MATH");
                    day2_4th.setText("CN");
                    day2_5th.setText("PM");
                    day2_6th.setText("CONSTI");

                    day3_1st.setText("DBMS");
                    day3_2nd.setText("PM");
                    day3_3rd.setText("ELEC");
                    day3_4th.setText("ADP");
                    day3_5th.setText("LATMATH");
                    day3_6th.setText("CONSTI");

                    day4_1st.setText("CN");
                    day4_2nd.setText("DBMS");
                    day4_3rd.setText("PROFENG");
                    day4_4th.setText("PROFENG");
                    day4_5th.setText("MATH");
                    day4_6th.setText("CN");

                    day5_1st.setText("MATH");
                    day5_2nd.setText("CN");
                    day5_3rd.setText("LATMATH");
                    day5_4th.setText("ELEC");
                    day5_5th.setText("LAB");
                    day5_6th.setText("LAB");

                    Toast.makeText(getApplicationContext(), "Semester 4 Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // register all the UI widgets with their
        // appropriate IDs
        // button to open the material date picker dialog
        mPickStartDateButton = findViewById(R.id.start_date_picker_btn);
        mShowSelectedStartDateText = findViewById(R.id.start_date);
        mPickEndDateButton = findViewById(R.id.end_date_picker_btn);
        mShowSelectedEndDateText = findViewById(R.id.End_date);

        //Pick Date

        DatePickerDialog.OnDateSetListener date_ =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };
        DatePickerDialog.OnDateSetListener date_1 =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel_();
            }
        };

        mPickStartDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TimeTableActivity.this,date_,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mPickEndDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(TimeTableActivity.this,date_,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // create the instance of the calendar to set the
        // bounds
//        Calendar calendar1 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        // from calendar object get the AUGUST month
//        long today = MaterialDatePicker.todayInUtcMilliseconds();
//        calendar1.setTimeInMillis(today);
//        calendar2.setTimeInMillis(today);
//
//
//        // create the instance of the CalendarConstraints
//        // Builder
//        CalendarConstraints.Builder calendarConstraintBuilder1 = new CalendarConstraints.Builder();
//        calendarConstraintBuilder1.setValidator(new DateValidatorWeekdays());
//        calendarConstraintBuilder1.setStart(today);
//        calendarConstraintBuilder1.setOpenAt(today);
//
//        CalendarConstraints.Builder calendarConstraintBuilder2 = new CalendarConstraints.Builder();
//        calendarConstraintBuilder2.setValidator(new DateValidatorWeekdays());
//        calendarConstraintBuilder2.setStart(today);
//        calendarConstraintBuilder2.setOpenAt(today);
//
//        // instantiate the Material date picker dialog
//        // builder
//        final MaterialDatePicker.Builder<Long> materialDatePickerBuilder1 = MaterialDatePicker.Builder.datePicker();
//        materialDatePickerBuilder1.setTitleText("SELECT A STARTING DATE");
//        materialDatePickerBuilder1.setCalendarConstraints(calendarConstraintBuilder1.build());
//
//        final MaterialDatePicker.Builder<Long> materialDatePickerBuilder2 = MaterialDatePicker.Builder.datePicker();
//        materialDatePickerBuilder2.setTitleText("SELECT A ENDING DATE");
//        materialDatePickerBuilder2.setCalendarConstraints(calendarConstraintBuilder2.build());
//
//        // now build the material date picker dialog
//        final MaterialDatePicker<Long> materialDatePicker1 = materialDatePickerBuilder1.build();
//        final MaterialDatePicker<Long> materialDatePicker2 = materialDatePickerBuilder2.build();
//        // handle the Select date button to open the
//        // material date picker
//        mPickStartDateButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        materialDatePicker1.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//                    }
//                });
//
//        mPickEndDateButton.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        materialDatePicker2.show(getSupportFragmentManager(), "MATERIAL_DATE_PICKER");
//                    }
//                });
//
//        materialDatePicker1.addOnPositiveButtonClickListener(
//                (MaterialPickerOnPositiveButtonClickListener) selection -> {
//                    // now update the selected date preview
//                    mShowSelectedStartDateText.setText("Start Date : " + materialDatePicker1.getHeaderText());
//                    mPickEndDateButton.setEnabled(true);
//                });
//
//        materialDatePicker2.addOnPositiveButtonClickListener(
//                (MaterialPickerOnPositiveButtonClickListener) selection -> {
//                    // now update the selected date preview
//                    mShowSelectedEndDateText.setText("End Date : " + materialDatePicker2.getHeaderText());
//                });

        // the call back received when the user "sets" the date in the dialog
    }
    private void updateLabel(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        mShowSelectedStartDateText.setText(dateFormat.format(myCalendar.getTime()));
        startdate_txt = dateFormat.format(myCalendar.getTime());
    }
    private void updateLabel_(){
        String myFormat="MM/dd/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        mShowSelectedEndDateText.setText(dateFormat.format(myCalendar.getTime()));
        enddate_lext = dateFormat.format(myCalendar.getTime());
    }
}