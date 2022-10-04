package id.ac.polinema.intentexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    TextView tvAbout,tvFullname,tvEmail,tvHomepage;
    Button btnHomepage;
    CircleImageView imageMasuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvAbout = findViewById(R.id.label_about);
        tvFullname = findViewById(R.id.label_fullname);
        tvEmail = findViewById(R.id.label_email);
        tvHomepage = findViewById(R.id.label_homepage);
        imageMasuk = findViewById(R.id.image_profile);

        String hasilAbout = getIntent().getExtras().getString("about");
        String hasilFullname = getIntent().getExtras().getString("fullname");
        String hasilEmail = getIntent().getExtras().getString("email");
        final String hasilHomepage = getIntent().getExtras().getString("homepage");

        tvAbout.setText(hasilAbout);
        tvFullname.setText(hasilFullname);
        tvEmail.setText((hasilEmail));
        tvHomepage.setText((hasilHomepage));


        btnHomepage = findViewById(R.id.button_homepage);
        btnHomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://"+hasilHomepage;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
