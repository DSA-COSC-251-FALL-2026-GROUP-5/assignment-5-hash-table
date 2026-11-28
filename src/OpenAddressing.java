abstract class OpenAddressing<T extends Comparable<T>> implements HashTable<T>{
  private static final double MAX_LOAD_FACTOR = 0.5;
  private int deletedAmount = 0;

  UnorderedArray<HashEntry<T>> table;

  OpenAddressing(){
    // NOTE: make the default size 13
    this.table = new UnorderedArray<>(5);
  }

  // this is the probe function
  abstract public Integer probe(String key, int probeNumber);

  void insertToTable(UnorderedArray<HashEntry<T>> table, HashEntry<T> hashEntry){
    int mappedIndex = (int) (Util.hash(hashEntry.key, Util.pDefault, Util.mDefault) % this.table.size());
    int insertionIndex = mappedIndex;
    int probeNumber = 0;
    while (table.get(insertionIndex) != null){
      // while it's occupied, continue 
      probeNumber++;
      insertionIndex = (mappedIndex + probe(hashEntry.key, probeNumber)) % this.table.size();
    }
    table.insertAt(insertionIndex, hashEntry);
  }

  void rehash(UnorderedArray<HashEntry<T>> oldTable, UnorderedArray<HashEntry<T>> newTable){
    for (int i = 0; i < oldTable.arr.length; i++){
      if (oldTable.get(i) == null || oldTable.get(i).deleted){
        continue;
      }
      insertToTable(newTable, oldTable.get(i));
    }
  }

  public void insert(String key, T value){
    // what we do when inserting is we first hash it
    // TODO: deal with the case when it overflows, or double before it overflows (after it reaches some threshold load factor)
    if (table.occupiedAmount >= table.capacity*MAX_LOAD_FACTOR){
      int resizeSize = table.nextResizeAmount(table.capacity);
      UnorderedArray<HashEntry<T>> newTable = new UnorderedArray<>(resizeSize);
      rehash(table, newTable);
      this.table = newTable;
    }
    HashEntry<T> hashEntry = new HashEntry<>(key, value);
    insertToTable(this.table, hashEntry);
  }

  public void search(String key){

  }
  public void delete(String key){

  }

  public String toString(){
    return table.toString();
  }
}
