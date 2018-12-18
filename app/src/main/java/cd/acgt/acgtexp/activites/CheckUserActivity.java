package cd.acgt.acgtexp.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cd.acgt.acgtexp.R;

public class CheckUserActivity extends AppCompatActivity {

    Button mCheckBT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_user);

        initScreen();
    }

    public void initScreen() {
        mCheckBT = findViewById(R.id.check_bt);
        mCheckBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckUserActivity.this, ProjectsActivity.class);
                startActivity(intent);
            }
        });
    }
}
