package id.ac.polinema.intentexercise;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {

    Button sendData;
    TextInputEditText etFullName,etEmail,etPassword,etConfPassword,etHomepage,etAbout;
    CircleImageView avatarImg;
    ImageButton inputImage;

    private static final String TAG = RegisterActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.text_fullname);
        etEmail = findViewById(R.id.text_email);
        etHomepage = findViewById(R.id.text_homepage);
        etAbout = findViewById(R.id.text_about);

        avatarImg = findViewById(R.id.image_profile);
        inputImage = findViewById(R.id.imageView);

        inputImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI),GALLERY_REQUEST_CODE);
            }
        });

        sendData = findViewById(R.id.button_ok);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindahProfile = new Intent(RegisterActivity.this,ProfileActivity.class);
                pindahProfile.putExtra("fullname",etFullName.getText().toString());
                pindahProfile.putExtra("email",etEmail.getText().toString());
                pindahProfile.putExtra("homepage",etHomepage.getText().toString());
                pindahProfile.putExtra("about",etAbout.getText().toString());
                startActivity(pindahProfile);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "Cancel Input Image", Toast.LENGTH_SHORT).show();
            return;
        }else if(requestCode == GALLERY_REQUEST_CODE){
            if(data != null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    avatarImg.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't  load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG,e.getMessage());
                }
            }
        }
    }
}
