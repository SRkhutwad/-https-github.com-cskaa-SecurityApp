package com.project.verification.EmpPkg;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.project.verification.DocumentUpload.AddEmpDocs;
import com.project.verification.R;

import java.text.BreakIterator;
import java.util.List;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.EmpViewHolder> {

    Context context;
    List<EmpData> arrayList ;

    public EmpAdapter(Context context, List<EmpData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_list_layout,parent,false);
        return new EmpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpViewHolder holder, int position) {
        holder.txName.setText(arrayList.get(position).getEmployeeName());

        holder.relate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Employee.class);
                //intent.putExtra("id",arrayList.get(position).getId());
                intent.putExtra("name",arrayList.get(position).getEmployeeName());
                intent.putExtra("age",arrayList.get(position).getEmployeeAge());
                intent.putExtra("salary",arrayList.get(position).getEmployeeSalary());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class EmpViewHolder extends RecyclerView.ViewHolder {
        TextView txName;
        RelativeLayout relate;

        public EmpViewHolder(@NonNull View itemView) {
            super(itemView);
            txName = itemView.findViewById(R.id.txName);
            relate = itemView.findViewById(R.id.relative);
        }
    }
}
