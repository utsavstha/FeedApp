package com.example.utsavstha.feedapp.screens.signIn.core;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.screens.signIn.SignInActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignInView {
    private View mView;
    SignInPresenter mSignInPresenter;
    private final PublishSubject<UserDao> loginClick = PublishSubject.create();
    private final PublishSubject<Boolean> registerClick = PublishSubject.create();

    // @BindView(R.id.input_layout_email)
    TextInputLayout emailLayout;
  //  @BindView(R.id.input_layout_password)
    TextInputLayout passwordLayout;

   // @BindView(R.id.et_input_email)
    EditText email;
   // @BindView(R.id.et_input_password)
    EditText password;

   // @BindView(R.id.btn_login)
    Button login;

   /* @OnClick(R.id.btn_login)
    public void onLogin() {
        if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            emailLayout.setError("This field cannot be empty.");
            passwordLayout.setError("This field cannot be empty.");
        }else {
            UserDao userDao = new UserDao();
            user
            loginClick.onNext(true);
        }
    }*/


    public SignInView(SignInActivity signInActivity) {

        FrameLayout parent = new FrameLayout(signInActivity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mView = LayoutInflater.from(signInActivity).inflate(R.layout.activity_sign_in, parent, true);
        ButterKnife.bind(signInActivity, mView);

        emailLayout = mView.findViewById(R.id.input_layout_email);
        passwordLayout = mView.findViewById(R.id.input_layout_password);

        email = mView.findViewById(R.id.et_input_email);
        password = mView.findViewById(R.id.et_input_password);

        login = mView.findViewById(R.id.btn_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    emailLayout.setError("This field cannot be empty.");
                    passwordLayout.setError("This field cannot be empty.");
                }else {
                    UserDao user = new UserDao();
                    user.setmEmail(email.getText().toString().trim());
                    user.setmPassword(password.getText().toString().trim());
                    loginClick.onNext(user);
                }
            }
        });

        mView.findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerClick.onNext(true);
            }
        });



    }

    public View constructView() {
        return mView;
    }

    public Observable<UserDao> loginClicks() {
        return loginClick;
    }

    public Observable<Boolean> registerClicks(){
        return registerClick;
    }

}
