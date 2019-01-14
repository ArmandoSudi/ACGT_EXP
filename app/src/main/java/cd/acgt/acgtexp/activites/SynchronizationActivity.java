package cd.acgt.acgtexp.activites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cd.acgt.acgtexp.R;
import cd.acgt.acgtexp.entites.Propriete;
import cd.acgt.acgtexp.service.ExpApi;
import cd.acgt.acgtexp.service.ExpApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynchronizationActivity extends AppCompatActivity {

    private static final String TAG = "SynchronizationActivity";
    private ExpApiInterface mApiInterface = ExpApi.getService();

    Button mSynchronizationBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synchronization);

        initView();
    }

    private void initView() {
        mSynchronizationBT = findViewById(R.id.synchronization_bt);
        mSynchronizationBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                synchronize();
            }
        });
    }

    public void synchronize(){
        mApiInterface.addPropriete(getPropriete()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Log.e(TAG, "addPropriete: " + response.body() );
                    } else {
                        Log.e(TAG, "addPropriete response body is null");
                    }
                } else {
                    Log.e(TAG, "addPropriete is not SUCCESSFULL: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(TAG, "addPropriete FAILED: " + t.getMessage());
            }
        });
    }

    public List<Propriete> getPropriete(){

        Date signatureProtocoleAccord = new Date();
        List<Propriete> proprieteList = new ArrayList<>();

        proprieteList.add(new Propriete("10001", "10001","1000531", "pk 10", 1.0, 1.0, "http://www.google.com", signatureProtocoleAccord));
        proprieteList.add(new Propriete("10001", "10001","1000531", "pk 10", 1.0, 1.0, "http://www.google.com", signatureProtocoleAccord));
        proprieteList.add(new Propriete("10001", "10001","1000531", "pk 10", 1.0, 1.0, "http://www.google.com", signatureProtocoleAccord));

        return proprieteList;
    }
}
