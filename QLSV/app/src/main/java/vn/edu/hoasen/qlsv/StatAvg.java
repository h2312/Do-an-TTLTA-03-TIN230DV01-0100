package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class StatAvg extends Activity {

    TextView txtA, txtB, txtCD, txtF;
    List<Student> studentList = new ArrayList<>();
    Integer size = 0;
    Integer good = 0;
    Integer nice = 0;
    Integer okay = 0;
    Integer bad = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_avg);

        txtF = findViewById(R.id.txtF);
        txtCD = findViewById(R.id.txtCD);
        txtB = findViewById(R.id.txtB);
        txtA = findViewById(R.id.txtA);
        GraphView graph = findViewById(R.id.graphAvg);

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

        for (Integer i = 0; i < size-1; i++){
                if (studentList.get(i).getAverage() >= 8)
                    good++;
                else if (studentList.get(i).getAverage() >= 6.5)
                    nice++;
                else if (studentList.get(i).getAverage() >= 5)
                    okay++;
                else bad++;
            }
        txtA.setText(String.valueOf(good));
        txtB.setText(String.valueOf(nice));
        txtCD.setText(String.valueOf(okay));
        txtF.setText(String.valueOf(bad));

        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, good)
        });
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(2, nice)
        });
        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(3, okay)
        });
        BarGraphSeries<DataPoint> series4 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(4, bad)
        });
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1500);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);
        graph.addSeries(series4);
//        series1.setValueDependentColor(new ValueDependentColor<DataPoint>() {
//            @Override
//            public int get(DataPoint data) {
//                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
//            }
//        });
        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(Color.CYAN);
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.MAGENTA);
        series3.setDrawValuesOnTop(true);
        series3.setValuesOnTopColor(Color.RED);
        series4.setDrawValuesOnTop(true);
        series4.setValuesOnTopColor(Color.BLACK);
        series1.setTitle("Giỏi");
        series2.setTitle("Khá");
        series3.setTitle("TB");
        series4.setTitle("Yếu");
        series1.setColor(Color.CYAN);
        series2.setColor(Color.MAGENTA);
        series3.setColor(Color.YELLOW);
        series4.setColor(Color.BLACK);
    }
}