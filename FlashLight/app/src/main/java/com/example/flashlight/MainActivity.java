package com.example.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView toggleButton;
    private ImageView torch;
    boolean hasCameraFlash=false;
    boolean flashOn=false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton=findViewById(R.id.toggleButton);
        torch=findViewById(R.id.torch);
        hasCameraFlash=getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if(hasCameraFlash){
                    if(flashOn){
                        flashOn=false;
                        toggleButton.setImageResource(R.drawable.redd);
                        torch.setImageResource(R.drawable.light0ff);
                        try {
                            flashlightoff();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        flashOn=true;
                        toggleButton.setImageResource(R.drawable.green);
                        torch.setImageResource(R.drawable.light0n);
                        try {
                            flashlighton();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Flash Light Not Available", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flashlighton() throws CameraAccessException {
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId=cameraManager.getCameraIdList()[0];


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId,true);
        }
        Toast.makeText(MainActivity.this, "Flash light on", Toast.LENGTH_SHORT).show();







    } @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void flashlightoff() throws CameraAccessException {
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraId=cameraManager.getCameraIdList()[0];


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraId,false);
        }
        Toast.makeText(MainActivity.this, "Flash light off", Toast.LENGTH_SHORT).show();







    }
}