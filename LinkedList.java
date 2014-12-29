/**
 * A list is a collection of objects that are sorted and can be
 * accessed by index. The first element in the list is at index 0.
 *
 * A list can store objects of any kind, and they can be of different
 * types: Integers, Doubles, String, or even other lists. However,
 * this list cannot store null objects.
 *
 * There is no limit to the number of elements in the list (provided
 * that there is free memory in the Java Virtual Machine).
 *
 * Not all operations on a list will always be successful. For
 * example, a programmer may try to remove an element from an empty
 * list, or from a position where there is nothing. Since we have not
 * covered exceptions yet, we need another mechanism to report
 * errors. In order to do that, methods of this list will return a
 * {@see ReturnObject} that will contain either an object or an error
 * value of the right kind (as defined in {@see ErrorMessage}).
 *
 * @author Vasco Horta
 */
public class LinkedList implements List {
    /**
     * List size
     */
	private int size;

	/**
	 * First element
	 */
	private boolean first;

    /**
     * Pointer to the next LinkedList object
     */
    private LinkedList next;

    /**
     * The Object
     */
    private Object obj;

    /**
     * Public initialization constructor
     */
    public LinkedList() {
		this.size  = 0;
		this.next  = null;
		this.obj   = obj;
		this.first = true;
	}

    /**
     * Private new object initialization constructor
     */
    private LinkedList(Object obj) {
		this.obj   = obj;
		this.first = false;
	}

	/**
	 * Returns true if the list is empty, false otherwise.
	 *
	 * @return true if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if ( size == 0 ) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of items currently in the list.
	 *
	 * @return the number of items currently in the list
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns the elements at the given position.
	 *
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 *
	 * @param index the position in the list of the item to be retrieved
	 * @return the element or an appropriate error message,
	 *         encapsulated in a ReturnObject
	 */
	public ReturnObject get(int index) {
   		ReturnObject ro;

		// Validate index only for first time
		if ( this.first ) {
    		ro = checkIndex(index);
    		if ( ro.hasError() ) {
				return ro;
			}

			// On first element, no data is stored
			// Check if list is empty and return appropriate error here
			if( isEmpty() ) {
				return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
			}
		}

		// Index == -1 means that we have the matched index
		// Please note that index zero is the first element
		// First element from ArrayList is not to be used.
		if ( index == -1 ) {
			ro = new ReturnObjectImpl(this.obj);
		}
		// Search for next index if it is not null
		else if ( this.next != null ) {
			// Decrease index until we get zero: the matched one
			ro = this.next.get(--index);
		}
		else {
			// If nothing found, then a index requested points to a null structure
			ro = new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
		}

		return ro;
	}

	/**
	 * Returns the elements at the given position and removes it
	 * from the list. The indexes of elements after the removed
	 * element must be updated accordignly.
	 *
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 *
	 * @param index the position in the list of the item to be retrieved
	 * @return the element or an appropriate error message,
	 *         encapsulated in a ReturnObject
	 */
	public ReturnObject remove(int index) {
   		ReturnObject ro = null;
		// Validate index only for first time
		if ( this.first ) {
    		ro = checkIndex(index);
    		if ( ro.hasError() ) {
				return ro;
			}

			// On first element, no data is stored
			// Check if list is empty and return appropriate error here
			if( isEmpty() ) {
				return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
			}
		}

        // Check if next element is the one we need to remove
		if ( index == 0 ) {
			if ( this.next == null ) {
				ro = new ReturnObjectImpl(this.next.obj);
				this.next = null;
			}
			else {
				ro = new ReturnObjectImpl(this.next.obj);
				this.next = this.next.next;
			}
		}
		else {
			ro = this.next.remove(--index);
		}

		if ( first ) {
			this.size--;
		}

		return ro;
	}

	/**
	 * Adds an element to the list, inserting it at the given
	 * position. The indeces of elements at and after that position
	 * must be updated accordignly.
	 *
	 * If the index is negative or greater or equal than the size of
	 * the list, then an appropriate error must be returned.
	 *
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 *
	 * @param index the position at which the item should be inserted in
	 *              the list
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         the item added or containing an appropriate error message
	 */
	public ReturnObject add(int index, Object item) {
   		ReturnObject ro;
		// Validate index only for first time
		if ( this.first ) {
    		ro = checkIndex(index);
    		if ( ro.hasError() ) {
				return ro;
			}

			// On first element, no data is stored
			// Check if list is empty and return appropriate error here
			if( isEmpty() ) {
				return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
			}
		}

		// Check if the item is not null. Null is not allowed
		if ( item == null ) {
		    ro = new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		}
    	// Add the element on given index
    	// ensuring that the linked chain is not broken.
    	else {
			if ( index == -1 ) {
				// Duplicate this.obj into a newly created item
				LinkedList newList = new LinkedList(this.obj);
				// Store the next item in to link to.
				LinkedList nextList = this.next;
				// Change this.obj with the current given item
				this.obj = item;
				// Restitch the list
				this.next = newList;
				newList.next = nextList;
   		        // Prepare the response
   		        ro = new ReturnObjectImpl(item);
			}
			else {
				ro = this.next.add(--index, item);
			}
		}

        if ( first ) {
            // Update the number of elements
            this.size++;
		}

		return ro;
	}

	/**
	 * Adds an element at the end of the list.
	 *
	 * If a null object is provided to insert in the list, the
	 * request must be ignored and an appropriate error must be
	 * returned.
	 *
	 * @param item the value to insert into the list
	 * @return an ReturnObject, empty if the operation is successful
	 *         the item added or containing an appropriate error message
	 */
	public ReturnObject add(Object item) {
		ReturnObject ro;
		if ( item == null ) {
			ro = new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
		}
		else if ( this.next == null ) {
            this.size++;
            this.next = new LinkedList(item);
            ro = new ReturnObjectImpl(item);
		}
		else {
			ro = this.next.add(item);
			this.size++;
		}

		return ro;
	}

    /**
     * Returns null if valid or the ReturnObject with the error
     *
     * @param index of the list
     * @return ReturnObject with the error or null if no error
     */
    private ReturnObject checkIndex(int index) {
   		ReturnObject ro;

        // Check index out of bounds, i.e.: negative
		if ( index < 0 ) {
			ro = new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}

		// Check index out of bounds, i.e.: bigger than the list size
		else if ( index > this.size ) {
			ro = new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
		}

		// When all checks pass, the index is valid.
		else {
			ro = new ReturnObjectImpl(ErrorMessage.NO_ERROR);
		}

		return ro;
	}
}
