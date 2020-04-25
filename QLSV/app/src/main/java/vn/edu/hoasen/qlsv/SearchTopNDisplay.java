package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchTopNDisplay extends Activity {
    Button btnTop;
    SearchTopNAdapter adapter;
    TextView txtName;
    EditText edtN;
    List<Student> studentList = new ArrayList<>();
    List<Student> studentDesc = new ArrayList<>();
    Integer pos = 0;
    Integer size = 0;
    Integer pkg = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_topn_display);

        btnTop = findViewById(R.id.btnFindTop);
        edtN = findViewById(R.id.edtN);

        Intent callerIntent = getIntent();
        Bundle packageFromCaller = callerIntent.getBundleExtra("MyPackage");
        pkg = packageFromCaller.getInt("n");

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

        Collections.sort(studentList, new DescAvgComparator());
        for (Student st : studentList)
        {
            studentDesc.add(st);
            pos++;
            if (pos == pkg) break;
        }
        RecyclerView recyclerView = findViewById(R.id.searchtopn_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter = new SearchTopNAdapter(getBaseContext(),studentDesc);
        recyclerView.setAdapter(adapter);

    }
    @Override
    protected void onRestart() {
        super.onRestart();
        studentList.clear();
        studentDesc.clear();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        studentDesc.clear();
        adapter.clear();
    }
}