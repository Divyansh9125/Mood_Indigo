package com.example.moodindigo.Login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.moodindigo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotPassword_Fragment extends Fragment implements View.OnClickListener {

    private static EditText email;
    private static TextView back, sendMail;
    private static View view;
    private FragmentManager fragmentManager;

    public ForgotPassword_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.forgot_password, container, false);

        fragmentManager = getActivity().getSupportFragmentManager();
        initViews();
        setListners();
        return view;
    }

    private void initViews(){
        email = view.findViewById(R.id.forgot_password_email);
        back = view.findViewById(R.id.backtologinbutton);
        sendMail = view.findViewById(R.id.send_password);
    }

    private void setListners(){
        back.setOnClickListener(this);
        sendMail.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send_password:
                checkValidation();
                break;
            case R.id.backtologinbutton:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                        .replace(R.id.frameContainer, new Login_Fragment(), Utils.ForgotPassword_Fragment).commit();
        }
    }

    private void checkValidation(){
        String getEmail = email.getText().toString();
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmail);

        if(getEmail.equals("") || getEmail.length() == 0){
            Toast.makeText(getContext(), "Please enter email!", Toast.LENGTH_SHORT).show();
        }else if(!m.find()){
            Toast.makeText(getContext(), "Please enter valid email!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getContext(), "Email sent!", Toast.LENGTH_SHORT).show();
        }
    }
}
