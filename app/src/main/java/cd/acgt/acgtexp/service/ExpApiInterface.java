package cd.acgt.acgtexp.service;

import java.lang.reflect.Type;
import java.util.List;

import cd.acgt.acgtexp.entites.Litige;
import cd.acgt.acgtexp.entites.Paiement;
import cd.acgt.acgtexp.entites.Projet;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.entites.Riverain;
import cd.acgt.acgtexp.entites.TypePropriete;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Sugar on 7/17/2018
 */
public interface ExpApiInterface {

    @GET("projets/")
    Call<List<Projet>> getProjets();

    @GET("/{projet_code}/{lot_code}/proprietes/")
    Call<List<Propriete>> getProprieteByLot(@Path("projet_code") String projetCode, @Path("lot_code") String lotCode);

    @GET("/propriete/{propriete_code}")
    Call<Propriete> getProprieteByCode(@Path("propriete_code") String proprieteCode);

    @GET("/riverain/{riverain_code}")
    Call<Riverain> getRiverainByCode(@Path("riverain_code") String riverainCode);

    @GET("/lot/{lot_code}/riverains/")
    Call<List<Riverain>> getRiverainByLot(@Path("lot_code") String lotCode);

    @GET("/propriete/{propriete_code}/type_proprietes/")
    Call<List<TypePropriete>> getTypeProprieteForPropriete(@Path("propriete_code") String proprieteCode);

    @POST("/addpropriete/")
    Call<String> addPropriete(@Body List<Propriete> proprietes);

    @POST("/type_propriete/add/")
    Call<String> addtypepropriete(@Body List<TypePropriete> typeProprietes);

    @POST("/riverain/add/")
    Call<String> addriverain(@Body List<Riverain> riverains);

    //TODO interface to get litiges
    @GET("/litiges/")
    Call<List<Litige>> getLitiges();

    //TODO interface to get dedommagement


    //TODO interface to get paiement
    @GET("/paiements/")
    Call<List<Paiement>> getPaiements();

    @Multipart
    @POST("retrofit_example/upload")
    Call uploadFile(@Part MultipartBody.Part file, @Part("file")RequestBody name);

}
