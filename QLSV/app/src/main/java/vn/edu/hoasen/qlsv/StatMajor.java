package vn.edu.hoasen.qlsv;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class  StatMajor extends Activity {
    TextView txtIT, txtEng, txtTour;
    List<Student> studentList = new ArrayList<>();
    Integer size = 0;
    Integer it = 0;
    Integer eng = 0;
    Integer tour = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_major);

        txtEng = findViewById(R.id.txtEng);
        txtTour = findViewById(R.id.txtTour);
        txtIT = findViewById(R.id.txtIT);
        GraphView graph = findViewById(R.id.graphMajor);

        InputStream is = getResources().openRawResource(R.raw.data);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );
        String line = "";
        try {
            reader.readLine();
            while ((line = reader.readLine()) != null) {
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
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading csv" + line);
            e.printStackTrace();
        }


        for (Integer i = 0; i < size - 1; i++) {
            if (studentList.get(i).getMajor().equals("IT"))
                it++;
            if (studentList.get(i).getMajor().equals("Tourist"))
                tour++;
            if (studentList.get(i).getMajor().equals("English"))
                eng++;
        }
        txtEng.setText(String.valueOf(eng));
        txtIT.setText(String.valueOf(it));
        txtTour.setText(String.valueOf(tour));

        BarGraphSeries<DataPoint> series1 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(1, eng)
        });
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(2, it)
        });
        BarGraphSeries<DataPoint> series3 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(3, tour)
        });

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1500);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(5);
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getGridLabelRenderer().setHorizontalLabelsVisible(false);
        graph.addSeries(series1);
        graph.addSeries(series2);
        graph.addSeries(series3);
        series1.setDrawValuesOnTop(true);
        series1.setValuesOnTopColor(Color.RED);
        series2.setDrawValuesOnTop(true);
        series2.setValuesOnTopColor(Color.GREEN);
        series3.setDrawValuesOnTop(true);
        series3.setValuesOnTopColor(Color.BLUE);
        series1.setTitle("English");
        series2.setTitle("IT");
        series3.setTitle("Tourist");
        series1.setColor(Color.RED);
        series2.setColor(Color.GREEN);
        series3.setColor(Color.BLUE);
    }
}