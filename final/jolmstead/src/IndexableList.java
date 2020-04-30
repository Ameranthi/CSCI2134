import java.util.ArrayList;
import java.util.Scanner;

/* This class implements an indexable list, which is a list that whose
 * elements can be efficiently accessed by their indices in the list.
 * I.e., the first element has index 0, the second has index 1, etc.
 *
 * IndexableList uses an array (currentArray) to store the elements,
 * making it efficiently indexable.  When the array fills up, a bigger
 * array is allocated.  To avoid the large cost of copying all the  
 * values at once into the bigger array, a special approach is used. 
 * The smaller array is assigned to prevArray.  During each operation
 * one element is copied from prevArray to currentArray.  The lastMoved
 * index keeps track of the last element moved.  If lastMoved is 0,
 * then all the elements have been moved from prevArray to currentArray.
 *
 * The invariant is that all elements with indices less the lastMoved
 * are in prevArray and all elements with indices greater or equals to
 * lastMoved are in currentArray
 */

@SuppressWarnings("unchecked")

public class IndexableList<E> {
    protected int numberOfElementsInCurrent;         // number of elements in list
    protected int lastMoved;           // last element moved from prevArray
    protected Object [] prevArray;     // previous (smaller) array
    protected Object [] currentArray;  // current array of elements


    public IndexableList() {

    }


    public IndexableList(ArrayList<E> list) {
        for (E element : list) {
            add(element);
        }
    }

    public IndexableList(E[] array) {
        assert array != null : "array shouldn't be null";
        currentArray = array;
        numberOfElementsInCurrent = array.length;
        assert currentArray != null: "current array shouldn't be null";
        for (E element : array) {
            add(element);
        }
    }

    /* Appends the specified element to the end of this list
     * Parameters:
     *     e - element to be appended to this list
     * Returns:
     *     true
     */
    public boolean add(E element) {
        add(numberOfElementsInCurrent, element);
        return true;
    }

    /* Inserts the specified element at the specified
     * position in this list.
     * Parameters:
     *    index - index at which the specified element is
     *            to be inserted
     *    element - element to be inserted
     * TBT
     */
    public void add(int index, E element) {
        if (currentArray.length == size()) {
            prevArray = currentArray;
            final int addedLength = currentArray.length;

            currentArray = new Object[prevArray.length + addedLength];
            lastMoved = size();

            assert currentArray.length > prevArray.length;
            numberOfElementsInCurrent = currentArray.length;
        }

        if (lastMoved > 0) {
            currentArray[lastMoved-1] = prevArray[lastMoved-1];
            lastMoved--;
        }

        for (int i = size()-1; i > index-1; i--) {
            set(i, get(i));
        }
        set(index, element);
        numberOfElementsInCurrent++;
    }

    /* Returns true if this list contains the specified element.
     * Parameters:
     *     o - element whose presence in this list is to be tested
     * Returns:
     *     true if this list contains the specified element
     * TBT
     */
    public boolean contains(Object o) {
        for (int i = 0; i < lastMoved; i++) {
            if (prevArray[i].equals(o)) {
                return true;
            }
        }

        for (int i = lastMoved; i < numberOfElementsInCurrent; i++) {
            if (currentArray[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the element at the specified position in this list.
     * Parameters:
     *     index - index of the element to return
     * Returns:
     *     the element at the specified position in this list
     */
    public E get(int index) {
        if (index < lastMoved) {
            return (E)prevArray[index];
        } else {
            return (E)currentArray[index];
        }
    }

    /* Returns the index of the first occurrence of the
     * specified element in this list, or -1 if this list
     * does not contain the element.
     * Parameters:
     *     o - element to search for
     *  Returns:
     *     the index of the first occurrence of the specified
     *     element in this list, or -1 if this list does not
     *     contain the element
     * TBT
     */
    public int indexOf(Object o) {
        assert lastMoved > -1;
        for (int i = 0; i < lastMoved; i++) {
            if (prevArray[i].equals(o)) {
                return i;
            }
        }

        assert numberOfElementsInCurrent == currentArray.length;
        for (int i = lastMoved; i < numberOfElementsInCurrent; i++) {
            if (currentArray[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    /* Returns true if this list contains no elements.
     * Returns:
     *     true if this list contains no elements
     */
    public boolean isEmpty() {
        return numberOfElementsInCurrent == 0;
    }

    /* Removes the element at the specified position in
     * this list (optional operation).
     * Parameters:
     *     index - the index of the element to be removed
     * Returns:
     *     the element previously at the specified position
     * TBT
     */
    public E remove(int index) {
        E element = get(index);

        for (int i = index; i < numberOfElementsInCurrent - 1; i++) {
            set(i, get(i + 1));
        }

        numberOfElementsInCurrent--;
        set(numberOfElementsInCurrent, null);

        return element;
    }

    /* Removes the first occurrence of the specified element
     * from this list, if it is present.
     * Parameters:
     *     o - element to be removed from this list, if present
     * Returns:
     *     true if this list contained the specified element
     */
    public boolean remove(Object o) {
        int i = indexOf(o);
        if (i >= 0) {
            remove(i);
        }
        return i >= 0;
    }

    /* Replaces the element at the specified position in this
     * list with the specified element.
     * Parameters:
     *     index - index of the element to replace
     *     element - element to be stored at the specified position
     * Returns:
     *     the element previously at the specified position
     */
    public E set(int index, E element) {
        E e = get(index);
        if (index < lastMoved) {
            prevArray[index] = element;
        } else {
            currentArray[index] = element;
        }
        return e;
    }

    /* Returns the number of elements in this list.
     * Returns:
     *     the number of elements in this list
     */
    public int size() {
        return numberOfElementsInCurrent;
    }

    /* Returns a view of the portion of this list between
     * the specified fromIndex, inclusive, and toIndex, exclusive.
     * Parameters:
     *     fromIndex - low endpoint (inclusive) of the subList
     *     toIndex - high endpoint (exclusive) of the subList
     * Returns:
     *     a view of the specified range within this list
     */
    public ArrayList<E> subList(int fromIndex, int toIndex) {
        ArrayList<E> list = new ArrayList<>();
        for (int i = fromIndex; i < toIndex; i++) {
            list.add(get(i));
        }
        return list;
    }

    /* Returns a Scanner containing the elements represented
     * by their toString() method.
     */
    public Scanner toScanner() {
        java.lang.String s = "";
        for (int i = 0; i < numberOfElementsInCurrent; i++) {
            s += get(i).toString() + " ";
        }
        return new Scanner(s);
    }
}
