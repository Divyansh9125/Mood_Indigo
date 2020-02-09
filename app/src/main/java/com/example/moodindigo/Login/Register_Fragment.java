package com.example.moodindigo.Login;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.moodindigo.HomeScreenActivity;
import com.example.moodindigo.R;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register_Fragment extends Fragment implements View.OnClickListener {

    private static EditText fullNmae, emailId, mobileNumber, institute, city, createPsswd, confirmPsswd;
    private static RadioGroup gender, yearStudy;
    private static CheckBox terms;
    private static RadioButton genderButton, yearButton;
    private static Button register, dob_selector;
    private static TextView alreadyRegistered, change_dob, dob;
    private static View view;
    private static boolean isCheckedTerms = false;
    private static FragmentManager fragmentManager;
    private static Calendar mCalendar;
    private static DatePickerDialog mDatePickerDialog;

    public Register_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.register, container, false);
        initviews();
        setListeners();
        return view;
    }

    private void initviews(){
        fragmentManager = getActivity().getSupportFragmentManager();

        fullNmae = view.findViewById(R.id.register_name);
        emailId = view.findViewById(R.id.register_email);
        mobileNumber = view.findViewById(R.id.register_mobile);
        institute = view.findViewById(R.id.register_institute);
        city = view.findViewById(R.id.register_city);
        createPsswd = view.findViewById(R.id.register_password);
        confirmPsswd = view.findViewById(R.id.register_confirm_password);
        gender = view.findViewById(R.id.register_gender);
        yearStudy = view.findViewById(R.id.register_study_year);
        register = view.findViewById(R.id.register_button);
        terms = view.findViewById(R.id.terms_and_conditions);
        alreadyRegistered = view.findViewById(R.id.alreadyRegistered);
        dob_selector = view.findViewById(R.id.dob_selector_register);
        dob = view.findViewById(R.id.dob_register);
        change_dob = view.findViewById(R.id.dob_change_register);
    }

    private void setListeners(){
        alreadyRegistered.setOnClickListener(this);
        register.setOnClickListener(this);
        dob_selector.setOnClickListener(this);
        change_dob.setOnClickListener(this);

        terms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    isCheckedTerms = true;
                }else{
                    isCheckedTerms = false;
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_button:
                checkvalidation();
                break;
            case R.id.alreadyRegistered:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                        .replace(R.id.frameContainer, new Login_Fragment(), Utils.Register_Fragment).commit();
                break;

            case R.id.dob_selector_register:
                mCalendar = Calendar.getInstance();
                int day = mCalendar.get(Calendar.DAY_OF_MONTH);
                int month = mCalendar.get(Calendar.MONTH);
                int year = mCalendar.get(Calendar.YEAR);

                mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, year, month, day);

                dob_selector.setVisibility(View.GONE);
                dob.setVisibility(View.VISIBLE);
                change_dob.setVisibility(View.VISIBLE);

                mDatePickerDialog.show();
                break;

            case R.id.dob_change_register:
                mCalendar = Calendar.getInstance();
                int mday = mCalendar.get(Calendar.DAY_OF_MONTH);
                int mmonth = mCalendar.get(Calendar.MONTH);
                int myear = mCalendar.get(Calendar.YEAR);

                mDatePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, myear, mmonth, mday);
                mDatePickerDialog.show();
                break;
        }
    }

    private void checkvalidation(){

        int genderId = gender.getCheckedRadioButtonId();
        int yearId = yearStudy.getCheckedRadioButtonId();
        genderButton = gender.findViewById(genderId);
        yearButton = yearStudy.findViewById(yearId);

        String getGender = "Hi";
        String getYear = "Divyansh";
        long getNumber = 0;

        if(yearButton != null && genderButton != null){
            getGender = genderButton.getText().toString();
            getYear = yearButton.getText().toString();
        }
        String getName = fullNmae.getText().toString();
        String number = mobileNumber.getText().toString();
        Log.i("Hi", "checkvalidation: "+number.length());
        String getEmailId = emailId.getText().toString();
        if(number.length() == 0) {
            number = "0000000000";
            getNumber = Long.parseLong(number);
        }else{
            getNumber = Long.parseLong(number);
        }
        String getInstitute = institute.getText().toString();
        String getCity = city.getText().toString();
        String getPassword = createPsswd.getText().toString();
        String getConfirmPassword = confirmPsswd.getText().toString();

        Log.i("Hi", "checkvalidation: "+getGender);
        Log.i("Hi", "checkvalidation: "+ getYear);

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if(getName.equals("") || getName.length()==0 || getCity.equals("") || getCity.length()==0 || getConfirmPassword.equals("")
                || getConfirmPassword.length()==0 || getEmailId.equals("") || getEmailId.length()==0 || getGender.equals("") ||
                getGender.length()==0 || getInstitute.equals("") || getInstitute.length() == 0 || getYear.equals("") || getYear.length() == 0
                || Long.toString(getNumber).length() == 0 || getPassword.equals("") || getPassword.length()==0 || getNumber == 0000000000){
            Toast.makeText(getContext(), "Please provide all the credentials!", Toast.LENGTH_SHORT).show();
        }else if(!m.find()){
            Toast.makeText(getContext(), "Please provide valid emailId!", Toast.LENGTH_SHORT).show();
        }else if(!getConfirmPassword.equals(getPassword)){
            Toast.makeText(getContext(), "Both the passwords don't match!", Toast.LENGTH_SHORT).show();
        }else if(!isCheckedTerms){
            Toast.makeText(getContext(), "Please agree to terms and conditions!", Toast.LENGTH_SHORT).show();
        }else if(number.length() != 10){
            Toast.makeText(getContext(), "Mobile Number is invalid!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getContext(), "Registration done!", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                    .replace(R.id.frameContainer, new Login_Fragment(), Utils.Register_Fragment).commit();
        }
    }
}
