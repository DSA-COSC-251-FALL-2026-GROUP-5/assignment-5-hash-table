public class HashEntry<T> implements Comparable<HashEntry<T>>{
  public String key;
  public T value;
  public boolean deleted = false;

  HashEntry(){
  }

  HashEntry(String key, T value){
    this.key = key;
    this.value = value;
  }

  @Override
  public int compareTo(HashEntry<T> hashEntry) {
    // compare using keys
    return hashEntry.key.compareTo(this.key);
  }

  public String toString(){
    return String.format(
      "[key=%s, value=%s, deleted=%s]",
      key,
      value,
      deleted
    );
  }
}
