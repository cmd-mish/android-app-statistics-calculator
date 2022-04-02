package fi.arcada.sos22_1_raknare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DatasetViewAdapter extends RecyclerView.Adapter<DatasetViewAdapter.DatasetViewHolder> {

    ArrayList<DataItem> dataset;
    Context context;

    // konstruktormetod
    public DatasetViewAdapter(ArrayList<DataItem> dataset, Context context) {
        this.dataset = dataset;
        this.context = context;
    }


    @NonNull
    @Override
    public DatasetViewAdapter.DatasetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // kopplar recycleView till raderna (inflate)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new DatasetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DatasetViewAdapter.DatasetViewHolder holder, int position) {
        DataItem item = dataset.get(position);

        holder.dataName.setText(item.getName());
        holder.dataValue.setText(String.format("%.2f", item.getValue()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public class DatasetViewHolder extends RecyclerView.ViewHolder {
        TextView dataName;
        TextView dataValue;

        public DatasetViewHolder(@NonNull View itemView) {
            super(itemView);
            dataName = itemView.findViewById(R.id.dataName);
            dataValue = itemView.findViewById(R.id.dataValue);
        }
    }
}
