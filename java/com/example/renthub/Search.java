package com.example.renthub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Search extends AppCompatActivity {

    ImageButton imageButtonSearch;

    String[] DivisionsStringVariable;
    String[] SylhetDivisionDistrictStringVariable;
    String[] DhakaDivisionDistrictStringVariable;
    String[] BarishalDivisionDistrictStringVariable;
    String[] MymensinghDivisionDistrictStringVariable;
    String[] KhulnaDivisionDistrictStringVariable;
    String[] RangpurDivisionDistrictStringVariable;
    String[] RajshahiDivisionDistrictStringVariable;
    String[] ChittagongDivisionDistrictStringVariable;

    String[] DhakaDistrictAreaStringVariable;
    String[] ChittagongDistrictAreaStringVariable;
    String[] GazipurDistrictAreaStringVariable;

    String[] RentRangeStringVariable;
    String[] RoomsStringVariable;

    private Spinner DivisionSpinnerVariable;
    private Spinner DistrictSpinnerVariable;
    private Spinner AreaSpinnerVariable;
    private Spinner RentRangeSpinnerVariable;
    private Spinner RoomsSpinnerVariable;


    NavigationView sidenav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    String searchPoint;
    String SelectedDivision;
    private TextView text;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mAuth= FirebaseAuth.getInstance();

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.search);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.homePage:
                        startActivity(new Intent(getApplicationContext(), HomePage.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        return true;

                    case R.id.postAHome:
                        startActivity(new Intent(getApplicationContext(), PostAHome.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


        DivisionsStringVariable=getResources().getStringArray(R.array.DivisionsString);// ei variable e values er declare kora string recieve korbe
        DhakaDivisionDistrictStringVariable=getResources().getStringArray(R.array.DhakaDivisionsDistrictsString);//same
        SylhetDivisionDistrictStringVariable=getResources().getStringArray(R.array.SylhetDivisionDistrictString);//same
        BarishalDivisionDistrictStringVariable=getResources().getStringArray(R.array.BarishalDivisionsDistrictsString);
        MymensinghDivisionDistrictStringVariable=getResources().getStringArray(R.array.MymensinghDivisionsDistrictsString);
        RajshahiDivisionDistrictStringVariable=getResources().getStringArray(R.array.RajshahiDivisionsDistrictsString);
        KhulnaDivisionDistrictStringVariable=getResources().getStringArray(R.array.KhulnaDivisionsDistrictsString);
        RangpurDivisionDistrictStringVariable=getResources().getStringArray(R.array.RangpurDivisionsDistrictsString);
        ChittagongDivisionDistrictStringVariable=getResources().getStringArray(R.array.ChittagongDivisionsDistrictsString);

        RentRangeStringVariable=getResources().getStringArray(R.array.Rent);
        RoomsStringVariable=getResources().getStringArray(R.array.Room);

        DhakaDistrictAreaStringVariable=getResources().getStringArray(R.array.dhakaDisArea);
        ChittagongDistrictAreaStringVariable=getResources().getStringArray(R.array.chittagongDisArea);
        GazipurDistrictAreaStringVariable=getResources().getStringArray(R.array.gazipurDisArea);

        DivisionSpinnerVariable=(Spinner) findViewById(R.id.spinnerDivison); // divison spinner jeta activity_search.xml e ase oita ke variable e set korbe
        DistrictSpinnerVariable =(Spinner) findViewById(R.id.spinnerDistrict);//same
        AreaSpinnerVariable=(Spinner)findViewById(R.id.spinnerArea);
        RentRangeSpinnerVariable=(Spinner)findViewById(R.id.spinnerRentRange);
        RoomsSpinnerVariable=(Spinner)findViewById(R.id.spinnerRooms);

        ArrayAdapter<String> DivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DivisionsStringVariable);// ei adapter division er nam gula ke spinner display layout er maddome adapter e set korbe
        //SelectedDivision=DivisionSpinnerVariable.getSelectedItem().toString();
        ArrayAdapter<String> SylhetDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, SylhetDivisionDistrictStringVariable);//same
        ArrayAdapter<String> DhakaDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DhakaDivisionDistrictStringVariable);//same
        ArrayAdapter<String> BarishalDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, BarishalDivisionDistrictStringVariable);//same
        ArrayAdapter<String> MymensinghDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, MymensinghDivisionDistrictStringVariable);//same
        ArrayAdapter<String> KhulnaDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, KhulnaDivisionDistrictStringVariable);//same
        ArrayAdapter<String> RajshahiDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RajshahiDivisionDistrictStringVariable);//same
        ArrayAdapter<String> RangpurDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RangpurDivisionDistrictStringVariable);//same
        ArrayAdapter<String> ChittagongDivisionAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, ChittagongDivisionDistrictStringVariable);//same
        ArrayAdapter<String> RentRangeAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RentRangeStringVariable);//same
        ArrayAdapter<String> RoomsAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, RoomsStringVariable);//same

        ArrayAdapter<String> DhakaDistrictAreaAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, DhakaDistrictAreaStringVariable);//same
        ArrayAdapter<String> ChittagongDistrictAreaAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay1, R.id.spinnerDisplay1, ChittagongDistrictAreaStringVariable);//same
        ArrayAdapter<String> GazipurDistrictAreaAdapter = new ArrayAdapter<String>(this, R.layout.spinnerdisplay, R.id.spinnerDisplay, GazipurDistrictAreaStringVariable);//same

        RentRangeSpinnerVariable.setAdapter(RentRangeAdapter);
        RoomsSpinnerVariable.setAdapter(RoomsAdapter);
        DivisionSpinnerVariable.setAdapter(DivisionAdapter);// set kora divison gulu spinner e show korbe

        DivisionSpinnerVariable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==2)
                {
                    DistrictSpinnerVariable.setAdapter(DhakaDivisionAdapter);
                    DistrictSpinnerVariable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0) {
                                AreaSpinnerVariable.setAdapter(DhakaDistrictAreaAdapter);
                                searchPoint="";
                                searchPoint= AreaSpinnerVariable.getSelectedItem().toString();
                            }

                            if(position==1) {
                                AreaSpinnerVariable.setAdapter(GazipurDistrictAreaAdapter);
                                searchPoint="";
                                searchPoint= AreaSpinnerVariable.getSelectedItem().toString();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(position==7)
                {
                    DistrictSpinnerVariable.setAdapter(SylhetDivisionAdapter);
                }
                if(position==0)
                {
                    DistrictSpinnerVariable.setAdapter(BarishalDivisionAdapter);
                }
                if(position==1)
                {
                    DistrictSpinnerVariable.setAdapter(ChittagongDivisionAdapter);
                    DistrictSpinnerVariable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position==0) {
                                AreaSpinnerVariable.setAdapter(ChittagongDistrictAreaAdapter);
                                searchPoint = "";
                                searchPoint= AreaSpinnerVariable.getSelectedItem().toString();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(position==3)
                {
                    DistrictSpinnerVariable.setAdapter(KhulnaDivisionAdapter);
                }
                if(position==4)
                {
                    DistrictSpinnerVariable.setAdapter(MymensinghDivisionAdapter);
                }
                if(position==5)
                {
                    DistrictSpinnerVariable.setAdapter(RajshahiDivisionAdapter);
                }
                if(position==6)
                {
                    DistrictSpinnerVariable.setAdapter(RangpurDivisionAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AreaSpinnerVariable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchPoint = AreaSpinnerVariable.getSelectedItem().toString();
                Toast.makeText(Search.this, "Local area is "+searchPoint, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        imageButtonSearch=(ImageButton)findViewById(R.id.imageButtonSearch);
        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData();
                //  startActivity(new Intent(search.this, SearchResults.class));
            }
        });
    }


    private void passData() {

        Intent intent = new Intent(Search.this, SearchResults.class);
        intent.putExtra("message", searchPoint);
        startActivity(intent);
    }
}
