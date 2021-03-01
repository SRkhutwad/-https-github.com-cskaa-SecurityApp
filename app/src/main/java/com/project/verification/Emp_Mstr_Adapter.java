package com.project.verification;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.project.verification.EmpPkg.AllEmp;
import com.project.verification.securityHomeScreen.Model;
import com.project.verification.securityRegistration.myAdapter;

public class Emp_Mstr_Adapter extends FirebaseRecyclerAdapter<Emp_Model,Emp_Mstr_Adapter.myviewholder>
{
    Context context;
    public Emp_Mstr_Adapter(Context context, @NonNull FirebaseRecyclerOptions<Emp_Model> options)
    {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull Emp_Mstr_Adapter.myviewholder holder, int position, @NonNull Emp_Model model)
    {
        holder.inEdName.setText(model.getInEdName());
        holder.inEdAge.setText(model.getInEdAge());
        holder.spinner.setText(model.getSpinner());
        holder.status.setText(model.getStatus());
/*
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                        Intent intent = new Intent(context, AllEmp.class);
//                        context.startActivity(intent);

                Intent intent = new Intent(context, ClientHomeActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);
            }
        });*/

    }

    @NonNull
    @Override
    public Emp_Mstr_Adapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emp_mstr_cardview,parent,false);
        return new Emp_Mstr_Adapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
         TextView inEdName,inEdAge,status,spinner;
        CardView cv;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            inEdName = (TextView)itemView.findViewById(R.id.ename);
            inEdAge = (TextView)itemView.findViewById(R.id.eage);
            spinner = (TextView)itemView.findViewById(R.id.crecord);
            status = (TextView)itemView.findViewById(R.id.status);

            cv = itemView.findViewById(R.id.cv);
        }
    }
}
