package com.example.manager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.manager.models.Mechanic;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button submit_rating;

    String serviceManUid ;
    DatabaseReference databaseReference;

    Mechanic mechanic;
    float rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        submit_rating = findViewById(R.id.submit_rating);

        ratingBar = findViewById(R.id.rating_bar);

        serviceManUid = getIntent().getStringExtra("serviceManUid");

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Mechanic").child(serviceManUid);


        submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mechanic = dataSnapshot.getValue(Mechanic.class);
                       // float rating = mechanic.getRating();
                        float number = mechanic.getNumberOfRating();

                        float newRating = (rating+ratingBar.getProgress())/(number+1);
                        databaseReference.removeEventListener(this);

                        HashMap<String,Object> update = new HashMap<>();

                        update.put("/Users/Mechanic/"+serviceManUid+"/overallRating",newRating);
                        update.put("/Users/Mechanic/"+serviceManUid+"/numberOfRating",number+1);

                        FirebaseDatabase.getInstance().getReference().updateChildren(update);
                        Intent intent = new Intent(getApplicationContext(), BottomNavigationActivity.class);
                        startActivity(intent);
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
