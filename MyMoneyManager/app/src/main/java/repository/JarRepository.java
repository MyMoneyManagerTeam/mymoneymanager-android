package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import api.ApiClient;
import api.JarService;
import model.User;
import model.jar.Jar;
import okhttp3.ResponseBody;
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
                Log.i("Jar","Fail on query: "+ t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<Jar> create(String token, Jar jar){
        final MutableLiveData<Jar> mutableLiveData = new MutableLiveData<>();

        getJarService().create(token, jar).enqueue(new Callback<Jar>(){
            @Override
            public void onResponse(Call<Jar> call, Response<Jar> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Jar> call, Throwable t) {
                Log.i("Jar","Fail on create: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<Jar> update(String token, Jar updateJar){
        final MutableLiveData<Jar> mutableLiveData = new MutableLiveData<>();

        getJarService().update(token, updateJar).enqueue(new Callback<Jar>() {
            @Override
            public void onResponse(Call<Jar> call, Response<Jar> response) {
                Log.i("Jar", response.body().toString());
            }

            @Override
            public void onFailure(Call<Jar> call, Throwable t) {
                Log.i("Jar","Fail on create: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

    public LiveData<Jar> delete(String token, String jarId){
        final MutableLiveData<Jar> mutableLiveData = new MutableLiveData<>();

        getJarService().delete(token, jarId).enqueue(new Callback<ResponseBody>(){
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("Jar", response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Jar","Fail on delete: " + t.getMessage());
            }
        });
        return mutableLiveData;
    }

}
