package com.example.moodindigo.Login;

import android.content.res.ColorStateList;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.moodindigo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login_Fragment extends Fragment implements View.OnClickListener {

    private static View view;
    private static EditText email, password;
    private static TextView forgotPassword, register;
    private static Button loginButton;
    private static CheckBox show_hide_password;
    private static LinearLayout loginLayout;
    private static Animation shakeAnimation;
    private static FragmentManager fragmentManager;

    public Login_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.login, container, false);
        initViews();
        setListeners();
        return view;
    }

    // Initiate Views
    private void initViews(){
        fragmentManager = getActivity().getSupportFragmentManager();

        email = view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);
        loginButton = view.findViewById(R.id.login_button);
        forgotPassword = view.findViewById(R.id.forgot_password);
        register = view.findViewById(R.id.createAccount);
        show_hide_password = view.findViewById(R.id.show_hide_password);
        loginLayout = view.findViewById(R.id.login_layout);

        //Load shake animation
        shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
    }

    // set listeners
    private void setListeners(){
        loginButton.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
        register.setOnClickListener(this);

        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    show_hide_password.setText(R.string.hide_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    show_hide_password.setText(R.string.show_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                checkvalidation();
                break;
            case R.id.forgot_password:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new ForgotPassword_Fragment(), Utils.ForgotPassword_Fragment).commit();
                break;

            case R.id.createAccount:
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, new Register_Fragment(), Utils.Register_Fragment).commit();
                break;
        }
    }

    private void checkvalidation(){
        String getEmailId = email.getText().toString();
        String getPassword = password.getText().toString();

        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);

        if(getEmailId.equals("") || getEmailId.length()==0
        || getPassword.equals("") || getPassword.length()==0){
            loginLayout.startAnimation(shakeAnimation);
            Toast.makeText(getContext(), "Please don't leave any field empty!", Toast.LENGTH_SHORT).show();
        }
        else if(!m.find()){
            Toast.makeText(getContext(), "Please provide correct EmailId!", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getContext(), "Login done!", Toast.LENGTH_SHORT).show();
        }
    }
}
