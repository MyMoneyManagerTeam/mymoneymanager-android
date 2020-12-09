package apiServices;

import java.util.List;
import model.transaction.TransactionHistory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TransactionService {
    @GET("Transaction/Query")
    Call<List<TransactionHistory>> query(@Header("Authorization") String token);

    @POST("Transaction/Create")
    Call<TransactionHistory> create(@Header("Authorization") String token, @Body  TransactionHistory newTransaction);
}
