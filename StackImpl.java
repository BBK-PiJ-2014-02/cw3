/**
 * An implementation of a stack that uses a {@List} as the underlying
 * data structure.
 *
 * Not all operations on a stack will always be successful. For
 * example, a programmer may try to pop an element from an empty
 * stack. Since we have not covered exceptions yet, we need another
 * mechanism to report errors. In order to do that, methods of this
 * list will return a {@see ReturnObject} that will contain either an
 * object or an error value of the right kind (as defined in {@see
 * ErrorMessage}).
 *
 * @author Vasco Horta
 */
public class StackImpl extends AbstractStack implements Stack {
    /**
     * Constructor accepting a List type.
     *
     * @param list List
     */
	public StackImpl(List list) {
        super(list);
	}

    /**
     * Pop the last item added and remove it.
     *
     * @return ReturnObject with the last item added.
     */
	public ReturnObject pop() {
		ReturnObject ro = internalList.remove(0);
		return ro;
	}

    /**
     * Returns the last added item without removing it.
     *
     * @return ReturnObject with last added item.
     */
	public ReturnObject top() {
		ReturnObject ro = internalList.get(0);
		return ro;
	}

    /**
     * Push a given item into the List Stack.
     *
     * @param item type Object.
     */
	public void push(Object item) {
		// Add item to position 0
		internalList.add(0,item);
	}

    /**
     * The size of the List Stack.
     *
     * @return the size of List Stack.
     */
	public int size() {
		return internalList.size();
	}

    /**
     * Check if the List Stack is empty.
     *
     * @return true if empty, false otherwise.
     */
	public boolean isEmpty() {
		return internalList.isEmpty();
	}
}

