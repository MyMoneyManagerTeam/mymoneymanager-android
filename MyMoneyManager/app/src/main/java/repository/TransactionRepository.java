package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import apiServices.ApiClient;
import apiServices.TransactionService;
import model.transaction.TransactionHistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {

    private TransactionService getTransactionService(){
        return ApiClient.getClient().create(TransactionService.class);
    }

    public MutableLiveData<List<TransactionHistory>> query(String token){
        final MutableLiveData<List<TransactionHistory>> mutableLiveData = new MutableLiveData<>();

        getTransactionService().query(token).enqueue(new Callback<List<TransactionHistory>>() {
            @Override
            public void onResponse(Call<List<TransactionHistory>> call, Response<List<TransactionHistory>> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<TransactionHistory>> call, Throwable t) {
                Log.i("Transaction","Fail on query: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public LiveData<TransactionHistory> create(String token, TransactionHistory newTransaction){
        final MutableLiveData<TransactionHistory> mutableLiveData = new MutableLiveData<>();

        getTransactionService().create(token, newTransaction).enqueue(new Callback<TransactionHistory>() {
            @Override
            public void onResponse(Call<TransactionHistory> call, Response<TransactionHistory> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TransactionHistory> call, Throwable t) {
                Log.i("Transaction","Fail on create: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }


}
