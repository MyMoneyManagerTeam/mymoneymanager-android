package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import api.AccountService;
import api.ApiClient;
import model.Accounts;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountRepository {

    private AccountService getAccountService(){return ApiClient.getClient().create(AccountService.class);}

    public LiveData<Accounts> get(final String token)
    {
        final MutableLiveData<Accounts> mutableLiveData = new MutableLiveData<>();

        getAccountService().getAccount(token).enqueue(new Callback<Accounts>() {
           @Override
           public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                  mutableLiveData.postValue(response.body());
           }

           @Override
           public void onFailure(Call<Accounts> call, Throwable t) {
               Log.i("accounts","fail" + t.getMessage());
           }
       });
       return mutableLiveData;
    }
}
