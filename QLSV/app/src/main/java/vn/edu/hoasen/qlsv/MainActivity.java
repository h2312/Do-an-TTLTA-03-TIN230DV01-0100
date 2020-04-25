package vn.edu.hoasen.qlsv;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.opencsv.bean.CsvBindByName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student {
    @CsvBindByName
    private String No;
    @CsvBindByName
    private String ID;
    @CsvBindByName
    private String LastName;
    @CsvBindByName
    private String FirstName;
    @CsvBindByName
    private String DateOfBirth;
    @CsvBindByName
    private String Gender;
    @CsvBindByName
    private String Major;
    @CsvBindByName
    private Integer English;
    @CsvBindByName
    private Integer Math;
    @CsvBindByName
    private Integer IT;

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public Integer getEnglish() {
        return English;
    }

    public void setEnglish(Integer english) {
        English = english;
    }

    public Integer getMath() {
        return Math;
    }

    public void setMath(Integer math) {
        Math = math;
    }

    public Integer getIT() {
        return IT;
    }

    public void setIT(Integer IT) {
        this.IT = IT;
    }

    public Double getAverage(){ return (double)(English + Math + IT)/3; }

    public String getGrade() {
        if (getAverage() >= 8)
            return "Giỏi";
        if (getAverage() >= 6.5)
            return "Khá";
        if (getAverage() >= 5)
            return "Trung bình";
        else return "Yếu";}

    public Student(){ }
    public Student(String a, String b, String c, String d, String e, String f, String g, Integer h, Integer i, Integer j){
        No = a;
        ID = b;
        LastName = c;
        FirstName = d;
        DateOfBirth = e;
        Gender = f;
        Major = g;
        English = h;
        Math = i;
        IT = j;
    }

}

class DescAvgComparator implements Comparator<Student> {
    public int compare(Student s1, Student s2) {
        return -(s1.getAverage().compareTo(s2.getAverage()));
    }
}

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this,"Xin chào! Chúc bạn một ngày vui vẻ :)",Toast.LENGTH_LONG).show();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.search_id) {
            Intent intent = new Intent(MainActivity.this,SearchID.class);
            startActivity(intent);
        } else if (id == R.id.search_topn) {
            Intent intent = new Intent(MainActivity.this,SearchTopN.class);
            startActivity(intent);
        } else if (id == R.id.list_all) {
            Intent intent = new Intent(MainActivity.this,ListAll.class);
            startActivity(intent);
        } else if (id == R.id.list_major) {
            Intent intent = new Intent(MainActivity.this,ListMajor.class);
            startActivity(intent);
        } else if (id == R.id.stat_major) {
            Intent intent = new Intent(MainActivity.this,StatMajor.class);
            startActivity(intent);
        } else if (id == R.id.stat_avg) {
            Intent intent = new Intent(MainActivity.this,StatAvg.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
