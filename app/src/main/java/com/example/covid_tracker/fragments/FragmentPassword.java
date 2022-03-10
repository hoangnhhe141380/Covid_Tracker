package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.covid_tracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class FragmentPassword extends Fragment {

    public static final String TAG = FragmentPassword.class.getName();
    private TextView txtOtp;
    private Button btnLogin;
    private String phoneNumber;
    private String verificationId;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Sign In - Step 2/2");
        View view = inflater.inflate(R.layout.layout_fragment_password, container, false);
        Bundle bundle = this.getArguments();
        if(null!=bundle){
            phoneNumber = bundle.getString("phoneNumber");
            verificationId = bundle.getString("verificationId");
        }
        mAuth = FirebaseAuth.getInstance();
        bindingView(view);
        bindingAction();
        return view;
    }

    private void bindingView(View view){
        txtOtp = view.findViewById(R.id.txt_otp);
        btnLogin = view.findViewById(R.id.btn_login);
    }

    private void bindingAction(){
        btnLogin.setOnClickListener(this::onVerify);
    }

    private void onVerify(View view) {
        String otp = txtOtp.getText().toString();
        if(!(otp.length()==6)){
            //TODO
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            // Update UI
                            //TODO
                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                //TODO
                            }
                        }
                    }
                });
    }


}
