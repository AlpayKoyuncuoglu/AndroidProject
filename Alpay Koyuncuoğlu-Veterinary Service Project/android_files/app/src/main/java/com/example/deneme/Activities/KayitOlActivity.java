package com.example.deneme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deneme.Models.RegisterPojo;
import com.example.deneme.R;
import com.example.deneme.RestApi.ManagerAll;
import com.example.deneme.Utils.Warnings;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KayitOlActivity extends AppCompatActivity {

    private Button kayitOlButon;
    private EditText registerPassword, registerUserName, registerMailAdress;
    private TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        tanimla();
        registerToUser();
        changeActivity();
    }


    public void tanimla() {
        kayitOlButon = (Button) findViewById(R.id.kayitOlButon);
        registerPassword = findViewById(R.id.registerPassword);
        registerUserName = findViewById(R.id.registerUserName);
        registerMailAdress = findViewById(R.id.registerMailAdress);
        registerText = findViewById(R.id.registerText);
    }

    public void registerToUser() {
        kayitOlButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = registerMailAdress.getText().toString();
                String userN = registerUserName.getText().toString();
                String pass = registerPassword.getText().toString();
                register(mail, userN, pass);
            }
        });
    }

    public void changeActivity() {
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    public void register(String userMailAdres, String userName, String userPass) {
        Call<RegisterPojo> req = ManagerAll.getInstance().kayitol(userMailAdres, userName, userPass);
        req.enqueue(new Callback<RegisterPojo>() {
            @Override
            public void onResponse(Call<RegisterPojo> call, Response<RegisterPojo> response) {
                if (response.body().isTf()) {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(KayitOlActivity.this, GirisYapActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getText(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), Warnings.internetProblemText, Toast.LENGTH_LONG).show();
            }
        });

    }

}
