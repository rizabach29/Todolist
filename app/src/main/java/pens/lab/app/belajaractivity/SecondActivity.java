package pens.lab.app.belajaractivity;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        int counter = intent.getIntExtra("counter", 999);

        TextView tvSecondActivity = findViewById(R.id.tvSecondActivity);
        tvSecondActivity.setText("Nilai counter dari first activity adalah = "+ counter);


    }

}



//base package
//layout base_activity & base_toolbar_layout
//value : attr, colors, style
//drawable : custom_shadow... & ic_log_out


//modul Login
////layout fragment login



/*
TUGAS
1. membuat modul profile
   - enter username& password klick login --> profile
   - tampilkan username dan password dalam profile
2. membuat theme baru
   - buat coller pallet baru
   - buat theme baru
   - panggil theme dalam manifest
   android:textColor="?attr/textWhiteColor"
 */
