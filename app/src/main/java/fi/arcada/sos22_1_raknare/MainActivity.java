package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // deklarera variabler
    TextView textMean, textDataOut;
    EditText editTextName;
    EditText editValue;
    RecyclerView recyclerView;

    // Vi skapar en arraylist för vår datamängd
    ArrayList<Double> dataset = new ArrayList<>();
    ArrayList<DataItem> dataItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera variabler
        textMean = findViewById(R.id.textViewMean);
        textDataOut = findViewById(R.id.datasetOut);
        editTextName = findViewById(R.id.editTextName);
        editValue = findViewById(R.id.editValue);
        recyclerView = findViewById(R.id.datasetRecyclerView);

        //dataItems = Statistics.getSampleDataset(); // ArrayList med testdata (flera DataItem-objekt)

        DatasetViewAdapter adapter = new DatasetViewAdapter(dataItems, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataset = Statistics.getDataValues(dataItems); // ArrayList med DataItem-objektens värden

        // Vi skriver tillfälligt ut vår datamängd
        String dataOut = "";
        for (double number: dataset) {
            dataOut += number + " ";
        }
        textDataOut.setText(dataOut);
        System.out.println(dataOut);
        // Vi skriver ut DataItem-datamängden
        dataOut += "\n----\n";
        for (DataItem item: dataItems) {
            dataOut += item.getName() + ":" + item.getValue() + " ";
        }
        textDataOut.setText(dataOut);

    }

    public void btnClick(View view) {
        dataItems.add(new DataItem(editTextName.getText().toString(), Double.parseDouble(editValue.getText().toString())));
    }

    public void calculate(View view) {
        dataset = Statistics.getDataValues(dataItems);

        // %.2f i String.format() avrundar till två decimaler
        String meanStr = String.format("Medelvärde: %.2f\n Median: %.2f\nStd.avvikelse: %.2f\nTypvärde: %.2f",
                Statistics.calcMean(dataset),
                Statistics.calcMedian(dataset),
                Statistics.calcSD(dataset),
                Statistics.calcMode(dataset)
        );

        textMean.setText(meanStr);
    }
}