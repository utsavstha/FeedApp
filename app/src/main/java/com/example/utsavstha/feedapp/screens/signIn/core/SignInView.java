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
    private final PublishSubject<UserDao> mLoginClick = PublishSubject.create();
    private final PublishSubject<Boolean> mRegisterClick = PublishSubject.create();

    private TextInputLayout mEmailLayout;

    private TextInputLayout mPasswordLayout;

    private EditText mEmail;

    private EditText mPassword;

    private Button mLogin, mRegister;


    public SignInView(SignInActivity signInActivity) {

        FrameLayout parent = new FrameLayout(signInActivity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        mView = LayoutInflater.from(signInActivity).inflate(R.layout.activity_sign_in, parent, true);


        mEmailLayout = mView.findViewById(R.id.input_layout_email);
        mPasswordLayout = mView.findViewById(R.id.input_layout_password);

        mEmail = mView.findViewById(R.id.et_input_email);
        mPassword = mView.findViewById(R.id.et_input_password);

        mLogin = mView.findViewById(R.id.btn_login);

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(mEmail.getText().toString()) || TextUtils.isEmpty(mPassword.getText().toString())){
                    mEmailLayout.setError("This field cannot be empty.");
                    mPasswordLayout.setError("This field cannot be empty.");
                }else {
                    UserDao user = new UserDao();
                    user.setmEmail(mEmail.getText().toString().trim());
                    user.setmPassword(mPassword.getText().toString().trim());
                    mLoginClick.onNext(user);
                }
            }
        });

        mRegister = mView.findViewById(R.id.btn_register);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRegisterClick.onNext(true);
            }
        });



    }

    public View constructView() {
        return mView;
    }

    public Observable<UserDao> loginClicks() {
        return mLoginClick;
    }

    public Observable<Boolean> registerClicks(){
        return mRegisterClick;
    }

}
