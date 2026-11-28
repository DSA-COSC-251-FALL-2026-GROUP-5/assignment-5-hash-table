class Debug{
  public static void main(String[] args) {
    /*
    UnorderedArray<HashEntry<Integer>> unorderedArray = new UnorderedArray<>(10);
    unorderedArray.insertAt(1, new HashEntry<Integer>("hello", 2));
    unorderedArray.printArray();
    */
    /*
    System.out.println(Util.hash("hello", Util.pDefault, Util.mDefault) % 5);
    System.out.println(Util.hash("world", Util.pDefault, Util.mDefault) % 5);
    System.out.println(Util.hash("foo", Util.pDefault, Util.mDefault) % 5);
    System.out.println(Util.hash("bar", Util.pDefault, Util.mDefault) % 5);
    */

    LinearProbing<Integer> linearProbing = new LinearProbing<>();
    linearProbing.insert("hello", 0);
    linearProbing.insert("foo", 2);

    linearProbing.insert("world", 3);
    linearProbing.insert("lol", 4);
    //linearProbing.insert("ok", 5);
    System.out.println(linearProbing.toString());
    System.out.println(linearProbing.table.occupiedAmount);
  }
}
