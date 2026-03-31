package bsu.edu.cs.APIApps.USDAClient;

public class USDAListParser {
    private static String listFile;

    public USDAListParser(String file) {
        listFile = file;
    }
    public USDAListParser(){
        listFile = "src/main/resources/USDAData/itemQueryList.json";
    }

    public int parseForNumberOfPages() {
    }

    public int parseForFDCID() {
    }

    public String parseForNameofFood(int i) {
    }

    public int parseForCurrentPage() {
    }

    public int parseForLastPage() {
    }

    public String parseForBrandNameOfFood(int i) {
    }

    public double parseForCaloriesOfFood(int i) {
    }
}
