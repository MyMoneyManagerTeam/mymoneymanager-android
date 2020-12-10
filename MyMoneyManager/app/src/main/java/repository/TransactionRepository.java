package repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import apiServices.ApiClient;
import apiServices.TransactionService;
import model.transaction.TransactionItem;
import model.transaction.Transactions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {

    private TransactionService getTransactionService(){
        return ApiClient.getClient().create(TransactionService.class);
    }

    public MutableLiveData<Transactions> query(String token){
        final MutableLiveData<Transactions> mutableLiveData = new MutableLiveData<>();

        getTransactionService().query(token).enqueue(new Callback<Transactions>() {
            @Override
            public void onResponse(Call<Transactions> call, Response<Transactions> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Transactions> call, Throwable t) {
                Log.i("transaction","Fail on query: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public LiveData<TransactionItem> create(String token, TransactionItem newTransaction){
        final MutableLiveData<TransactionItem> mutableLiveData = new MutableLiveData<>();

        getTransactionService().create(token, newTransaction).enqueue(new Callback<TransactionItem>() {
            @Override
            public void onResponse(Call<TransactionItem> call, Response<TransactionItem> response) {
                mutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<TransactionItem> call, Throwable t) {
                Log.i("Transaction","Fail on create: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }


}
