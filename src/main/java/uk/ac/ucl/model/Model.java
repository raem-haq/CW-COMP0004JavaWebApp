package uk.ac.ucl.model;

import java.util.*;


public class Model
{
  private DataFrame dataFrame;

  public Model(String fileName) {
    DataLoader dataLoader = new DataLoader(fileName);
    dataFrame = dataLoader.load();
  }

  public List<String> getColumnNames(){ return  dataFrame.getColumnNames();}

  // was getPatientNames
  public List<String> getPatientIDs()
  {
    return dataFrame.getOrderedColumn("ID");
  }

  // find multiple keywords
  // to avoid duplicate records a set is used
  public Set<List<String>> basicSearchFor(String[] keywords)
  {
    Set<List<String>> returnSet = new HashSet<>();
    for (String word : keywords){
      returnSet.addAll(dataFrame.basicSearchFor(word));
    }

    return returnSet;
  }

  public List<String> getRow(Integer i){
    return dataFrame.getRecord(i);
  }

  public List<List<String>> advancedSearchFor(String keyword, String field)
  {
    if (field.equals("ID")){
      List<String> IDs = dataFrame.searchID(keyword);
      List<List<String>> result = new ArrayList<>();
      if (IDs.isEmpty()){return result;}
      result.add(IDs);
      return result;
    }
    return dataFrame.searchBy(keyword, field);
  }

  public List<List<String>> getTable() {
    HashMap<String, List<String>> all = new HashMap<>(dataFrame.getOrderedColumns());
    List<String> headers = new ArrayList<>(all.keySet());
    List<List<String>> table = new ArrayList<>();
    table.add(headers);
    for (int i = 0; i < dataFrame.getRowCount(); i++){
      List<String> row = new ArrayList<>();
      for (String field : headers){
        row.add(all.get(field).get(i));
      }
      table.add(row);
    }
    return table;
  }

  public String addRow(HashMap<String,String> record) {return dataFrame.addRow(record);}

  public void deleteRow(Integer index) {dataFrame.deleteRow(index);}
  public String modifyRow(Integer index, HashMap<String,String> record) {return dataFrame.modifyRow(index, record);}

  public void sort(String field, Boolean asc) { dataFrame.sortBy(field, asc);}
}
