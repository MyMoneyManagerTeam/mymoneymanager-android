package apiServices;

import model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthenticateService {

    @POST("Auth/Authenticate")
    Call<User> authenticate(@Body LoginRequest loginRequest);

    @GET("Auth/tokentestlambda")
    Call<Object> testToken(@Header("Authorization") String token);
}
