/**
 * A sampleable list can be sampled.
 *
 * @author Vasco Horta
 */
public class SampleableListImpl extends LinkedList implements SampleableList {
    /**
     * Returns a list containing the first, third, fifth...
     * items of this list, or an empty list if the list is empty.
     *
     * @return a list containing the first, third, fifth... items of this list
     */
    public SampleableList sample() {
        // Get a new SampleableList
        SampleableList sl = new SampleableListImpl();

        // Add first, third, fifth elements as requested
        for( int i = 0; i < size(); i++ ) {
            // The first element is element 0
            if ( i % 2 == 0 ) {
                ReturnObject ro = get(i);
                sl.add(ro.getReturnValue());
            }
        }
        // Return the built new SampleableList
        return sl;
    }
}
