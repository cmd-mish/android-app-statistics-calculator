package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Statistics {

    // Metod för att generera en datamängd att testa med
    public static ArrayList<DataItem> getSampleDataset() {
        Random rnd = new Random();

        ArrayList<DataItem> sampleData = new ArrayList<>();
        String[] names = { "Fili", "Kili", "Balin", "Dwalin", "Ori", "Nori", "Dori", "Gloin", "Oin", "Bifur", "Bofur", "Bombur", "Thorin" };
        double[] ages = { 253.0, 397.0, 382.0, 130.0, 201.0, 252.0, 270.0, 384.0, 163.0, 132.0, 173.0, 248.0, 139.0, 139.0, 139.0 };

        for (int i = 0; i < names.length; i++) {
            // sampleData.add(new DataItem(name, rnd.nextInt(300)+100));
            sampleData.add(new DataItem(names[i], ages[i]));
        }
        return sampleData;
    }

    // Metod för att skapa skild ArrayList med endast värdena från DataItems
    public static ArrayList<Double> getDataValues(ArrayList<DataItem> dataItems) {
        // Skapa ny arraylist för Double-värden
        ArrayList<Double> dataValues = new ArrayList<>();
        // Loopa igenom dataItems och spara endast värdena i den nya arrayListen
        for (DataItem item: dataItems) {
            dataValues.add(item.getValue());
        }
        return dataValues;
    }

    // Sorteringsmetod att användas i andra metoder
    public static ArrayList<Double> getSorted(ArrayList<Double> dataset) {
        // Vi måste skapa en kopia av vår datamängd så vi inte sorterar den ursprungliga
        ArrayList<Double> sorted = new ArrayList<>(dataset);
        Collections.sort(sorted);
        return sorted;
    }

    //min
    public static double getMin(ArrayList<DataItem> dataItems) {
        return getSorted(getDataValues(dataItems)).get(0);
    }

    // max
    public static double getMax(ArrayList<DataItem> dataItems) {
        return getSorted(getDataValues(dataItems)).get(dataItems.size() - 1);
    }

    // Medelvärde
    public static double calcMean(ArrayList<Double> dataset) {
        double sum = 0;
        for (int i = 0; i < dataset.size(); i++) {
            sum += dataset.get(i);
        }
        return sum / dataset.size();
    }

    // Median
    public static double calcMedian(ArrayList<Double> dataset) {
        ArrayList<Double> sorted = getSorted(dataset);
        int mid = sorted.size() / 2;
        double median;
        if (sorted.size() % 2 == 0) {
            // Om antalet är jämnt, ta medelvärdet av de två mittersta
            median = (sorted.get(mid-1) + sorted.get(mid)) / 2;
        } else {
            // Om antalet är udda, ta det mittersta värdet
            median = sorted.get(mid);
        }
        return median;
    }

    // Standardavvikelse (Standard Deviation
    public static double calcSD(ArrayList<Double> dataset) {
        double sumDiff = 0;
        double avg = calcMean(dataset);

        // Loopa igenom datamängden
        for (double dataVal: dataset) {
            // Summan av de enskilda skillnaderna i kvadrat
            sumDiff += Math.pow(dataVal-avg,2);
        }
        // Dela summan med antalet värden (räkna ut variansen)
        double variance = sumDiff / dataset.size();
        // Till sist, ta roten av variansen och returnera
        return Math.sqrt(variance);

    }

    // Typvärde (mode)
    public static double calcMode(ArrayList<Double> dataset) {
        HashMap<Double, Integer> valueCount = new HashMap<>();

        for (double dataValue: dataset) {
            Integer count = valueCount.get(dataValue);

            if (count == null) count = 0;
            valueCount.put(dataValue, count + 1);
        }

        int maxCount = 0;
        double modeValue = 0.0;

        for (Double dataValue: valueCount.keySet()) {
            Integer curCount = valueCount.get(dataValue);

            if (curCount > maxCount) {
                maxCount = curCount;
                modeValue = dataValue;
            }
        }

        return modeValue;
    }
}