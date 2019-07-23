package it.unipr.mobdev.easythoraxus.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import it.unipr.mobdev.easythoraxus.R;
import it.unipr.mobdev.easythoraxus.models.ProcedureDescriptor;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<ProcedureDescriptor> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // costruttore
    public MyListAdapter(Context context, List<ProcedureDescriptor> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.rv_row, parent, false);
        return new ViewHolder(view);
    }

    // bind dei dati in ogni riga
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProcedureDescriptor procedureDescriptor = mData.get(position);
        holder.myTextViewName.setText(procedureDescriptor.getName());
        holder.myTextViewDescription.setText(procedureDescriptor.getDescription());
    }

    // numero totale righe
    @Override
    public int getItemCount() {
        return mData.size();
    }

    //"A ViewHolder describes an item view and metadata about its place within the RecyclerView."
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextViewName;
        TextView myTextViewDescription;

        ViewHolder(View itemView) {
            super(itemView);
            myTextViewName = itemView.findViewById(R.id.procedure_name);
            myTextViewDescription = itemView.findViewById(R.id.procedure_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }

    }


    public ProcedureDescriptor getItem(int id) {
        return mData.get(id);
    }

    // cattura eventi
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // risponde agli eventi
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}