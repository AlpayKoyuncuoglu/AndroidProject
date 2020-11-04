package com.example.deneme.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.deneme.Fragments.HomeFragment;
import com.example.deneme.Fragments.KampanyaFragment;
import com.example.deneme.R;
import com.example.deneme.Utils.ChangeFragments;
import com.example.deneme.Utils.GetSharedPreferences;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences getSharedPreferences;
    private GetSharedPreferences preferences;
    private MaterialButton anasayfaButton;
    private ChangeFragments changeFragments;
    private ImageView cikisYap, aramaYapButon, anasayfaButon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFragment();
        tanimla();
        kontrol();
        action();
    }

    private void getFragment() {
        changeFragments = new ChangeFragments(MainActivity.this);
        changeFragments.change(new HomeFragment());
    }

    public void action() {
        anasayfaButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragments.change(new HomeFragment());
            }
        });
        cikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetSharedPreferences getSharedPreferences=new GetSharedPreferences((MainActivity.this));
                getSharedPreferences.deleteToSession();
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        aramaYapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:05318155443"));
                startActivity(intent);
            }
        });
    }

    public void tanimla() {
        preferences = new GetSharedPreferences(MainActivity.this);
        getSharedPreferences = preferences.getSession();
        anasayfaButon = findViewById(R.id.anasayfaButon);
        aramaYapButon = findViewById(R.id.aramaYapButon);
        cikisYap = findViewById(R.id.cikisYap);


    }

    public void kontrol() {
        if (getSharedPreferences.getString("id", null) == null &&
                getSharedPreferences.getString("mailadres", null) == null &&
                getSharedPreferences.getString("username", null) == null) {
            Intent intent = new Intent(MainActivity.this, GirisYapActivity.class);
            startActivity(intent);
            finish();

        }
    }

}
