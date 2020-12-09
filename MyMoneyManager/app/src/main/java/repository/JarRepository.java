package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.JarService;
import model.User;
import model.jar.Jar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JarRepository {

    private JarService getJarService(){
        return ApiClient.getClient().create(JarService.class);
    }

    public LiveData<List<Jar>> query(String token){
        final MutableLiveData<List<Jar>> mutableLiveData = new MutableLiveData<>();

        getJarService().query(token).enqueue(new Callback<List<Jar>>() {
            @Override
            public void onResponse(Call<List<Jar>> call, Response<List<Jar>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Jar>> call, Throwable t) {
                Log.i("Jar","fail: "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }
}
