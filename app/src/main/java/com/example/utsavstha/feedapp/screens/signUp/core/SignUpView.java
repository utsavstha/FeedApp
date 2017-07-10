package com.example.utsavstha.feedapp.screens.signUp.core;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.utsavstha.feedapp.R;
import com.example.utsavstha.feedapp.models.UserDao;
import com.example.utsavstha.feedapp.screens.signUp.SignUpActivity;
import com.example.utsavstha.feedapp.utils.PermissionUtils;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by utsavstha on 7/9/17.
 */

public class SignUpView {

    private int MY_PERMISSIONS_REQUEST_READ_FILES;
    private View mView;
    private final PublishSubject<UserDao> mRegisterClick = PublishSubject.create();
    private final PublishSubject<Boolean> mUploacClick = PublishSubject.create();

    private TextInputLayout mNameLayout;
    private TextInputLayout mEmailLayout;
    private TextInputLayout mPasswordLayout;
    private TextInputLayout mConfirmPasswordLayout;

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mConfirmPassword;

    private ImageView mProfileImage;
    private SignUpActivity mSignUpActivity;
    private int READ_REQUEST_CODE = 100;
    private int MY_PERMISSIONS_REQUEST_CAMERA;
    private String mImageUri;
    private Uri uri;

    public SignUpView(SignUpActivity signUpActivity) {
        FrameLayout parent = new FrameLayout(signUpActivity);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        this.mSignUpActivity = signUpActivity;

        mView = LayoutInflater.from(signUpActivity).inflate(R.layout.activity_sign_up, parent, true);

        mNameLayout = mView.findViewById(R.id.input_layout_name);
        mEmailLayout = mView.findViewById(R.id.input_layout_email);
        mPasswordLayout = mView.findViewById(R.id.input_layout_password);
        mConfirmPasswordLayout = mView.findViewById(R.id.input_layout_confirm_password);

        mName = mView.findViewById(R.id.et_input_name);
        mEmail = mView.findViewById(R.id.et_input_email);
        mPassword = mView.findViewById(R.id.et_input_password);
        mConfirmPassword = mView.findViewById(R.id.et_input_confirm_password);

        mProfileImage = mView.findViewById(R.id.iv_upload_image);


        mView.findViewById(R.id.btn_upload_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUploacClick.onNext(true);
            }
        });

        mView.findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(mEmail.getText().toString().trim())
                        || !TextUtils.isEmpty(mName.getText().toString().trim())
                        || !TextUtils.isEmpty(mPassword.getText().toString().trim())
                        || !TextUtils.isEmpty(mConfirmPassword.getText().toString().trim())) {

                    if (mPassword.getText().toString().equalsIgnoreCase(mConfirmPassword.getText().toString())) {
                        UserDao user = new UserDao();
                        user.setmName(mName.getText().toString().trim());
                        user.setmEmail(mEmail.getText().toString().trim());
                        user.setmPassword(mPassword.getText().toString().trim());
                        user.setmImageUrl(mImageUri);
                        mRegisterClick.onNext(user);

                    } else {
                        mPasswordLayout.setError("Passwords do not match.");
                        mConfirmPasswordLayout.setError("Passwords do not match.");
                    }


                } else {
                    mNameLayout.setError("This Field cannot be empty.");
                    mEmailLayout.setError("This Field cannot be empty.");
                    mPasswordLayout.setError("This Field cannot be empty.");
                    mConfirmPasswordLayout.setError("This Field cannot be empty.");
                }


            }
        });


    }

    public View constructView() {
        return mView;
    }

    public Observable<UserDao> registerClicks() {
        return mRegisterClick;
    }

    public Observable<Boolean> uploadClicks() {
        return mUploacClick;
    }

    public void showSelectMenu() {
        final CharSequence[] items = {"Select From Gallery", "Take Picture",};
        AlertDialog.Builder builder = new AlertDialog.Builder(mSignUpActivity);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Select From Gallery")) {
                    selectFromGallery();
                } else {
                    takePhoto();
                }
            }
        });
        builder.show();
    }

    private void takePhoto() {
        Intent intent;
        if (ContextCompat.checkSelfPermission(mSignUpActivity,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(mSignUpActivity,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);

            if (MY_PERMISSIONS_REQUEST_READ_FILES == 1) {
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                mSignUpActivity.startActivityForResult(intent, READ_REQUEST_CODE);
            }
        }
    }

    private void selectFromGallery() {

        Intent intent;

        if (PermissionUtils.checkPermission(mSignUpActivity)) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                intent = new Intent(Intent.ACTION_GET_CONTENT);

            } else {
                intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            }
            intent.setType("image*//*");
            mSignUpActivity.startActivityForResult(intent, READ_REQUEST_CODE);

        }

    }

    public void setImage(Uri uri) {
        mProfileImage.setImageURI(uri);
        mImageUri = uri.getPath();
    }
}
