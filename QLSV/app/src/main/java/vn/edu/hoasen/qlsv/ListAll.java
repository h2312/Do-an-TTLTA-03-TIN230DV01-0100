package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ListAll extends Activity {
    ListAllAdapter adapter;
    TextView txtName;
    ArrayList<Student> studentList = new ArrayList<>();
    Integer size = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

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

        RecyclerView recyclerView = findViewById(R.id.listall_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListAllAdapter(this,studentList);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        studentList.clear();
        adapter.clear();
        super.onBackPressed();
    }
    @Override
    protected void onRestart() {
        studentList.clear();
        adapter.clear();
        super.onRestart();
    }
}
