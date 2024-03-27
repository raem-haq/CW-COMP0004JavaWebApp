package uk.ac.ucl.model;

import java.security.Key;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import  java.util.regex.Pattern;


public class DataFrame {

    // Sorting could require you to modify the order of each column to the sorted order
    // However swapping rows would be tedious
    // It is also unnecessary
    // Instead I have a actualToDisplay which maps from the actual order (the order in which rows were added)
    // to the sorted order (which is what is displayed0
    //
    private final HashMap<Integer,Integer> displayToActual = new HashMap<>();
    private final HashMap<Integer,Integer> actualToDisplay = new HashMap<>();

    private final HashMap<String, Column> columns = new HashMap<>();

    public void addColumn(String name) {
        columns.put(name, new Column(name, new ArrayList<String>()));
    }

    public Column getColumn(String name) {
        return columns.get(name);
    }

    public List<String> getColumnNames() {
        return new ArrayList<String>(columns.keySet());
    }

    public int getRowCount() {
        return columns.get(getColumnNames().getFirst()).getSize();
    }

    public String getValue(String columnName, Integer displayRowIndex) {
        int actualRowIndex = displayToActual.get(displayRowIndex);
        return columns.get(columnName).getRowValue(actualRowIndex); // assumes first column has the largest size
    }


    public void putValue(String columnName, Integer displayRowIndex, String value) {
        int actualRowIndex = displayToActual.get(displayRowIndex);
        columns.get(columnName).setRowValue(actualRowIndex, value);
    }

    public List<String> getRecord(Integer displayRowIndex){
        int actualRowIndex = displayToActual.get(displayRowIndex);
        List<String> record = new ArrayList<>();
        for (String name : getColumnNames()){
            record.add(getColumn(name).getRowValue(actualRowIndex));
        }
        return record;
    }

    // assumes new records are entered from the first column
    // true of this program - see DataLoader
    public void addValue(String columnName, String value) {
        columns.get(columnName).addRowValue(value);

        int lastIndex = columns.get(columnName).getLastIndex();
        if (displayToActual.size() != lastIndex + 1){
            displayToActual.put(lastIndex, lastIndex);
        }
        if (actualToDisplay.size() != lastIndex + 1){
            actualToDisplay.put(lastIndex, lastIndex);
        }
        checkIndexMap();
    }

    private void checkIndexMap(){
        for (int i = 0; i < getRowCount(); i++){
            if (!(displayToActual.containsKey(i) && actualToDisplay.containsKey(i)
                    && actualToDisplay.containsValue(i) && displayToActual.containsValue(i)
            && i == displayToActual.get(actualToDisplay.get(i)) && i==actualToDisplay.get(displayToActual.get(i)))){
                int x = 0/0;
            }
        }
    }

    // searches for any occurrence of a keyword
    public List<List<String>> basicSearchFor(String searchString){
        List<List<String>> records = new ArrayList<>();
        for (String name: getColumnNames()){
            List<Integer> indices = getColumn(name).findAllValueIndex(searchString);
            if (!indices.isEmpty()) {
                for (int i : indices) {
                    records.add(getRecord(actualToDisplay.get(i)));
                }
            }
        }
        return records;
    }

    //searches for a keyword in a given field
    public List<List<String>> searchBy(String searchString, String field){
        List<Integer> indices = getColumn(field).findAllValueIndex(searchString);
        List<List<String>> results = new ArrayList<>();
        for (int i : indices){
            results.add(getRecord(actualToDisplay.get(i)));
        }
        return results;
    }

    // ID is assumed to be the only unique value in the table.
    public List<String> searchID(String ID){
        int unsortedIndex = getColumn("ID").findFirstValueIndex(ID);
        return unsortedIndex == -1 ? new ArrayList<String>() : getRecord(actualToDisplay.get(unsortedIndex));
    }

    // sort the table by a field
    public void sortBy(String field, Boolean asc){
        ArrayList<Integer> tempIndexList = new ArrayList<Integer>(getColumn(field).sort(asc));
        for (int i = 0; i < tempIndexList.size(); i++){
            // as said above, we are not rearranging columns to a sorted order.
            // instead defining a map from the actual to sorted order.
            displayToActual.put(i, tempIndexList.get(i));
            actualToDisplay.put(tempIndexList.get(i), i);
        }
        checkIndexMap();
    }

    public HashMap<String, List<String>> getAll(){
        HashMap<String, List<String>> all = new HashMap<>();
        for (String name : getColumnNames()){
            all.put(name, getColumn(name).getAll());
        }
        return all;
    }

