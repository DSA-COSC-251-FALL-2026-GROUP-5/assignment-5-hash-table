class LinearProbing<T extends Comparable<T>> extends OpenAddressing<T>{
  public Integer probe(String key, int probeNumber){
    return probeNumber;
  }
}
