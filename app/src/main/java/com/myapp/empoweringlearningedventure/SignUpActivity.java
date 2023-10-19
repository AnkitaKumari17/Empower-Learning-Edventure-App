package com.myapp.empoweringlearningedventure;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    EditText txtSignUpUser, txtSighUpMobile, txtSignUpEmail, txtSignUpPassword, txtSignUpCategory;
    Button btnRegister;
    TextView login;
    FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setTitle("Sign Up");

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar= new ProgressDialog(SignUpActivity.this);
        txtSignUpUser = findViewById(R.id.textUserName);
        txtSighUpMobile = findViewById(R.id.textPhone);
        txtSignUpEmail= findViewById(R.id.textEmail);
        txtSignUpPassword = findViewById(R.id.textPassword);
        txtSignUpCategory = findViewById(R.id.textUserType);
        btnRegister = findViewById(R.id.btnSignIn);
        login = findViewById(R.id.signUp_LoginBtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUpActivity.this, "Login button has been clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void checkCredentials(){
        String userName, email, password, category, mobile;
        userName = String.valueOf(txtSignUpUser.getText());
        email = String.valueOf(txtSignUpEmail.getText());
        password = String.valueOf(txtSignUpPassword.getText());
        category = String.valueOf(txtSignUpCategory.getText());
        mobile = String.valueOf(txtSighUpMobile.getText());

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(SignUpActivity.this, "Enter Name ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(SignUpActivity.this, "Enter email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(SignUpActivity.this, "Enter password ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(category)){
            Toast.makeText(SignUpActivity.this, "Enter category ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mobile)){
            Toast.makeText(SignUpActivity.this, "Enter Mobile No. ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(!email.contains("@"))
            showError(txtSignUpEmail, "Email is not Valid");
        else if (mobile.length()<10) {
            showError(txtSighUpMobile,"Mobile no. is not correct");
        } else {
            mLoadingBar.setTitle("Registration");
            mLoadingBar.setMessage("Please wait, while check your login credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Account Created",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(SignUpActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
            ;
        }
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }

}