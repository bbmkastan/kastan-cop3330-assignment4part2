package ucf.assignments;

/*
 *  UCF COP3330 Summer 2021 Assignment 4 Solution
 *  Copyright 2021 Bao Kastan
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    private static String filePath;

    public void setFilePath(String newFilePath) {
        filePath = newFilePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public ArrayList<String> getStrings() throws FileNotFoundException {
        return readFile(filePath);
    }

    public ArrayList<String> readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner in = new Scanner(file);
        ArrayList<String> strings = new ArrayList<>();
        while (in.hasNextLine()) {
            strings.add(in.nextLine());
        }
        return strings;
    }
}
