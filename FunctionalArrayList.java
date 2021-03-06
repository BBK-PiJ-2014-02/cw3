/**
 * A functional list is a list with a few additional methods that are
 * common in functional languages (such as Lisp, Clojure, or Haskell)
 * to work with lists in a recursive way.
 *
 * Not all operations on a recursive list will always be
 * successful. For example, a programmer may try to extract the head
 * from an empty list. Since we have not covered exceptions yet, we
 * need another mechanism to report errors. In order to do that,
 * methods of this list will return a {@see ReturnObject} that will
 * contain either an object or an error value of the right kind (as
 * defined in {@see ErrorMessage}).
 *
 * @author VascoHorta
 */
public class FunctionalArrayList extends ArrayList implements FunctionalList {
    /**
     * Returns the element at the beginning of the list.
     *
     * If the list is empty, an appropriate error is returned.
     *
     * @return a copy of the element at the beginning of the list or
     *         an error if the list is empty.
     */
    public ReturnObject head() {
        ReturnObject ro;

        if ( isEmpty() ) {
            ro = new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
        }
        else {
            ro = get(0);
        }

        return ro;
    }

    /**
     * Returns a list with the elements in this list except the
     * head. The elements must be in the same order. The original list
     * must not change or be affected by changes in the new list.
     *
     * If the list is empty, another empty list is returned.
     *
     * @return FunctionalList independent copy without head
     */
    public FunctionalList rest() {
        // Get a new instance of FunctionalArrayList
        FunctionalList fl = new FunctionalArrayList();

        // Copy all elements to the new FunctionalArrayList without the head
        for ( int i = 1; i < size(); i++ ) {
            ReturnObject ro = get(i);
            fl.add(ro.getReturnValue());
        }

        // Return the newly created FunctionalList without the head.
        return fl;
    }
}
