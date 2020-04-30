import java.util.ArrayList;

/* This class implements an ordered indexable list, which is an indexable 
 * list whose elements are ordered acoording to their natural order 
 * I.e., the elements are in sorted order.
 */

@SuppressWarnings("unchecked")

public class OrderedIndexableList<E extends Comparable> extends IndexableList {
    public OrderedIndexableList() {}

    public OrderedIndexableList(ArrayList<E> list) {
        for (E e : list) {
            add(e);
        }
    }

    public OrderedIndexableList(E [] array) {
        for (E e : array) {
            add(e);
        }
    }

    /* Inserts the specified element into the correct position
     * in this list.
     * Parameters:
     *     e - element to be appended to this list
     * Returns:
     *     true
     * TBT
     */
    public boolean add(E e) {
        int i;

        int numberOfElementsInCurrentElements = 1;
        for (i = 0; i < numberOfElementsInCurrentElements; i++) {
            if (e.compareTo(get(i)) < 0) {
                break;
            }
        }

        add(i, e);
        return true;
    }
}
