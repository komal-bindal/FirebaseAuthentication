package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private final int RC_SIGN_IN = 123;

    FirebaseUser auth = FirebaseAuth.getInstance().getCurrentUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Log.i("MainActivity2", "onCreate");
        if (auth != null) {
            startActivity(new Intent(this, MainActivity.class));
            this.finish();
        }
        createSignInIntent();
    }

    private void createSignInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build(), new AuthUI.IdpConfig.PhoneBuilder().build());


               Intent intent =  AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setLogo(R.drawable.ic_launcher_background)
                        .build();

        startActivityForResult(intent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                this.finish();
            } else {
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response == null){
                    Log.d("MainActivity2", "onActivityResult: user cancel sign in request");
                }else{
                    Log.e("MainActivity2", "onActivityResult: " + response.getError());
                }
                createSignInIntent();

            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity2", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity2", "onPause");
//        if (auth != null) {
//            startActivity(new Intent(this, MainActivity.class));
//            this.finish();
//        } else {
//            createSignInIntent();
//        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity2", "onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity2", "onDestroy");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity2", "onStart");

    }

}