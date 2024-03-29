package com.example.recordbook;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    ArrayList<TransactionModel> transactionModelArrayList;
    TextView button;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> transactionModelArrayList) {
        this.context = context;
        this.transactionModelArrayList = transactionModelArrayList;
    }

    //searchbar...................................................
    public void setfilterlist(ArrayList<TransactionModel> filterlist ){
        this.transactionModelArrayList = filterlist;
        notifyDataSetChanged();
    }
    //...................................................searchbar

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TransactionModel model = transactionModelArrayList.get(position);
        String priority = model.getType();
        if (priority.equals("Expense")) {

//            holder.priority.setBackgroundResource(R.drawable.expense_sign);
            holder.amount.setTextColor(Color.parseColor("#FF0000"));
        } else {
//            holder.priority.setBackgroundResource(R.drawable.deposit_sign);
            holder.amount.setTextColor(Color.parseColor("#5FF103"));
        }
        holder.amount.setText(model.getAmount());
        holder.date.setText(model.getDate());
        holder.note.setText(model.getNote());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,update_transaction.class);
                intent.putExtra("id",transactionModelArrayList.get(position).getId());
                intent.putExtra("amount",transactionModelArrayList.get(position).getAmount());
                intent.putExtra("note",transactionModelArrayList.get(position).getNote());
                intent.putExtra("type",transactionModelArrayList.get(position).getType());
                context.startActivity(intent);
                ((Activity)context).finish();
            }
        });

    }

    @Override
    public int getItemCount() {
        return transactionModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView note, amount, date;

        //        View priority;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note_one);
            amount = itemView.findViewById(R.id.amount_one);
            date = itemView.findViewById(R.id.date_one);
//            priority = itemView.findViewById(R.id.priority_one);
        }
    }
}
