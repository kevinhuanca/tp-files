package com.ulp.archivos.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.archivos.R;
import com.ulp.archivos.databinding.ActivityMainBinding;
import com.ulp.archivos.ui.registro.RegistroActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel vm;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        vm.getMMail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etMail.setText(s);
            }
        });

        vm.getMPass().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.etPass.setText(s);
            }
        });

        binding.btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.validar(
                        binding.etMail.getText().toString(),
                        binding.etPass.getText().toString()
                );
            }
        });

        binding.btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), RegistroActivity.class);
                startActivity(intent);
            }
        });

    }
}