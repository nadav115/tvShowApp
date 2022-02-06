//package com.example.tvshowapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.TextView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import java.util.ArrayList;
//import java.util.Random;
//import android.os.Bundle;
//
//public class MainActivity extends AppCompatActivity {
//// need to add implements here
//
//    private LoginFragment loginFragment;
//    //private WelcomeFragment welcomeFragment;
//    private SignUpFragment signUpFragment;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        findViewsId();
//        initFragmentClasses();
//        initCallBacks();
//
//        displayFragment(welcomeFragment);
//
//
//    }
//
//    private void initCallBacks() {
//        //welcomeFragment.initLogInRegisterCallBack(this);
//        signUpFragment.initNewAccountCallBack(this);
//        //welcomeFragment.initOpenParkingActivityCallBack(this);
//        loginFragment.initOpenParkingActivityCallBack(this);
//    }
//
//    private void initFragmentClasses() {
//        loginFragment = new LoginFragment();
//        welcomeFragment = new WelcomeFragment();
//        signUpFragment = new RegisterFragment();
//
//    }
//
//    private void displayFragment(Fragment fragment) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().setCustomAnimations(
//                R.anim.slide_in,  // enter
//                R.anim.fade_out,  // exit
//                R.anim.fade_in,   // popEnter
//                R.anim.slide_out  // popExit
//        )
//                .replace(R.id.frameLayout, fragment, null)
//                .setReorderingAllowed(true)
//                .addToBackStack(null)
//                .commit();
//    }
//
//    @Override
//    public void onBackPressed() {
//
//        int count = getSupportFragmentManager().getBackStackEntryCount();
//        //when have one fragment in backStack
//        if (count == 1) {
//            finish();
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    private void findViewsId() {
//
//    }
//
//    @Override
//    public void logInPressBtnCallBack() {
//        displayFragment(loginFragment);
//    }
//
//    @Override
//    public void registerPressCallBack() {
//        displayFragment(signUpFragment);
//
//    }
//
//    @Override
//    public void newAccountRegisterCallBack() {
//        Intent intent = new Intent(FirstActivity.this, ParkingActivity.class);
//        startActivity(intent);
//        finish();
//    }
//}