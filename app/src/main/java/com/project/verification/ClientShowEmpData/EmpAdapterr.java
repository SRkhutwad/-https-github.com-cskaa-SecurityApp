package com.project.verification.ClientShowEmpData;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.verification.EmpPkg.Employee;
import com.project.verification.R;

import java.util.List;

public class EmpAdapterr extends RecyclerView.Adapter<com.project.verification.ClientShowEmpData.EmpAdapterr.EmpViewHolder> {

    Context context;
    List<EmpDataa> arrayList ;

    public EmpAdapterr(Context context, List<EmpDataa> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public com.project.verification.ClientShowEmpData.EmpAdapterr.EmpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_list_layout_client,parent,false);
        return new com.project.verification.ClientShowEmpData.EmpAdapterr.EmpViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull com.project.verification.ClientShowEmpData.EmpAdapterr.EmpViewHolder holder, int position) {
        holder.txName.setText(arrayList.get(position).getEmployeeName());

        holder.relate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClientEmployee.class);
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
