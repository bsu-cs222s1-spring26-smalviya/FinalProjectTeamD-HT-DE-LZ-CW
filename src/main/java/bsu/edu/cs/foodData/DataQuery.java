package bsu.edu.cs.foodData;

import bsu.edu.cs.APIApps.USDAClient.USDAListParser;
import bsu.edu.cs.APIApps.USDAClient.USDAParser;
import bsu.edu.cs.user.User;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataQuery {
    private FoodItem item;
    //These will be hard coded to be the test ones for now;
    private String userLogString;
    //Maybe it would be better to link this stuff to the user or something
    //I want this to display the data from a specific item that the user is looking at,
    //I have the item variable stored here locally so if we need to, we can call different things without
    //having to mention the item as a parameter every time
    private USDAListParser listParser = new USDAListParser();
    public DataQuery(int userID){
        setUserLogString(userID);
    }

    //This is for our test cases really
    public DataQuery(String fileName){
        userLogString = fileName;
    }

    public String displayFoodData() throws JSONException {
        return "Calories: " + (int)item.getCalories() + " kCal\n" +
                "Protein: " + item.getProtein() + " g\n" +
                "Carbs: " + item.getCarbs() + " g\n" +
                "Fiber: " + item.getFiber() + " g\n" +
                "Sugar: " + item.getSugar() + " g\n" +
                "Saturated Fat: " + Double.toString(item.getSatFat()) + " g\n" +
                "Unsaturated Fat: " + Double.toString(item.getUnSatFat()) + " g\n" +
                "Cholesterol: " + item.getCholesterol() + " mg\n" +
                "Potassium: " + item.getPotassium() + " mg\n" +
                "Calcium: " + item.getCalcium() + " mg\n" +
                "Iron: " + item.getIron() + " mg";
    }
    //Grabs the user log of food for the day mentioned and then returns a string of it.
    public String grabUserLogForDay(int month, int day, int year) {

        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            br.readLine(); // skip header

            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int m = Integer.parseInt(data[0]);
                int d = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);

                if (m == month && d == day && y == year) {

                    this.item = new FoodItem(Integer.parseInt(data[5]));

                    return String.format(
                            "Time: %s"+
                            "%s calories,\n" +
                            "%s postassium,\n" +
                            "%s iron,\n" +
                            "%s unsaturated fat,\n" +
                            "%s saturated fat,\n"+
                            "%s protein,\n" +
                            "%s calcium,\n" +
                            "%s sugar,\n" +
                            "%s fiber,\n" +
                            "%s carbs,\n" +
                            "%s cholesterol",
                            data[3],capitalize(data[4]),
                            data[6],data[7],data[8],data[9],data[10],
                            data[11],data[12],data[13],data[14],data[15],data[16]
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void logFoodItem(int month, int day, int year, FoodItem newItem) {
        // Check if file needs a header written first
        boolean needsHeader = false;
        try (BufferedReader br = new BufferedReader(new FileReader(userLogString))) {
            needsHeader = br.readLine() == null;
        } catch (IOException e) {
            needsHeader = true;
        }

        try (FileWriter fw = new FileWriter(userLogString, true)) {
            if (needsHeader) {
                fw.write("month,day,year,time,name,fdcID,calories,potassium,iron,unsatFat,satFat,protein,calcium,sugar,fiber,carbs,cholesterol\n");
            }

            LogTime timer = new LogTime();
            String currentTime = timer.getCurrentTime();

            fw.write(String.format("%d,%d,%d,%s,%s,%d,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f\n",
                    month, day, year,
                    currentTime,
                    newItem.getName(),
                    newItem.getFdcID(),
                    newItem.getCalories(),
                    newItem.getPotassium(),
                    newItem.getIron(),
                    newItem.getUnSatFat(),
                    newItem.getSatFat(),
                    newItem.getProtein(),
                    newItem.getCalcium(),
                    newItem.getSugar(),
                    newItem.getFiber(),
                    newItem.getCarbs(),
                    newItem.getCholesterol()
            ));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFoodItem(int fdcID) throws JSONException, IOException {item = new FoodItem(fdcID);}

    public String getUserLogString(){
        return userLogString;
    }
    private void setUserLogString(int userID) {
        userLogString = String.format("src/main/resources/UserData/logs/%dLogData.csv",userID);
    }

    private String capitalize(String str) {
        return str.substring(0,1).toUpperCase() + str.substring(1);
    }

    public String searchFood(String foodName) throws JSONException, IOException {
        listParser.searchForFoods(foodName);
        String listOfFood = "";
        for(int i = 1; i<=5; i++){
            listOfFood.concat(String.format("%d:\nName: %s\nBrand: %s\nCalories per serving:%d\n\n",
                    i,listParser.parseForNameofFood(i),listParser.parseForBrandNameOfFood(i),
                    (int)listParser.parseForCaloriesOfFood(i)));
        }
        return listOfFood;
    }
    public int getFoodID(int choice) throws JSONException {
        return (int)listParser.parseForFDCID(choice);

    }
}
