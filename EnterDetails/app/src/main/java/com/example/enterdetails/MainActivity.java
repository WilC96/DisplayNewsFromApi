package com.example.enterdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, DatePickerDialog.OnDateSetListener {

    Spinner spinnerArea;
    Spinner spinnerState;
    ArrayList<String> valAreas;
    ArrayList<String> valStates;

    Button date;
    Button submit;

    // date strings
    String onDateSet;
    String areaSet;
    String stateSet;
    String currentDateString = "";

    // spinner strings
    String area;
    String state;

    // declare constants
    public static final String NAME = "com.example.enterdetails.NAME";
    public static final String PHONE = "com.example.enterdetails.PHONE";
    public static final String AREA = "com.example.enterdetails.AREA";
    public static final String ADDRESS = "com.example.enterdetails.ADDRESS";
    public static final String CITY = "com.example.enterdetails.CITY";
    public static final String STATE = "com.example.enterdetails.STATE";
    public static final String ZIP = "com.example.enterdetails.ZIP";
    public static final String EMAIL = "com.example.enterdetails.EMAIL";
    public static final String BD = "com.example.enterdetails.BD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // spinner
        spinnerArea = findViewById(R.id.spnArea);
        spinnerState = findViewById(R.id.spnState);
        valAreas = addValues("Area", "Shiganshina", "Grace field", "y am i here", "Marineford");
        valStates = addValues("State", "State A", "State B", "State C");

        // adapter
        ArrayAdapter<String> areaAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valAreas);
        ArrayAdapter<String> stateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, valStates);

        // view
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set spinner adapters and listen functions
        spinnerArea.setAdapter(areaAdapter);
        spinnerState.setAdapter(stateAdapter);
        spinnerArea.setOnItemSelectedListener(this);
        spinnerState.setOnItemSelectedListener(this);

        Log.d("testing", "instantiated objects");

        // date picker button
        date = (Button) findViewById(R.id.btnBD);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        // submit button
        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDisplay();
            }
        });
    }


    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch(parent.getId()) {
            case R.id.spnArea:
                area = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), area, Toast.LENGTH_SHORT).show();
                break;
            case R.id.spnState:
                state = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), state, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        String area = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), area, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
    }

    public ArrayList<String> addValues(String ... args) {
        ArrayList<String> list = new ArrayList<>();

        for (String str : args) {
            list.add(str);
        }
        Log.d("testing", "generated values");
        return list;
    }

    public void openDisplay() {
        Log.d("testing", "open display");
        TextInputLayout txtName = findViewById(R.id.txtName);
        String name = txtName.getEditText().getText().toString();

        TextInputLayout txtPhone = findViewById(R.id.txtPhone);
        String phone = txtPhone.getEditText().getText().toString();

        TextInputLayout txtAddress = findViewById(R.id.txtAddress);
        String address = txtAddress.getEditText().getText().toString();

        TextInputLayout txtCity = findViewById(R.id.txtCity);
        String city = txtCity.getEditText().getText().toString();

        TextInputLayout txtZip = findViewById(R.id.txtZip);
        String zip = txtZip.getEditText().getText().toString();

        TextInputLayout txtEmail = findViewById(R.id.txtEmail);
        String email = txtEmail.getEditText().getText().toString();


        Intent intent = new Intent(this, DisplayDetails.class);
        intent.putExtra(NAME, name);
        intent.putExtra(PHONE, phone);
        intent.putExtra(ADDRESS, address);
        intent.putExtra(CITY, city);
        intent.putExtra(EMAIL, zip);
        intent.putExtra(ZIP, email);
        intent.putExtra(STATE, state);
        intent.putExtra(AREA, area);
        intent.putExtra(BD, currentDateString);

        Log.d("testing", "just before starting activity");

        startActivity(intent);
    }
}