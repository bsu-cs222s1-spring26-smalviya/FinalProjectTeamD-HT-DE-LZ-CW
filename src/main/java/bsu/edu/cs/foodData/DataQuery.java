package bsu.edu.cs.foodData;

import bsu.edu.cs.APIApps.USDAClient.USDAListParser;
import bsu.edu.cs.APIApps.USDAClient.USDAParser;
import bsu.edu.cs.user.User;
import org.json.JSONException;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DataQuery {
    private FoodItem item;
    private String userLogString;
    private final USDAListParser listParser = new USDAListParser();

    public DataQuery(int userID) {
        setUserLogString(userID);
    }

    // For test cases
    public DataQuery(String fileName) {
        userLogString = fileName;
    }

    public String displayFoodData() throws JSONException {
        return "Calories: " + (int) item.getCalories() + " kCal\n" +
                "Protein: " + item.getProtein() + " g\n" +
                "Carbs: " + item.getCarbs() + " g\n" +
                "Fiber: " + item.getFiber() + " g\n" +
                "Sugar: " + item.getSugar() + " g\n" +
                "Saturated Fat: " + item.getSatFat() + " g\n" +
                "Unsaturated Fat: " + item.getUnSatFat() + " g\n" +
                "Cholesterol: " + item.getCholesterol() + " mg\n" +
                "Potassium: " + item.getPotassium() + " mg\n" +
                "Calcium: " + item.getCalcium() + " mg\n" +
                "Iron: " + item.getIron() + " mg";
    }

    public String grabUserLogForDay(int month, int day, int year) {
        StringBuilder log = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");

                int m = Integer.parseInt(data[0]);
                int d = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);

                if (m == month && d == day && y == year) {
                    log.append(String.format(
                            "\nTime: %s\n" +
                                    "Name: %s \n" +
                                    "Calories: %s,\n" +
                                    "Potassium: %s,\n" +
                                    "Iron: %s,\n" +
                                    "Unsaturated Fat: %s,\n" +
                                    "Saturated Fat: %s,\n" +
                                    "Protein: %s,\n" +
                                    "Calcium: %s,\n" +
                                    "Sugar: %s,\n" +
                                    "Fiber: %s,\n" +
                                    "Carbs: %s,\n" +
                                    "Cholesterol: %s\n",
                            data[3], data[4],
                            data[6], data[7], data[8], data[9], data[10],
                            data[11], data[12], data[13], data[14], data[15], data[16]
                    ));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return log.toString();
    }

    public void logFoodItem(int month, int day, int year, double userWeight, String measurement) {
        boolean needsHeader = false;
        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            needsHeader = br.readLine() == null;
        } catch (IOException e) {
            needsHeader = true;
        }

        try (FileWriter fw = new FileWriter(userLogString, true)) {
            if (needsHeader) {
                fw.write("month,day,year,time,name,fdcID,calories,potassium,iron,unsatFat,satFat,protein,calcium,sugar,fiber,carbs,cholesterol,weight,measurement\n\n");
            }

            LogTime timer = new LogTime();
            String currentTime = timer.getCurrentTime();
            fw.write(String.format("\n%d,%d,%d,%s,\"%s\",%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%s",
                    month, day, year,
                    currentTime,
                    item.getName(),
                    item.getFdcID(),
                    item.getCalories(),
                    item.getPotassium(),
                    item.getIron(),
                    item.getUnSatFat(),
                    item.getSatFat(),
                    item.getProtein(),
                    item.getCalcium(),
                    item.getSugar(),
                    item.getFiber(),
                    item.getCarbs(),
                    item.getCholesterol(),
                    userWeight,
                    measurement
            ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFoodItem(int fdcID) throws JSONException, IOException {
        item = new FoodItem(fdcID);
    }

    public FoodItem getFoodItem() {
        return item;
    }

    public String getUserLogString() {
        return userLogString;
    }

    private void setUserLogString(int userID) {
        userLogString = String.format("src/main/resources/UserData/logs/%dLogData.csv", userID);
    }

    public String searchFood(String foodName) throws JSONException, IOException {
        listParser.searchForFoods(foodName);
        String listOfFood = "options:\n";
        int numOfOptions = getNumberOfFoodOptions();
        if (numOfOptions < 1) {
            listOfFood = "No foods under that name found.";
        } else {
            for (int i = 1; i <= numOfOptions; i++) {
                listOfFood = listOfFood.concat(String.format("%d:\nName: %s\nBrand: %s\nCalories per serving:%d\n\n",
                        i, listParser.parseForNameofFood(i), listParser.parseForBrandNameOfFood(i),
                        (int) listParser.parseForCaloriesOfFood(i)));
            }
        }
        return listOfFood;
    }
    public String[] searchFoodList(String foodName) throws JSONException, IOException {
        listParser.searchForFoods(foodName);
        String[] listOfFood = new String[0];
        int numOfOptions = getNumberOfFoodOptions();
        if (numOfOptions < 1) { return listOfFood;}
        else {
            for (int i = 1; i <= numOfOptions; i++) { listOfFood[i-1] = listParser.parseForNameofFood(i);}
        }
        return listOfFood;
    }
    public Map<String, Double> getAllWeightEntries() {
        // LinkedHashMap preserves insertion order so dates stay chronological
        Map<String, Double> entries = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(",");
                if (data.length < 19) continue;

                String dateKey = String.format("%s/%s/%s",
                        data[0].trim(), data[1].trim(), data[2].trim());

                // Only store the first entry per day so the graph shows one point per day
                if (!entries.containsKey(dateKey)) {
                    try {
                        double weight = Double.parseDouble(data[17].trim());
                        entries.put(dateKey, weight);
                    } catch (NumberFormatException ignored) {}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public int getNumberOfFoodOptions() {
        try {
            return listParser.getFoodCount();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFoodID(int choice) throws JSONException {
        return (int) listParser.parseForFDCID(choice);
    }

    public String getFoodLabel(int position) throws JSONException {
        String name  = listParser.parseForNameofFood(position);
        String brand = listParser.parseForBrandNameOfFood(position);
        int    cals  = (int) listParser.parseForCaloriesOfFood(position);

        if (brand == null || brand.isEmpty()) {
            return String.format("%s | %d kcal", name, cals);
        }
        return String.format("%s | %s | %d kcal", name, brand, cals);
    }

    public double getTotalCaloriesForDay(int month, int day, int year) {
        double totalCalories = 0.0;

        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            String line;
            br.readLine(); // Skip the header row

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");

                if (parts.length < 7) continue;

                try {
                    int logMonth = Integer.parseInt(parts[0].trim());
                    int logDay   = Integer.parseInt(parts[1].trim());
                    int logYear  = Integer.parseInt(parts[2].trim());

                    if (logMonth == month && logDay == day && logYear == year) {
                        totalCalories += Double.parseDouble(parts[6].trim());
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user log: " + e.getMessage());
        }

        // Keep your print statement for debugging
        System.out.println("Total calories for " + month + "/" + day + "/" + year + ": " + totalCalories);
        return totalCalories;
    }
}
