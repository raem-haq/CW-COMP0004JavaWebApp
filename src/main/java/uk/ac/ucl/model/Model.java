package uk.ac.ucl.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
  private DataFrame dataFrame;

  public Model(String fileName) {
    DataLoader dataLoader = new DataLoader(fileName);
    dataFrame = dataLoader.load();
  }

  // The example code in this class should be replaced by your Model class code.
  // The data should be stored in a suitable data structure.

  public List<String> getColumnNames(){ return  dataFrame.getColumnNames();}

  // was getPatientNames
  public List<String> getPatientIDs()
  {
    return dataFrame.getColumn("ID").getRows();
  }

  // This also returns dummy data. The real version should use the keyword parameter to search
  // the data and return a list of matching items.
  public List<List<String>> basicSearchFor(String keyword)
  {
    return dataFrame.basicSearchFor(keyword);
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
    List<List<String>> table = new ArrayList<>();
    table.add(getColumnNames());
    table.addAll(dataFrame.getAll());
    return table;
  }

  public void sort(String field, Boolean asc) { dataFrame.sortBy(field, asc);}
}
