package com.example.covid_tracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.covid_tracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class FragmentLogin extends Fragment implements View.OnClickListener {

    public static final String TAG = FragmentLogin.class.getName();
    private final String COUNTRY_CODE = "+84";
    private TextView tvSkip;
    private Button btnLogin;
    private TextView txtPhoneNumberLogin;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("SIGN UP - STEP 1/2");
        View view = inflater.inflate(R.layout.layout_fragment_login, container, false);

        tvSkip = (TextView) view.findViewById(R.id.tv_skip);
        btnLogin = view.findViewById(R.id.btn_login);
        txtPhoneNumberLogin = view.findViewById(R.id.txt_phone_number_login);
        bindingAction();

        //Handle on click listener event for tvSkip to navigate to home fragment
        tvSkip.setOnClickListener(this);


        //Should add on click listener event for btnLogin to navigate to password fragment

        return view;
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onBtnLogin);
    }

    private void onBtnLogin(View view) {
        String phone = txtPhoneNumberLogin.getText().toString().trim();
        if (phone.isEmpty() && phone.length() != 10) {
            Toast.makeText(getActivity(), "Invalid phone number", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneNumber = COUNTRY_CODE + phone.substring(1);
        OnVerificationStateChangedCallbacks(phoneNumber);
    }

    private void OnVerificationStateChangedCallbacks(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(getActivity())                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(getActivity(), "Verification failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(verificationId, forceResendingToken);
                                //goToOtpActivity(phone_number, verificationId);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
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

    //Handle text view Skip on click listener
    @Override
    public void onClick(View view) {
        Navigation.findNavController(view).navigate(R.id.tv_skip);
    }
}
