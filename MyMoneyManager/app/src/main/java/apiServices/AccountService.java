package apiServices;

import model.accounts.Accounts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AccountService {
    @GET("Account/get")
    Call<Accounts> getAccount(@Header("Authorization") String token);

}
