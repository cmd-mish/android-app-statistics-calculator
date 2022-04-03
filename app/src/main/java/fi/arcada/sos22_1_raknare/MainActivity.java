package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Deklarerar variabler
    TextView textViewOutput;
    EditText editTextName, editTextValue;
    String textOut = "";
    RecyclerView recyclerView;
    DatasetViewAdapter adapter;

    // Skapar ett DataItem objekt
    ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiserar variabler
        textViewOutput = findViewById(R.id.textViewOutput);
        editTextName = findViewById(R.id.editTextName);
        editTextValue = findViewById(R.id.editTextValue);
        recyclerView = findViewById(R.id.recyclerViewDataset);

        dataItems = Statistics.getSampleDataset();

        adapter = new DatasetViewAdapter(dataItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void calculate(View view) {
        String calcOutput = String.format("Minimivärde: %.2f\nMaximivärde: %.2f\nMedelvärde: %.2f" +
                        "\nMedianvärde: %.2f\nTypvärde: %.2f\nStandardavvikelse: %.2f\nNedre kvartil: %.2f" +
                        "\nÖvre kvartil: %.2f\nKvartilavstånd: %.2f",
                Statistics.calcMin(dataItems),
                Statistics.calcMax(dataItems),
                Statistics.calcAverage(dataItems),
                Statistics.calcMedian(dataItems),
                Statistics.calcMode(dataItems),
                Statistics.calcStdev(dataItems),
                Statistics.calcLQ(dataItems),
                Statistics.calcUQ(dataItems),
                Statistics.calcQR(dataItems)
        );
        textViewOutput.setText(calcOutput);
    }

    public void addItem(View view) {
        if (!TextUtils.isEmpty(editTextName.getText().toString()) && !TextUtils.isEmpty(editTextValue.getText().toString())) {
            String name = editTextName.getText().toString();
            double value = Double.parseDouble(editTextValue.getText().toString());
            dataItems.add(new DataItem(name, value));

            // Uppdaterar recycler views innehåll
            adapter = new DatasetViewAdapter(dataItems, this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Reset värden i textfält
            editTextName.setText("");
            editTextValue.setText("");
        }
    }
}