package com.project.verification.securityRegistration;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.project.verification.R;
import com.project.verification.securityHomeScreen.Model;

public class myAdapter extends FirebaseRecyclerAdapter<Model,myAdapter.myviewholder>
{
    Context context;
    public myAdapter(Context context, @NonNull FirebaseRecyclerOptions<Model> options)
    {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Model model)
    {
            holder.name.setText(model.getName());
            holder.address.setText(model.getAddress());
            holder.state.setText(model.getState());
            holder.city.setText(model.getCity());
            holder.pin.setText(model.getPin());
            holder.phone.setText(model.getPhone());
            holder.email.setText(model.getEmail());

            holder.cvv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
//                        Intent intent = new Intent(context, AllEmp.class);
//                        context.startActivity(intent);

                    Intent intent = new Intent(context, AllEmp.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                    context.startActivity(intent);
                }
            });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,address,state,city,pin,phone,email;
        CardView cvv;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            email = (TextView)itemView.findViewById(R.id.email);
            address = (TextView)itemView.findViewById(R.id.address);
            state = (TextView)itemView.findViewById(R.id.state);
            city = (TextView)itemView.findViewById(R.id.city);
            pin = (TextView)itemView.findViewById(R.id.pin);
            phone = (TextView)itemView.findViewById(R.id.phone);

            cvv = itemView.findViewById(R.id.cvv);
        }
    }
}
