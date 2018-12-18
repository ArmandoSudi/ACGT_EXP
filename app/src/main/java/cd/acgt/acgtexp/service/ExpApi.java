package cd.acgt.acgtexp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sugar on 7/16/2018
 */
public class ExpApi {

    static Gson gson = new GsonBuilder()
            .setDateFormat("dd/mm/yyyy")
            .create();

    private static final Retrofit RETROFIT = new Retrofit.Builder()
            .baseUrl(Config.API_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    public static final ExpApiInterface SERVICE = RETROFIT.create(ExpApiInterface.class);

    public static ExpApiInterface getService() {
        return SERVICE;
    }
}
