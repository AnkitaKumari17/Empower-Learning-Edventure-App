package com.myapp.empoweringlearningedventure;

//import static com.myapp.empoweringlearningedventure.R.id.signIn_RegisterBtn;
import static com.myapp.empoweringlearningedventure.R.id.signUp_LoginBtn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText txtSignInEmail, txtSignInPass, txtUserType;
    Button signIn;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;
  //  TextView register = findViewById(R.id.signIn_RegisterBtn);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Sign In");

        mAuth = FirebaseAuth.getInstance();
        mLoadingBar= new ProgressDialog(SignInActivity.this);
        signIn =findViewById(R.id.btnSignIn);
        txtSignInEmail = findViewById(R.id.textEmail);
        txtSignInPass= findViewById(R.id.textPassword);
        txtUserType = findViewById(R.id.textUserType);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

   /*     register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                Toast.makeText(SignInActivity.this, "Register button has been clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, StartupActivity.class);
                startActivity(intent);
                finish();
            }
        });*/
    }

    private void checkCredentials(){
        String email, password, userType;
        email = String.valueOf(txtSignInEmail.getText());
        password = String.valueOf(txtSignInPass.getText());
        userType = String.valueOf(txtUserType.getText());

        if(email.isEmpty()|| !email.contains("@"))
            showError(txtSignInEmail,"Email is not valid");
        else if (password.isEmpty()) {
            showError(txtSignInPass,"Must enter the Password ");
        } else if (userType.isEmpty()) {
            showError(txtUserType,"Enter user type teacher/Student");
        } else{
            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("Please wait, while check your login credentials");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(SignInActivity.this, "Login Successfully",Toast.LENGTH_SHORT).show();
                        mLoadingBar.dismiss();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

              /*          if(userType.equals("Student")){
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), TeacherMainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }*/

                    }
                    else {
                        Toast.makeText(SignInActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
}