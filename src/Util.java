class Util{
  public static int pDefault = 31;
  public static int mDefault = 1000000007;
  public static long hash(String key, int p, int m){
    // this is a simple hashing algorithm called the polynomial rolling hash
    // p should ideally be prime
    // and m should also ideally be prime
    // typical choice is:
    // p = 31
    // m = 1 000 000 007
    // we want m to be prime because
    //
    // When we have the expression:
    // a b equiv 0 (mod m)
    long hashValue = 0;
    for (int i = 0; i < key.length(); i++){
      hashValue = (hashValue*p + key.charAt(i)) % m;
    }
    return hashValue;
  }
}
