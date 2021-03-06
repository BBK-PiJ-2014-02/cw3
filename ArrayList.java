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
public class ArrayList implements List {
    /**
     * Array initial size.
     */
    private final int INITIAL_ARRAY_SIZE = 10;

    /**
     * Rate of change in percentage for the array to increase of decrease.
     */
    private final int ARRAY_RATE_OF_CHANGE_PERCENTAGE = 10;

    /**
     * The array list
     */
    private Object[] array;

    /**
     * The amount of elements in the array
     */
    private int size;

    /**
     * The constructor
     *
     * Initializes the array, and sets the size to zero elements.
     */
    public ArrayList() {
        this.array = new Object[INITIAL_ARRAY_SIZE];
        this.size  = 0;
    }

    /**
     * Returns true if the list is empty, false otherwise.
     *
     * @return true or false if the list is empty or not.
     */
    public boolean isEmpty() {
        if ( this.size > 0 ) {
            return false;
        }
        return true;
    }

    /**
     * The number of elements in the list.
     *
     * @return the number of elements in the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Returns the element at the given position.
     *
     * If the index is negative or greater or equal than the size of
     * the list, then an appropriate error must be returned.
     *
     * @param index the position in the list of the item to be retrieved
     * @return the element or an appropriate error message,
     *         encapsulated in a ReturnObject
     */
    public ReturnObject get(int index) {
        // Validate the index
        ReturnObject ro = checkIndex(index);

        // If no errors, an object is to be returned, confidently.
        if ( !ro.hasError() ) {
            ro = new ReturnObjectImpl(this.array[index]);
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
        // Validate the index
        ReturnObject ro = checkIndex(index);

        // If no errors, an object is to be removed confidently
        if ( !ro.hasError() ) {

            // Resize down the array if too big before removing the new element
            if ( (this.size + ( this.array.length * ARRAY_RATE_OF_CHANGE_PERCENTAGE / 100 )) < this.array.length ) {
                reduceArray();
            }

            // Retrieve the element into a prepared response
            ro = new ReturnObjectImpl(this.array[index]);

            // Remove the element on given index moving all elements
            // on given index and subsequent, one index down.
            shiftDown(index);

            // Number of elements decreases
            this.size--;

        }
        return ro;
    }

    /**
     * Adds an element to the list, inserting it at the given
     * position. The indexes of elements at and after that position
     * must be updated accordingly.
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
        // Validate the index
        ReturnObject ro = checkIndex(index);

        // Validate the item supplied. Null is not allowed
        if ( item == null ) {
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
        }

        // If no errors, confidently adds the new item to the required index
        if ( !ro.hasError() ) {

            // Do we need more space?
            if ( (this.size + 1) >= this.array.length ) {
                extendArray();
            }

            // Shift all elements one index up
            shiftUp(index);

            // Add the item into the respective index
            this.array[index] = item;

            // Number of elements increases
            this.size++;

            // Prepare the response
            return new ReturnObjectImpl(item);
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
        // Validate the item supplied. No null objects allowed
        if ( item == null ) {
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
        }

        // If no errors, confidently add the new item at the end of the array.
        // Do we need more space?
        if ( (this.size + 1) >= this.array.length ) {
            extendArray();
        }

        // Assign the element to the last available position
        this.array[size] = item;

        // Update the element amount in the array
        this.size++;

        // Prepare the response
        return new ReturnObjectImpl(item);
    }


    /**
     * Checks if the supplied index is valid and returns a
     * ReturnObject with appropriate error message.
     *
     * @param index of the array
     * @return ReturnObject with the error or null if no error
     */
    private ReturnObject checkIndex(int index) {
        // NOTE: cannot get(index) or add(index,item) on an empty list.
        if ( isEmpty() ) {
            return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
        }

        // Check index out of bounds, i.e.: negative
        if ( index < 0 ) {
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }

        // Check index out of bounds, i.e.: bigger than or equal to the array size
        if ( index >= this.size ) {
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        }

        // When all checks pass, the index is valid.
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }

    /**
     * Extends the array to accomodate more elements.
     * The amount of the extension is calculated by ARRAY_INCREASE_PERCENTAGE.
     */
    private void extendArray() {
        // Current array size
        int currentSize = this.array.length;

        // Percentage to add to current size
        int addingSize = ( currentSize * ARRAY_RATE_OF_CHANGE_PERCENTAGE / 100 );

        // The new array size
        int newSize = currentSize + addingSize;

        // Create the new Object array with the new size
        Object[] copy = new Object[newSize];

        // Copy all elements from the old array to the new
        for(int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }

        // Lose the old and assign the new to this.array
        this.array = copy;
    }

    /**
     * Reduce the array size to speed up operations.
     * The amount of the extension to be cut is calculated by ARRAY_INCREASE_PERCENTAGE.
     */
    private void reduceArray() {
        // Current array size
        int currentSize = this.array.length;

        // Percentage to cut to current size
        int reducingSize  = ( currentSize * ARRAY_RATE_OF_CHANGE_PERCENTAGE / 100 );

        // The array's new size
        int newSize     = currentSize - reducingSize;

        // Create the new Object array with the new size
        Object[] copy = new Object[newSize+1];

        // Copy all elements from the old array to the new
        for(int i = 0; i < copy.length; i++) {
            copy[i] = array[i];
        }

        // Lose the old and assign the new to this.array
        this.array = copy;
    }

    /**
     * Shift all elements one index up starting from the given index
     */
    private void shiftUp(int index) {
        // Make sure there is still enough space in the array to add more elements.
        if ( this.array.length < this.size + (this.array.length * ARRAY_RATE_OF_CHANGE_PERCENTAGE / 100) ) {
            extendArray();
        }

        // Move all subsequent elements of index, including index, one index up.
        for( int i = this.array.length-1; i > index; i-- ) {
            this.array[i] = this.array[i-1];
        }
    }

    /**
     * Shift all elements one index down starting from the given index+1
     */
    private void shiftDown(int index) {
        // Move all subsequent elements of index, one index down,
        // overriding the element on the index position.
        for( int i = index; i < this.array.length-1; i++ ) {
            this.array[i] = this.array[i+1];
        }
    }
}
