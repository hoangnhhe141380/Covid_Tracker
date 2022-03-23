package com.example.covid_tracker.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.covid_tracker.MainActivity;
import com.example.covid_tracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
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
    private Menu optionsMenu;
    private MenuItem menuItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Sign In - Step 2/2");
        View view = inflater.inflate(R.layout.layout_fragment_password, container, false);
        Bundle bundle = this.getArguments();
        if (null != bundle) {
            phoneNumber = bundle.getString("phoneNumber");
            verificationId = bundle.getString("verificationId");
        }
        mAuth = FirebaseAuth.getInstance();
        bindingView(view);
        bindingAction();
        return view;
    }

    private void bindingView(View view) {
        txtOtp = view.findViewById(R.id.txt_otp);
        btnLogin = view.findViewById(R.id.btn_login);
    }

    private void bindingAction() {
        btnLogin.setOnClickListener(this::onVerify);
    }

    private void onVerify(View view) {
        String otp = txtOtp.getText().toString();
        if (otp.length() != 6) {
            //TODO
            return;
        }
        txtOtp.clearFocus();
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
                            setMenu();
                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                            FragmentProfile profile = new FragmentProfile();
                            Bundle bundle = new Bundle();
                            bundle.putString("phoneNumber", user.getPhoneNumber());
                            profile.setArguments(bundle);
                            transaction.replace(R.id.content_frame, profile);
                            transaction.commit();
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

    private void setMenu(){
        MenuItem navigation_login   = MainActivity.menu.findItem(R.id.navigation_login);
        MenuItem navigation_logout  = MainActivity.menu.findItem(R.id.navigation_logout);
        navigation_login.setVisible(false);
        navigation_logout.setVisible(true);
    }
}
