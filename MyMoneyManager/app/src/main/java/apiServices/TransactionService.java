package apiServices;

import model.transaction.TransactionItem;
import model.transaction.Transactions;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface TransactionService {
    @GET("Transaction/Query")
    Call<Transactions>query(@Header("Authorization") String token);

    @POST("Transaction/Create")
    Call<TransactionItem> create(@Header("Authorization") String token, @Body TransactionItem newTransaction);
}
