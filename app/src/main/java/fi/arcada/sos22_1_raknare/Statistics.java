package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Statistics {

    // Metod för att generera samle datamängd
    public static ArrayList<DataItem> getSampleDataset() {
        ArrayList<DataItem> sampleData = new ArrayList<>();
        String[] names = { "Fili", "Kili", "Balin", "Dwalin", "Ori", "Nori", "Dori", "Gloin", "Oin", "Bifur", "Bofur", "Bombur" };
        double[] ages = { 253.0, 397.0, 382.0, 130.0, 201.0, 252.0, 270.0, 384.0, 163.0, 132.0, 173.0, 248.0 };

        for (int i = 0; i < names.length; i++) {
            sampleData.add(new DataItem(names[i], ages[i]));
        }
        return sampleData;
    }

    // Metoden returnerar bara värden från ArrayList med DataItem:er
    public static ArrayList<Double> getValues(ArrayList<DataItem> dataItems) {
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
    public static double calcMin(ArrayList<DataItem> dataItems) {
        return sortValues(getValues(dataItems)).get(0);
    }

    // maximivärde
    public static double calcMax(ArrayList<DataItem> dataItems) {
        return sortValues(getValues(dataItems)).get(dataItems.size() - 1);
    }

    // medelvärde
    public static double calcAverage(ArrayList<DataItem> dataItems) {
        ArrayList<Double> dataset = getValues(dataItems);
        double sum = 0;

        for (int i = 0; i < dataset.size(); i++) {
            sum += dataset.get(i);
        }

        return sum / dataset.size();
    }

    // median
    public static double calcMedian(ArrayList<DataItem> dataItems) {
        ArrayList<Double> sortedValues = sortValues(getValues(dataItems));
        int mid = sortedValues.size() / 2;
        double median;

        if (sortedValues.size() % 2 == 0) {
            median = (sortedValues.get(mid - 1) + sortedValues.get(mid)) / 2;
        } else {
            median = sortedValues.get(mid);
        }

        return median;
    }

    public static double calcMode(ArrayList<DataItem> dataItems) {
        HashMap<Double, Integer> valueCounts = new HashMap<>();
        ArrayList<Double> values = getValues(dataItems);

        for (double value: values) {
            Integer count = valueCounts.get(value);
            if (count == null) count = 0;

            valueCounts.put(value, count +1);
        }

        int maxCount = 0;
        double modeValue = 0.0;

        for (Double dataValue: valueCounts.keySet()) {
            Integer count = valueCounts.get(dataValue);

            if (count > maxCount) {
                maxCount = count;
                modeValue = dataValue;
            }
        }

        return modeValue;
    }

    public static double calcStdev(ArrayList<DataItem> dataItems) {
        double mean = calcAverage(dataItems);
        ArrayList<DataItem> formattedItems = new ArrayList<>();

        for (int i = 0; i < dataItems.size(); i++) {
            double newValue = (dataItems.get(i).getValue() - mean) * ((dataItems.get(i).getValue() - mean) - mean);
            formattedItems.add(new DataItem(dataItems.get(i).getName(), newValue));
        }

        mean = calcAverage(formattedItems);
        return Math.sqrt(mean);
    }

    public static double calcLQ(ArrayList<DataItem> dataItems) {
        if (dataItems.size() <= 2) { // Om det finns 2 eller få värden i ArrayList returnerar metoden 0
            return 0.0;
        } else {
            ArrayList<Double> sorted = sortValues(getValues(dataItems));
            int index;

            if (dataItems.size() % 2 == 0) {
                index = (int)(dataItems.size() * 0.25);
                return (sorted.get(index - 1) + sorted.get(index)) / 2;
            } else {
                index = (int)(dataItems.size() * 0.25);
                return sorted.get(index);
            }
        }
    }

    public static double calcUQ(ArrayList<DataItem> dataItems) {
        if (dataItems.size() <= 2) { // Om det finns 2 eller få värden i ArrayList returnerar metoden 0
            return 0.0;
        } else {
            ArrayList<Double> sorted = sortValues(getValues(dataItems));
            int index;

            if (dataItems.size() % 2 == 0) {
                index = (int)(dataItems.size() * 0.75);
                return (sorted.get(index - 1) + sorted.get(index)) / 2;
            } else {
                index = (int)(dataItems.size() * 0.75);
                return sorted.get(index);
            }
        }
    }

    public static double calcQR(ArrayList<DataItem> dataItems) {
        return calcUQ(dataItems) - calcLQ(dataItems);
    }
}