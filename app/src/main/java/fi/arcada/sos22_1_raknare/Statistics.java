package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    // Metod för att generera samle datamängd
    public static ArrayList<DataItem> getSampleDataset() {
        ArrayList<DataItem> sampleData = new ArrayList<>();
        String[] names = {"Fili", "Kili", "Balin", "Dwalin", "Ori", "Nori", "Dori", "Gloin", "Oin", "Bifur", "Bofur", "Bombur", "Thorin"};
        double[] ages = {253.0, 397.0, 382.0, 130.0, 201.0, 252.0, 270.0, 384.0, 163.0, 132.0, 173.0, 248.0, 139.0, 139.0, 139.0};

        for (int i = 0; i < names.length; i++) {
            sampleData.add(new DataItem(names[i], ages[i]));
        }
        return sampleData;
    }

    // Metoden returnerar bara värden från ArrayList med DataItem:er
    public static ArrayList<Double> getValues (ArrayList<DataItem> dataItems) {
        ArrayList<Double> values = new ArrayList<>();

        for (DataItem item: dataItems) {
            values.add(item.getValue());
        }

        return values;
    }

    // Sorteringsmetod: min -> max
    public static ArrayList<Double> sortValues(ArrayList<Double> dataset) {
        ArrayList<Double> sorted = new ArrayList<>(dataset);
        Collections.sort(sorted);
        return sorted;
    }

    // minimivärde
    public static double getMin(ArrayList<DataItem> dataItems) {
        return sortValues(getValues(dataItems)).get(0);
    }

    // maximivärde
    public static double getMax(ArrayList<DataItem> dataItems) {
        return sortValues(getValues(dataItems)).get(dataItems.size() - 1);
    }
}
