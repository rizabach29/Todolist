package pens.lab.app.belajaractivity.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

import pens.lab.app.belajaractivity.R;
import pens.lab.app.belajaractivity.data.model.Task;
import pens.lab.app.belajaractivity.modul.login.LoginContract;

public class RecyclerViewAdapterTodolist extends RecyclerView.Adapter<RecyclerViewAdapterTodolist.MyViewHolder> {
    private static ArrayList<Task> mDataset;
    private static MyClickListener myClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvDescription;
        CheckBox cbList;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTodolistTitle);
            tvDescription = (TextView) itemView.findViewById(R.id.tvTodolistDescription);
            cbList = (CheckBox) itemView.findViewById(R.id.cbList);
            itemView.setOnClickListener(this);
            cbList.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();

            if (view.getId() == R.id.cbList)
                myClickListener.onItemCheck(position,view);
            else
                myClickListener.onItemClick(position, view);
        }
    }

    public RecyclerViewAdapterTodolist(ArrayList<Task> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public RecyclerViewAdapterTodolist.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item_todolist, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvTitle.setText(mDataset.get(position).getTitle());
        holder.tvDescription.setText(mDataset.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public interface MyClickListener {
        void onItemClick(int position, View v);
        void onItemCheck(int position, View v);
    }

}