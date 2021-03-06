package apiServices;

import java.util.List;

import model.jar.Jar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JarService {

    @GET("Jar/Query")
    Call<List<Jar>> query(@Header("Authorization") String token);

    @POST("Jar/Create")
    Call<Jar> create(@Header("Authorization") String token, @Body Jar newJar);

    @PUT("Jar/Update")
    Call<Jar> update(@Header("Authorization") String token, @Body Jar updateJar);

    @DELETE("Jar/Delete/{jarId}")
    Call<ResponseBody> delete(@Header("Authorization") String token, @Path("jarId") String jarIdToDelete);

}
