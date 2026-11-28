interface HashTable<T> {
  public void insert(String key, T value);
  public void search(String key);
  public void delete(String key);
  public String toString();
}
