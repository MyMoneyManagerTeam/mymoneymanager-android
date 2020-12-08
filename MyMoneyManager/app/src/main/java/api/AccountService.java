package api;

import model.accounts.Accounts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AccountService {

    @POST("Account")
    Call<Accounts> ModifyBalance();

    @GET("Account/get")
    Call<Accounts> getAccount(@Header("Authorization") String token);

}
