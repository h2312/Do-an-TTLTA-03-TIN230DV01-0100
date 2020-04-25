package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.os.Bundle;
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
import java.util.List;


public class SearchID extends Activity {
    Button btnFind;
    EditText edtID;
    TextView txtIT, txtEng, txtName, txtMath, txtAvg, txtGrade, txtDOB;
    List<Student> studentList = new ArrayList<>();
    Integer pos = 0;
    Integer size = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_id);

        btnFind = findViewById(R.id.btnFind);
        edtID = findViewById(R.id.edtID);
        txtName = findViewById(R.id.txtName);
        txtDOB = findViewById(R.id.txtDOB);
        txtEng = findViewById(R.id.txtEng);
        txtMath = findViewById(R.id.txtMath);
        txtIT = findViewById(R.id.txtIT);
        txtAvg = findViewById(R.id.txtAvg);
        txtGrade = findViewById(R.id.txtGrade);

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

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = edtID.getText().toString();
                for (Integer i = 0; i < size-1; i++)
                if (a.equals(studentList.get(i).getID())) {
                    pos = i;
                    txtName.setText(studentList.get(pos).getLastName()+" "+studentList.get(pos).getFirstName());
                    txtDOB.setText(studentList.get(pos).getDateOfBirth());
                    txtEng.setText(String.valueOf(studentList.get(pos).getEnglish()));
                    txtMath.setText(String.valueOf(studentList.get(pos).getMath()));
                    txtIT.setText(String.valueOf(studentList.get(pos).getIT()));
                    txtAvg.setText(String.valueOf( (double)Math.round(studentList.get(i).getAverage()*100)/100));
                    txtGrade.setText(studentList.get(pos).getGrade());
                    break;
                }
            }
        });
    }
}