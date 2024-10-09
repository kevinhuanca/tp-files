package com.ulp.archivos.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ulp.archivos.model.Usuario;
import com.ulp.archivos.request.ApiClient;
import com.ulp.archivos.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> mMail;
    private MutableLiveData<String> mPass;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = getApplication().getApplicationContext();
    }

    public LiveData<String> getMMail() {
        if (mMail == null) {
            mMail = new MutableLiveData<>();
        }
        return mMail;
    }

    public LiveData<String> getMPass() {
        if (mPass == null) {
            mPass = new MutableLiveData<>();
        }
        return mPass;
    }

    public void validar(String mail, String password) {
        if (mail.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Hay campos vacios", Toast.LENGTH_SHORT).show();
            return;
        }
        Usuario usuario = ApiClient.login(context, mail, password);
        if (usuario == null) {
            Toast.makeText(context, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.putExtra("registrado", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        mMail.setValue("");
        mPass.setValue("");
    }

}