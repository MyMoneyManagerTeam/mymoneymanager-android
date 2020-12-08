package repository;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import api.ApiClient;
import api.AuthenticateService;
import api.LoginRequest;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateRepository {

    private AuthenticateService getAuthenticateService() {
        return ApiClient.getClient().create(AuthenticateService.class);
    }

    public LiveData<User> authenticate(LoginRequest loginRequest) {
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

        getAuthenticateService().authenticate(loginRequest).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.postValue(response.body());
                } else {
                    mutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Verif", "rate : " + t.getMessage() + " | " + call.request().url());
            }
        });

        return mutableLiveData;
    }

    public LiveData<Object> testToken(String bearer) {
        final MutableLiveData<Object> mutableLiveData = new MutableLiveData<>();

        getAuthenticateService().testToken(bearer).enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful())
                {
                    Log.i("verif","Succes" + response.headers());

                }
                else
                {
                    Log.i("verif","Succes mais pas trop" + response.headers());

                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.i("verif","Fail :" + t.getMessage());

            }
        });
        return mutableLiveData;
    }
}
