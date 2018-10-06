package com.example.fgw.ighackvehicle;

import android.Manifest;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

//import static com.example.fgw.tracker.app.CHANNEL_1_ID;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    LinearLayout Bottomsheet;

    private static final String TAG = MapsActivity.class.getSimpleName();
    private HashMap<String, Marker> mMarkers = new HashMap<>();
    private GoogleMap mMap;
    private EditText locationsearch,bussnumbersearch;
    private ImageView map,search1,search;
    private Button button;
    private ListView listView;
//    private BusKaAdapter busKaAdapter;
    BottomSheetBehavior sheetBehavior;
   public String keyl;
    private FirebaseAuth mAuth;
    private BottomSheetBehavior mBottomSheetBehavior1;
    LinearLayout tapactionlayout;
    View white_forground_view;
    View bottomSheet;
//    ArrayList<ListItem> busList;
    public Double lo,laa;
    private NotificationManagerCompat notificationManager;



//    private void notifyme() {
//
//        Intent activityIntent =new Intent(this,MapsActivity.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(this,0,activityIntent,0);
//        Intent broadcastIntent =new Intent(this,NotificationReceiver.class);
//Toast.makeText(getBaseContext(),"skan.",Toast.LENGTH_SHORT).show();
//        broadcastIntent.putExtra("Toastmessage",0);
//        PendingIntent actionIntent  =PendingIntent.getBroadcast(this,0,broadcastIntent ,PendingIntent.FLAG_UPDATE_CURRENT );
//
//        Notification notification= new NotificationCompat.Builder(this,CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_stat_name)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
//                .setContentIntent(contentIntent)
//                .setAutoCancel(true)
//                .setOnlyAlertOnce(true)
//                .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
//                .build();
//
//
//        notificationManager.notify(1,notification);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
//        locationsearch = findViewById(R.id.editText4);
//        bussnumbersearch = findViewById(R.id.editText);
//        search1 = findViewById(R.id.imageView6);
//        search = findViewById(R.id.imageView7);
        mAuth=FirebaseAuth.getInstance();


//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        tapactionlayout = (LinearLayout) findViewById(R.id.tap_action_layout);
//        bottomSheet = findViewById(R.id.bottom_sheet);
//        mBottomSheetBehavior1 = BottomSheetBehavior.from(bottomSheet);
//        mBottomSheetBehavior1.setPeekHeight(280);
//        mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        mBottomSheetBehavior1.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
//                    tapactionlayout.setVisibility(View.VISIBLE);
//                }
//
//                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
//                    tapactionlayout.setVisibility(View.GONE);
//                }
//
//                if (newState == BottomSheetBehavior.STATE_DRAGGING) {
//                    tapactionlayout.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });
//
//        tapactionlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mBottomSheetBehavior1.getState()==BottomSheetBehavior.STATE_COLLAPSED)
//                {
//                    mBottomSheetBehavior1.setState(BottomSheetBehavior.STATE_EXPANDED);
//                }
//            }
//        });
//
//
//
//
//
////        button = findViewById(R.id.button);
////        Bottomsheet=findViewById(R.id.bottom_sheet);
//        listView = (ListView) findViewById(R.id.listID);
//        busList = new ArrayList<>();

//        sheetBehavior = BottomSheetBehavior.from(Bottomsheet);
//
//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        break;
//                    case BottomSheetBehavior.STATE_EXPANDED: {
//                        button.setText("Close Sheet");
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_COLLAPSED: {
//                        button.setText("Expand Sheet");
//                    }
//                    break;
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });




        notificationManager= NotificationManagerCompat.from(this);

        map =findViewById(R.id.imageView5);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                keyl = mAuth.getUid();
//                requestLocationUpdates();
//                subscribeToUpdates();
//            }
//        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyl = mAuth.getUid();
                Toast.makeText(getApplicationContext(),keyl,Toast.LENGTH_SHORT).show();
                requestLocationUpdates();
                subscribeToUpdates();
//                busList.add(new ListItem("3141","DL 2453"));
//                busList.add(new ListItem("3211","DL 2452"));
//                busList.add(new ListItem("3231","DL 2423"));
//                busList.add(new ListItem("3141","DL 2453"));
//                busList.add(new ListItem("3211","DL 2452"));
//
//
//
//                busKaAdapter = new BusKaAdapter(getApplicationContext(),busList);
//                listView.setAdapter(busKaAdapter);
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Vehicle").child(mAuth.getUid());

        ref.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
//                    notifyme();

//                Location A = new Location("kladms");
//                if(dataSnapshot.child("latitude").getValue()!=null && dataSnapshot.child("longitude").getValue()!=null ){
//
//                Double a = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
//                A.setLongitude(Double.parseDouble(dataSnapshot.child("longitutde").getValue().toString()));
//                A.setLatitude(Double.parseDouble(dataSnapshot.child("latitude").getValue().toString()));
//                String aaa = Double.toString(A.getLatitude());
//                Toast.makeText(getBaseContext(),aaa+"",Toast.LENGTH_SHORT).show();
//                setMarker(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
//                setMarker(dataSnapshot);
//                Toast.makeText(getBaseContext(),dataSnapshot.child("ping").getValue().toString(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(),"RIDER WANTS TO RIDE",Toast.LENGTH_SHORT).show();

