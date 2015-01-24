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
 * @author VascoHorta
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
        this.obj   = null;
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
		// Please keep in mind that we start from the index value given plus one
		// and then recursively reduce it until 0 is reach for the wanted element.
		// The first element is the HEAD where no data is stored.

        ReturnObject ro;

        // Validate index only when at the HEAD of the list
        if ( this.first ) {
            // At the HEAD, no data is stored
            // Check if list is empty and return appropriate error if so
            if( isEmpty() ) {
                return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
            }

            // Now validate the index.
            ro = checkIndex(index);

            // If any error is found, stop here and return it.
            if ( ro.hasError() ) {
                return ro;
            }

            // First element is the HEAD, thus virtually index -1, so we bump the given index by one
            // such that when index given is 0, we start decreasing from 1, and move to the next in list,
            // ignoring the HEAD element.
            index++;
        }

        // Recursive index == 0 means that we have the matched index we are interested in.
        // First element from LinkedList is not to be used.
        if ( index == 0 ) {
            ro = new ReturnObjectImpl(this.obj);
        }
        // Wanted indexed element is not found yet, i.e.: index == 0, thus continue to next if not null
        else {
            // Decrease index until we reach index 0: the matched one
            ro = this.next.get(--index);

            // Please note that no checks were made to check if this.next == null
            // As a developer, we want this class to fatal if at any point the
            // this.next->get(--index) is attempted on a null this.next.
            // It is much easier to fix the problem if it fatals here than masking
            // the problem and make live harder to the next developer.
            // THUS: check the checkIndex() as it should have captured this
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

            // On first list element = HEAD, no data is stored
            // Check if list is empty and return appropriate error here
            if( isEmpty() ) {
                return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
            }

            // If not empty, check if index is valid
            else {
                ro = checkIndex(index);
                if ( ro.hasError() ) return ro;
            }

            // To skip the HEAD, index is reduced by one
            index--;

            // All checks done, ready to extract the requested item.
            // Safely decrease the list size.
            this.size--;
        }

        // Check if next element is the one we need to remove
        if ( index == -1 ) {
            ro = new ReturnObjectImpl(this.next.obj);
            this.next = this.next.next;
        }
        else {
            ro = this.next.remove(--index);
        }

        // Many checks for null next elements were skipped to give full trust
        // on the checkIndex() method instead.

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
		// Please keep in mind that we start from the index value given
		// and then recursively decrease it to 0 on each recursive call.
		// The first element is the HEAD where no data is stored.

        ReturnObject ro;

        // Validate index only once at the first runing time
        if ( this.first ) {

            // Null items are not allowed to be added
            if ( item == null ) {
                return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
            }

            // Ensure we have got a valid index
            ro = checkIndex(index);
            if ( ro.hasError() ) {
                return ro;
            }

            // All checks passed, confidently increase size,
            // trusting this item will be added.
            this.size++;

            // Bump the given index by one to skip the HEAD
            index++;
        }

        // This is the exact index where the new element should go.
        // - create a new node with this current node's item object
        // - point new node next element to current node.next element
        // - add newly supplied item object to current node
        // - point current node's.next element to the newly created node.
        if ( index == 0 ) {

            // Create a new node with this current node's item object
            LinkedList newListNode = new LinkedList(this.obj);

            // Store next node into a new node.
            LinkedList nextListNode = this.next;

            // Change this.obj to now have the new given item
            this.obj = item;

            // Restitch the list
            this.next = newListNode;
            newListNode.next = nextListNode;

            // Prepare the response
            ro = new ReturnObjectImpl(item);
        }

        // Reaching here, means not yet in position,
        // recursively keep searching while we have next.
        else {
            ro = this.next.add(--index, item);
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

        // Apply initial checks once
        if ( first ) {

            // If item is null, return immediately without any further action
            if ( item == null ) {
                return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
            }

            // All checks done, this element will be added.
            // Bump up size confidently.
            this.size++;
		}

        // If the next element in list is null, we have reached the end of the list
        // Append the new item here.
        if ( this.next == null ) {
            this.next = new LinkedList(item);
            ro = new ReturnObjectImpl(item);
        }
        // We have not yet reached the end of the list.
        else {
            ro = this.next.add(item);
        }

        return ro;
    }

    /**
     * Returns NO_ERROR if valid or the ReturnObject with the error
     *
     * @param index list required to be checked
     * @return ReturnObject with the error or null if no error
     */
    private ReturnObject checkIndex(int index) {

        // Check index out of bounds, i.e.: negative
        if ( index < 0 ) {
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }

        // Check index out of bounds, i.e.: bigger than or equal to the list size
        if ( index >= this.size ) {
            if ( isEmpty() ) {
                return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
            }
            else {
                return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
            }
        }

        // When all checks pass, the index is valid.
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }
}
