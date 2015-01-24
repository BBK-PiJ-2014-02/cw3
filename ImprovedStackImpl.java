/**
 * An implementation of a stack with additional methods.
 *
 * Classes implementing this interface must use a {@see List} as the
 * underlying data structure to store the elements on the stack.
 *
 * Not all operations on a stack will always be successful. For
 * example, a programmer may try to pop an element from an empty
 * stack. Since we have not covered exceptions yet, we need another
 * mechanism to report errors. In order to do that, methods of this
 * list will return a {@see ReturnObject} that will contain either an
 * object or an error value of the right kind (as defined in {@see
 * ErrorMessage}).
 *
 * @author VascoHorta
 */
public class ImprovedStackImpl implements ImprovedStack {
    /**
     * The List
     */
    private List list;

    /**
     * The constructor
     */
    public ImprovedStackImpl(List list) {
        this.list = list;
    }

    /**
     * Returns a copy of this stack with the items reversed, the top
     * elements on the original stack is at the bottom of the new
     * stack and viceversa.
     *
     * @return a copy of this stack with the items reversed.
     */
    public ImprovedStack reverse() {
        // To return a copy of the list, a new one needs to be created.
        // Given that either LinkedList or ArrayList would work equally,
        // developer chosen at this point LinkedList for performance
        // reasons.
        ImprovedStack is = new ImprovedStackImpl(new LinkedList());

        for( int i = 0; i < size(); i++) {
            ReturnObject ro = list.get(i);
               is.push(ro.getReturnValue());
        }

         return is;
    }

    /**
     * Removes the given object from the stack if it is
     * there. Multiple instances of the object are all removed.
     *
     * Classes implementing this method must use method .equals() to
     * check whether the item is in the stack or not.
     *
     * @param object the object to remove
     */
    public void remove(Object object) {
        // Need to run through all list
        for(int i = 0; i < size(); i++) {
            // Get the return object for the index i
            ReturnObject ro = list.get(i);
            // Extract the object to be compared
            Object item = ro.getReturnValue();

            // If equals, remove it and redo that same index
            // since current i will be occupied with the next item
            // to ensure that no more copies of the same Object
            // exist in the list.
            if ( item.equals(object) ){
                list.remove(i);
                i--;
            }
        }
    }

    /**
     * Stack pop() functionality.
     *
     * @return a ReturnObject with the item popped from stack.
     */
    public ReturnObject pop() {
        return list.remove(0);
    }

    /**
     * Stack top() functionality.
     *
     * @return a ReturnObject with the top item in stack.
     */
    public ReturnObject top() {
        return list.get(0);
    }

    /**
     * Stach push() functionality.
     *
     * @param item adds a new item to the Stack.
     */
    public void push(Object item) {
        // Push the new item into position 0
        if( isEmpty() ) {
            list.add(item);
        }
        else {
            list.add(0,item);
        }
    }

    /**
     * The size of the stack.
     *
     * @return the size of the stack.
     */
    public int size() {
        return list.size();
    }

    /**
     * Check if stack is empty.
     *
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

