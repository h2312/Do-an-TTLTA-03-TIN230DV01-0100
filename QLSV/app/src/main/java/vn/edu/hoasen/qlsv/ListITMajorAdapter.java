package vn.edu.hoasen.qlsv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListITMajorAdapter extends RecyclerView.Adapter<ListITMajorAdapter.ViewHolder> {

    private List<Student> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    ListITMajorAdapter(Context context, List<Student> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student stu = mData.get(position);
        if (stu.getMajor().equals("IT"))
        {
            String fname = stu.getFirstName();
            String lname = stu.getLastName();
            holder.myName.setText(lname + " " + fname);
            holder.myGender.setText("Giới tính: " + stu.getGender());
            holder.myDOB.setText("Ngày sinh: " + stu.getDateOfBirth());
            holder.myMajor.setText("Ngành: " + stu.getMajor());
            holder.myEnglish.setText("Điểm English: " +stu.getEnglish());
            holder.myMath.setText("Điểm Math: " +stu.getMath());
            holder.myIT.setText("Điểm IT: " + stu.getIT());
            holder.myAverage.setText(String.valueOf("Điểm trung bình: " + (double) Math.round(stu.getAverage() * 100) / 100));
            holder.myGrade.setText("Học lực: " + stu.getGrade());
        }
        else holder.Layout_hide();
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myName, myDOB, myGender, myMajor, myEnglish, myMath, myIT, myAverage, myGrade;
        final LinearLayout.LayoutParams params;

        ViewHolder(View itemView) {
            super(itemView);
            params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            myName = itemView.findViewById(R.id.tvName);
            myDOB = itemView.findViewById(R.id.tvDOB);
            myGender = itemView.findViewById(R.id.tvGender);
            myMajor = itemView.findViewById(R.id.tvMajor);
            myEnglish = itemView.findViewById(R.id.tvEnglish);
            myMath = itemView.findViewById(R.id.tvMath);
            myIT = itemView.findViewById(R.id.tvIT);
            myAverage = itemView.findViewById(R.id.tvAvg);
            myGrade = itemView.findViewById(R.id.tvGrade);
        }
        private void Layout_hide() {
            params.height = 0;
            itemView.setLayoutParams(params);

        }
    }
}