//                notifyme();
//                Toast.makeText(getBaseContext(),dataSnapshot.child("longitude").toString() + dataSnapshot.getKey() + dataSnapshot.child("latitude"),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getBaseContext(),dataSnapshot.toString() ,Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MapsActivity.this, NotMain.class));
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
//                setMarker(dataSnapshot);
//                notifyme();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Authenticate with Firebase when the Google map is loaded
        mMap = googleMap;
        try {
            // Customise map styling via JSON file
            boolean success = googleMap.setMapStyle( MapStyleOptions.loadRawResourceStyle( this, R.raw.mapstyles));

            if (!success) {
//                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
//            Log.e(TAG, "Can't find style. Error: ", e);
        }


        mMap.setMaxZoomPreference(16);
//        loginToFirebase();
//        subscribeToUpdates();

    }
    private void requestLocationUpdates(){

        LocationRequest request = new LocationRequest();
        request.setInterval(1000);
        request.setFastestInterval(50);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(this);
//        final String path = getString(R.string.firebase_path) + "/" + keyl;
        final String path = "locationVehicle" + "/" + keyl;

//        Toast.makeText(getBaseContext(),keyl,Toast.LENGTH_SHORT).show();
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permission == PackageManager.PERMISSION_GRANTED) {
            // Request location updates and when an update is
            // received, store the location in Firebase
            client.requestLocationUpdates(request, new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    DatabaseReference   ref = FirebaseDatabase.getInstance().getReference(path);
                    Location location = locationResult.getLastLocation();
//                    latitudecurrent = location.getLatitude();
//                    longitudecurrent = location.getLongitude();
                    if (location != null) {
//                        Log.d(TAG, "location update " + location);
                        ref.setValue(location);

                    }
//                    longitudes.setText( location.getLongitude()+"");
//                    la.setText( location.getLatitude()+"");
                }
            }, null);
        }

    }

//    private void loginToFirebase() {
//        String email = getString(R.string.firebase_email);
//        String password = getString(R.string.firebase_password);
//        // Authenticate with Firebase and subscribe to updates
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(
//                email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    subscribeToUpdates();
//                    Log.d(TAG, "firebase auth success :)");
//                } else {
//                    Log.d(TAG, "firebase auth failed");
//                }
//            }
//        });
//    }

    private void subscribeToUpdates() {
        // Functionality coming next step

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("locationVehicle");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                Location A = new Location("kladms");
//                if(dataSnapshot.child("latitude").getValue()!=null && dataSnapshot.child("longitude").getValue()!=null ){
//
//                Double a = Double.parseDouble(dataSnapshot.child("latitude").getValue().toString());
//                A.setLongitude(Double.parseDouble(dataSnapshot.child("longitutde").getValue().toString()));
//                A.setLatitude(Double.parseDouble(dataSnapshot.child("latitude").getValue().toString()));
//                String aaa = Double.toString(A.getLatitude());
//                Toast.makeText(getBaseContext(),aaa+"",Toast.LENGTH_SHORT).show();
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
//                Toast.makeText(getBaseContext(),dataSnapshot.child("longitude").toString() + dataSnapshot.getKey() + dataSnapshot.child("latitude"),Toast.LENGTH_SHORT).show();
//                Toast.makeText(getBaseContext(),dataSnapshot.toString() ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                setMarker(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.d(TAG, "Failed to read value.", error.toException());
            }
        });
    }
//    private double calculatedistance(double longitude, double latitude, double longitude1, double latitude1)
//    {float distance =
//    }
    private void calculate(Double latA,Double latB,Double lngA,Double lngB)
    {
        Location locationA = new Location("point A");

        locationA.setLatitude(latA);
        locationA.setLongitude(lngA);

        Location locationB = new Location("point B");

        locationB.setLatitude(latB);
        locationB.setLongitude(lngB);

        float distance = locationA.distanceTo(locationB);

    }
    private void setMarker(DataSnapshot dataSnapshot) {
        // Functionality coming next step
        String key = dataSnapshot.getKey();
        HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
        double lat = Double.parseDouble(value.get("latitude").toString());
        double lng = Double.parseDouble(value.get("longitude").toString());
        LatLng location = new LatLng(lat, lng);
        if (!mMarkers.containsKey(key)) {
            Toast.makeText(getBaseContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_LONG).show();
//            if (dataSnapshot.getValue() == mAuth.getUid())
                mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key + ":" + lat + ":" + lng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_stat_name)).position(location)));
//            else
//                mMarkers.put(key, mMap.addMarker(new MarkerOptions().title(key + ":" + lat + ":" + lng).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_name)).position(location)));

        } else {
            mMarkers.get(key).setPosition(location);
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : mMarkers.values()) {
            builder.include(marker.getPosition());
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 300));
    }
    void distance(){
        calculate(1.0,22.0,33.0,44.0);
    }

}