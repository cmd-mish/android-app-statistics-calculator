package fi.arcada.sos22_1_raknare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Deklarerar variabler
    TextView textViewOutput;
    EditText editTextName, editTextValue;
    String textOut = "";

    // Skapar en ArrayList med värden och DataItem objekt
    ArrayList<Double> dataset = new ArrayList<>();
    ArrayList<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiserar variabler
        textViewOutput = findViewById(R.id.textViewOutput);
        editTextName = findViewById(R.id.editTextName);
        editTextValue = findViewById(R.id.editTextValue);

        dataItems = Statistics.getSampleDataset();
        dataset = Statistics.getValues(dataItems);

        for (Double data: dataset) {
            textOut += data + ", ";
        }

       /* for (DataItem item: dataItems) {
            textOut += item.getName() + " " + item.getValue() + "\n";
        } */

        textViewOutput.setText(textOut);
    }

    public void calculate(View view) {
        String calcOutput = String.format("Minimivärde: %.2f\nMaximivärde: %.2f",
                Statistics.getMin(dataItems),
                Statistics.getMax(dataItems)
        );

        textViewOutput.setText(calcOutput);
    }
}