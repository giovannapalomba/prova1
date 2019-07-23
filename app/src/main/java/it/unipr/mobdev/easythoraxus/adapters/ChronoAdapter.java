package it.unipr.mobdev.easythoraxus.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import it.unipr.mobdev.easythoraxus.R;

public class ChronoAdapter extends RecyclerView.Adapter<ChronoAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ChronoAdapter.ItemClickListener mClickListener;

    public ChronoAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String interval = mData.get(position);
        holder.myTextView.setText(interval);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.procedure_name);
            itemView.setOnClickListener(this);

            //nascondo la textview descrizione
            View description = itemView.findViewById(R.id.procedure_description);
            description.setVisibility(View.GONE);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public String getItem(int id) {
        return mData.get(id);
    }

    public void setClickListener(ChronoAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
