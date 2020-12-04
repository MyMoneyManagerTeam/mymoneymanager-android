package api;

import android.accounts.Account;

import java.util.List;

import model.Accounts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AccountService {

    @GET("Account")
    Call<Accounts> ModifyBalance();

    @GET("Account")
    Call<Accounts> get(@Header("Authorization") String token);

}
