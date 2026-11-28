public class UnorderedArray<T extends Comparable<T>> extends GenericArray<T> {
  UnorderedArray(int size) {
    super(size);
  }

  @Override
  void insert(T x) {
    // 3.1. insertion simply inserts it at the end, which should take O(1) because
    // we're keeping track of the occupiedAmount pointer, so we resize if it's going
    // to overflow
    // WARNING: because we implemented insertAt, insert should not be used, because, all of the elements that are stored will no longer be inn a continuous blob, thus, it might be the case that insertion overrides some other value that has already been written to the array.
    if (occupiedAmount == capacity) {
      resize(nextResizeAmount(capacity));
    }
    arr[occupiedAmount] = x;
    occupiedAmount++;
  }

  void insertAt(int index, T x){
    if (arr[index] == null){
      occupiedAmount++;
    }
    arr[index] = x;
  }

  @SuppressWarnings("unchecked")
  @Override
  int find(T x) {
    // 5.1. find using an unordered array requires linear search, which requires
    // O(n) time complexity
    // WARNING: find will also not work because of the addition of insertAt
    for (int i = 0; i < occupiedAmount; i++) {
      if (((T[]) arr)[i].compareTo(x) == 0) {
        // we find the first occurance
        return i;
      }
    }
    // if we've searched through the whole occupied space and haven't returned, then
    // we haven't found it.
    return -1;
  }
}
