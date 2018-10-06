package com.example.fgw.ighackvehicle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class vehicleno extends AppCompatActivity {

//    @BindView(R.id.editText8)
//    EditText editText8;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.imageView4)
    ImageView imageView4;
    public FirebaseAuth mAuth;
    public DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicleno);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance().getReference("PatrolVehicleDriver").child(mAuth.getUid());
        ButterKnife.bind(this);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                db.child("Email").setValue(mAuth.getCurrentUser().getEmail().toString());
                db.child("vehicleID").setValue(editText.getText().toString());
//                db.child("vehicleno").setValue(editText8.getText().toString());
                startActivity(new Intent(vehicleno.this, TrackerActivity.class));
                finish();

            }});
    }


}
