package fi.arcada.sos22_1_raknare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Statistics {

    // Metod för att generera en datamängd att testa med
    public static ArrayList<DataItem> getSampleDataset() {
        Random rnd = new Random();

        ArrayList<DataItem> sampleData = new ArrayList<>();
        String[] names = { "Fili", "Kili", "Balin", "Dwalin", "Ori", "Nori", "Dori", "Gloin", "Oin", "Bifur", "Bofur", "Bombur", "Thorin" };

        for (String name: names) {
            sampleData.add(new DataItem(name, rnd.nextInt(300)+100));
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
}