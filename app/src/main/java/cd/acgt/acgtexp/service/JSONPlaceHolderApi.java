package cd.acgt.acgtexp.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Sugar on 10/24/2018
 */
public interface JSONPlaceHolderApi {
    @GET("posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);
}
