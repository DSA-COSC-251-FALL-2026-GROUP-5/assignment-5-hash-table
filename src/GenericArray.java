import java.math.BigInteger;

abstract class GenericArray<T extends Comparable<T>> {
    protected Object[] arr;
    protected int capacity;
    protected int occupiedAmount;

    GenericArray(int size) {
        arr = new Object[size];
        capacity = size;
        occupiedAmount = 0;
    }

  // UnorderedArray can just insert at the end, however, OrderedArray must insert
  // in order
  abstract void insert(T x);

  // clearly differs between ordered and unordered arrays
  abstract int find(T x);

  boolean delete(T x) {
    // 4. deletion does differ across ordered and unordered array, however, because
    // a deletion can be implemented using a .find() and a .leftShift() method, we
    // can write its different implementation in a subclass and simply use the
    // interface generically.

    // a .leftShift() is O(n) in the worst case. A .find is O(log(n)) for
    // an ordered array, and O(n) for unordered array
    //
    // As such, for an ordered array:
    // O(n) + O(log(n)) = O(n)
    //
    // For an unordered array:
    // O(n) + O(n) = O(n)

    int indexToDelete = find(x);
    if (indexToDelete == -1) {
      // if we can't find it, then we can't delete it
      return false;
    }
    // simply treat the value at that index as garbage and shift to the left to
    // override it.
    leftShift(indexToDelete);
    return true;
  }

  @SuppressWarnings("unchecked")
  T get(int index) {
    if (index < 0 || index >= capacity) {
      throw new IndexOutOfBoundsException();
    }

    return (T) arr[index];
  }

  int size() {
    // 7. I assume this is the total capacity that the array can hold, which is
    // simply the length of the current array (but if we don't want to use
    // arr.length, we can internally store it too). This will be executed in O(1)
    return capacity;
  }

  int count() {
    // 8. since we're internally storing the capacity anyway, this is a simple
    // lookup that can be executed in O(1) time
    return occupiedAmount;
  }

  void resize(int newSize) {
    Object[] newArr = new Object[newSize];

    occupiedAmount = Math.min(newSize, occupiedAmount);

    for (int i = 0; i < occupiedAmount; i++) {
      newArr[i] = arr[i];
    }

    arr = newArr;
    capacity = newSize;
  }

  void leftShift(int index) {
    // a utility function that imagines that there's a hole at index i, and shifts
    // everything beyond it to the left to "fill the hole"

    // assume that error checking has been done prior
    if (occupiedAmount <= 0) {
      return;
    }

    for (int i = index + 1; i < occupiedAmount; i++) {
      arr[i - 1] = arr[i];
    }

    arr[occupiedAmount - 1] = null;
    occupiedAmount--;
  }

  void rightShift(int index) {
    // a utility function that imagines that we need a hole at index i, and shifts
    // everything from index i to the right

    // we assume that we've already checked that it's safe to right shift without
    // loss

    if (!(occupiedAmount <= 0)) {
      for (int i = occupiedAmount; i > index; i--) {
        arr[i] = arr[i - 1];
      }
    }
    // assume that we will replace the space with something else later
    // NOTE: In the case that we're rightShifting beyond occupiedAmount, we'll also
    // increment occupiedAmount and trust that the reason why we called rightShift
    // in the first place is to make space
    occupiedAmount++;
  }

  int nextResizeAmount(int size) {
    // a utility function used as a heuristic to increment the size of the array if
    // we've inserted too many items

    //return 2 * size;

    // for now, we'll resize it to roughly twice the current size (but prime)
    return BigInteger.valueOf(size * 2L)
                            .nextProbablePrime()
                            .intValueExact();
  }

  @Override
  public String toString(){
    String returnString = "[";
    for (int i = 0; i < arr.length; i++) {
      if (i > 0){
        returnString = returnString.concat(", ");
      }
      if (arr[i] == null) {
        returnString = returnString.concat("null");
      } else {
        returnString = returnString.concat("%s".formatted(arr[i]));
      }
    }

    returnString = returnString.concat("]");
    return returnString;
  }

  void printArray() {
    // an extra utility function for debugging and testing purposes, which just
    // prints the entire array as a string to standard out
    System.out.println(toString());
  }

  void printInfo() {
    printArray();
    System.out.printf("Unordered array size: %d\n", size());
    System.out.printf("Unordered array occupied: %d\n", count());
  }
}
