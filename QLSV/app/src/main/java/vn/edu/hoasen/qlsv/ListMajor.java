package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class ListMajor extends Activity {
//    ListEnglishMajorAdapter adapterEng;
//    ListITMajorAdapter adapterIT;
//    ListTourMajorAdapter adapaterTour;
    RecyclerView.Adapter adapter;
    TextView txtName;
    Button btnFindMajor;
    List<Student> studentList = new ArrayList<>();
    Integer pos = 0;
    Integer size = 0;
    String Arr[] = {"English","IT","Tourist"};
    Spinner Spin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_major);

        Spin = findViewById(R.id.spinnerMajor);
        btnFindMajor = findViewById(R.id.btnFindMajor);

        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null){
                //split = ,
                String[] item = line.split(",");
                //Read data
                Student studentL = new Student();
                studentL.setNo(item[0]);
                studentL.setID(item[1]);
                studentL.setLastName(item[2]);
                studentL.setFirstName(item[3]);
                studentL.setDateOfBirth(item[4]);
                studentL.setGender(item[5]);
                studentL.setMajor(item[6]);
                studentL.setEnglish(Integer.parseInt(item[7]));
                studentL.setMath(Integer.parseInt(item[8]));
                studentL.setIT(Integer.parseInt(item[9]));
                studentList.add(studentL);
                size++;
            }
        } catch (IOException e){
            Log.wtf("MyActivity","Error reading csv" + line);
            e.printStackTrace();
        }

        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,Arr);
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Spin.setAdapter(SpinnerAdapter);
        Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pos=-1;
            }
        });
        btnFindMajor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView recyclerView = findViewById(R.id.listmajor_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                switch(pos){
                    case 0: adapter = new ListEnglishMajorAdapter(getBaseContext(),studentList); break;
                    case 1: adapter = new ListITMajorAdapter(getBaseContext(),studentList); break;
                    case 2: adapter = new ListTouristMajorAdapter(getBaseContext(),studentList); break;
                }
//                adapter = new ListEnglishMajorAdapter(getBaseContext(),studentList);
                recyclerView.setAdapter(adapter);
            }
        });


    }
}