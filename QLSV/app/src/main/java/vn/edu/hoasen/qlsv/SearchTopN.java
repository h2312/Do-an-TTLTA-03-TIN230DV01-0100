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

public class SearchTopN extends Activity {
    Button btnTop;
    SearchTopNAdapter adapter;
    EditText edtN;
    List<Student> studentList = new ArrayList<>();
    List<Student> studentDesc = new ArrayList<>();
    Integer pos = 0;
    Integer size = 0;
    Bundle bundle = new Bundle();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_topn);

        btnTop = findViewById(R.id.btnFindTop);
        edtN = findViewById(R.id.edtN);

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(SearchTopN.this,SearchTopNDisplay.class);
            String a = edtN.getText().toString();
            Integer b = Integer.parseInt(a);
            bundle.putInt("n",b);
            intent.putExtra("MyPackage",bundle);
            startActivity(intent);
            }
        });
    }
    @Override
    protected void onRestart() {
        studentList.clear();
        studentDesc.clear();
        super.onRestart();
    }
    @Override
    public void onBackPressed() {
        studentList.clear();
        studentDesc.clear();
        super.onBackPressed();
    }
}
