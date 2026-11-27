abstract class OpenAddressing<T extends Comparable<T>> implements HashTable<T>{
  UnorderedArray<T> table;

  OpenAddressing(){
    this.table = new UnorderedArray<>(13);
  }


  // this is the probe function
  abstract public Integer probe(String key, int probeNumber);


  public void insert(String key, T value){
    // what we do when inserting is we first hash it
    // TODO: deal with the case when it overflows, or double before it overflows (after it reaches some threshold load factor)
    int mappedIndex = (int) (Util.hash(key, Util.pDefault, Util.mDefault) % this.table.size());
    int insertionIndex = mappedIndex;
    int probeNumber = 0;
    while (this.table.get(mappedIndex) != null){
      // while it's occupied, continue 
      probeNumber++;
      insertionIndex = mappedIndex + probe(key, probeNumber);
    }

    this.table.insertAt(insertionIndex, value);

  }
  public void search(String key){

  }
  public void delete(String key){

  }
  public String toString(){
    String returnString = "";
    return returnString;
  }

}