    // Check if a value is a valid entry of a field
    private boolean checkValue(String value, String field){
        if (value.isEmpty()){
            return (!field.equals("ID"));
        }

        if (field == "ID"){
            int index = columns.get("ID").findFirstValueIndex(value);
            if (index != -1) { return false;} // ID must be unique
        }

        // some guess-work was involved in the regexes
        boolean valid =
                switch (field){
                    case "BIRTHDATE", "DEATHDATE" -> {
                        yield Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", value);
                    }
                    case "ID" -> {
                        // ID really only contained the character a-f and A-F,
                        // IDs were in the form 8-4-4-4-12
                        yield Pattern.matches("^[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}$", value);
                    }
                    case "SSN" -> {
                        yield Pattern.matches("^\\d{3}-\\d{2}-\\d{4}$", value);
                    }
                    case "DRIVERS" -> {
                        yield Pattern.matches("^[A-Z]\\d{8}", value);
                    }
                    case "PASSPORT" -> {
                        // passport numbers only ever started and ended with X
                        // they also had 7 or 8 digits in the middle
                        // Just in case, I relaxed the conditions to accept any amount of digits in the middle and any character to begin with
                        yield Pattern.matches("X\\d+X", value);
                    }
                    case "PREFIX" ->{
                        yield (value.equals("Mr.")||value.equals("Mrs.")||value.equals("Ms."));
                    }

                    //no data was provided so I accepted everything
                    case "SUFFIX" -> {yield true;}

                    case "MAIDEN", "FIRST", "LAST" -> {
                        yield Pattern.matches("[a-zA-Z]+\\d{3}", value);
                    }
                    case "MARITAL" -> {
                        yield (value.equals("M") || value.equals("S"));
                    }

                    case "ETHNICITY" -> {
                        yield Pattern.matches("^[a-zA-Z]+(?:_[a-zA-Z]+)?$", value);
                    }
                    case "RACE" -> {
                        yield Pattern.matches("[a-z]+", value);
                    }
                    case "GENDER" -> {
                        yield (value.equals("F") || value.equals("M"));
                    }
                    case "CITY" -> {
                        yield Pattern.matches("\\b[A-Za-z\\s]+", value);
                    }
                    case "ADDRESS" -> {
                        yield Pattern.matches("[A-Za-z\\d\\s]+", value);
                    }
                    case "STATE" -> {
                        yield Pattern.matches("\\b[A-Z][a-z]+\\b(?:\\s+[A-Z][a-z]+\\b)*", value);
                    }
                    case "ZIP" -> {
                        yield Pattern.matches("\\d{5}", value);
                    }
                    default -> {
                        yield false;
                    }

                };
        return valid;
    }

    public String addRow(HashMap<String, String> record){
        String message = "";
        boolean valid = true;
        int index = 0;

        // check if record i svalid
        while (index < record.size() && valid){
            String field = getColumnNames().get(index);
            String value = record.get(field).strip();
            valid = checkValue(value, field);
            if (!valid) {
                message = value + " is not a valid " + field + ". ";
                message += "Cannot modify row. ";
                if (field.equals("ID")) {message += "ID might not be unique. ";}
            }
            index++;
        }
        if (!valid) { return message;} // if not, return an error message

        // if so,  add it
        for (String name : getColumnNames()){
            addValue(name, record.get(name));
        }
        return "Added row";
    }


    public void deleteRow(Integer displayIndex){
        // displayIndex is in my project assured to be in bounds
        int index = displayToActual.get(displayIndex);
        for (String name : getColumnNames()){
            columns.get(name).deleteValue(index);
        }
        HashMap<Integer, Integer> newDispToActual = new HashMap<>();
        HashMap<Integer, Integer> newActualToDisplay = new HashMap<>();
        for (int i = 0; i < getRowCount(); i++){
            int keyD = i < displayIndex ? i : i - 1;
            int pairD = displayToActual.get(i) < index ? displayToActual.get(i) : displayToActual.get(i) - 1;
            newDispToActual.put(keyD, pairD);

            int keyA = i < index ? i : i - 1;
            int pairA = actualToDisplay.get(i) < displayIndex ? actualToDisplay.get(i) : actualToDisplay.get(i) - 1;
            newDispToActual.put(keyA, keyD);
        }
    }

    // Similar to addRow
    public String modifyRow(Integer displayedIndex, HashMap<String, String> record){
        String message = "";
        boolean valid = true;
        int index = 0;
        while (index < record.size() && valid){
            String field = getColumnNames().get(index);
            String value = record.get(field).strip();
            valid = checkValue(value, field);
            if (!valid) {
                message = value + " is not a valid " + field + ". ";
                message += "Cannot modify row. ";
                if (field.equals("ID")) {message += "ID might not be unique. ";}
            }
            index++;
        }
        if (!valid) { return message;}

        int realIndex = displayToActual.get(displayedIndex);
        for (String name : getColumnNames()){
            columns.get(name).modify(realIndex, record.get(name));
        }
        return "Modified row";
    }
}
