package com.cmpundhir.cm.butterknifedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.emailEDit)
    TextInputEditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.txtMessage)
    TextView txtMesaage;
    @BindString(R.string.email)
    String regEmail;
    @BindString(R.string.pass)
    String regPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.button)
    public void login(View view){
        String email,pass;
        email = editText1.getText().toString();
        pass = editText2.getText().toString();
        //validation for empty string
        if(TextUtils.isEmpty(email)){
            txtMesaage.setText("");
            editText1.setError("Please enter email");
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(pass)){
            txtMesaage.setText("");
            editText2.setError("Please enter password");
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.equals(regEmail)) {
            if(pass.equals(regPassword)) {
                hideKeyboard(this);
                txtMesaage.setText("Welcome " + email);
            }else{
                txtMesaage.setText("");
                editText2.setError("Incorrect password");
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        }else {
            txtMesaage.setText("");
            editText1.setError("Incorrect email");
            Toast.makeText(this, "Incorrect email", Toast.LENGTH_SHORT).show();
        }
    }
    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
