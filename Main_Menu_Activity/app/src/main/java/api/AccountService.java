package api;

import android.accounts.Account;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountService {
    @GET("account")
    Call<Account> getAccount();

}
