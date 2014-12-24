public class Test {
    public static void main( String[] args ) {
        // Testing ReturnObject Implementation
        // testReturnObject(ReturnObject ro, returnValueExpected, hasErrorExpected, errorMessageExpected)
        testReturnObject("ReturnObject 01",new ReturnObjectImpl(null),                            null,  false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 02",new ReturnObjectImpl(1),                               1,     false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 03",new ReturnObjectImpl("Test"),                          "Test",false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 04",new ReturnObjectImpl(1.442),                           1.442, false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 05",new ReturnObjectImpl(false),                           false, false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 06",new ReturnObjectImpl(ErrorMessage.NO_ERROR),           null,  false,ErrorMessage.NO_ERROR);
        testReturnObject("ReturnObject 07",new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE),    null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testReturnObject("ReturnObject 08",new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS),null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testReturnObject("ReturnObject 09",new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT),   null,  true, ErrorMessage.INVALID_ARGUMENT);

        // Testing the ArrayList Implementation of List
        List list = new ArrayList();
        // testArrayList(testName, list, isEmptyExpected, sizeExpected)
        testArrayList   ("ArrayList 00b",list,true,0);
        testReturnObject("ArrayList 01",list.add("First element"), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 01b",list,false,1);
        testReturnObject("ArrayList 02",list.add("Second element"),"Second element", false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 02b",list,false,2);
        testReturnObject("ArrayList 03",list.add(0,"Insert new at zero"), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 03b",list,false,3);
        testReturnObject("ArrayList 04",list.add(10,"Invalid position"), null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   ("ArrayList 04b",list,false,3);
        testReturnObject("ArrayList 05",list.add("Element to remove"),"Element to remove", false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 05b",list,false,4);
        testReturnObject("ArrayList 06",list.add("Last element"),"Last element", false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 06b",list,false,5);
        testReturnObject("ArrayList 07",list.add(null), null,  true, ErrorMessage.INVALID_ARGUMENT);
        testArrayList   ("ArrayList 07b",list,false,5);

        testReturnObject("ArrayList 08",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 08b",list,false,5);
        testReturnObject("ArrayList 09",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 09b",list,false,5);
        testReturnObject("ArrayList 10",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 10b",list,false,5);
        testReturnObject("ArrayList 11",list.get(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 11b",list,false,5);
        testReturnObject("ArrayList 12",list.get(4), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 12b",list,false,5);
        testReturnObject("ArrayList 13",list.get(5), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testArrayList   ("ArrayList 13b",list,false,5);

        testReturnObject("ArrayList 14",list.remove(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 14b",list,false,4);

        testReturnObject("ArrayList 15",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 15b",list,false,4);
        testReturnObject("ArrayList 16",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 16b",list,false,4);
        testReturnObject("ArrayList 17",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 17b",list,false,4);
        testReturnObject("ArrayList 18",list.get(3), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 18b",list,false,4);
        testReturnObject("ArrayList 19",list.get(4), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testArrayList   ("ArrayList 19b",list,false,4);

        testReturnObject("ArrayList 20",list.remove(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 20b",list,false,3);
        testReturnObject("ArrayList 21",list.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 21b",list,false,2);
        testReturnObject("ArrayList 22",list.remove(0), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 22b",list,false,1);
        testReturnObject("ArrayList 23",list.remove(0), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   ("ArrayList 23b",list,true,0);

	}


    /**
     * Unit Testing ArrayList Implementation.
     * Outputs results only for isEmpty() and size()
     *
     * @param testName the test name
     * @param list the List
     * @param isEmptyExpected boolean expected list value for isEmpty()
     * @param sizeExpected int for the element amount expected in List
     */
    private static void testArrayList(String testName, List list, boolean isEmptyExpected, int sizeExpected) {
		boolean isEmptyFound = list.isEmpty();
		int     sizeFound    = list.size();

		if ( isEmptyFound != isEmptyExpected ) {
			System.out.println("[" + testName + "]\t - List.isEmpty() Found: " + isEmptyFound + " and expected " + isEmptyExpected);
		}

		if ( sizeFound != sizeExpected ) {
			System.out.println("[" + testName + "]\t - List.size() Found: " + sizeFound + " and expected " + sizeExpected);
		}
    }

    /**
     * Unit Testing ReturnObject Implementation.
     * Outputs detailed results if found do not match expected.
     *
     * @param testName the test name
     * @param ro The ReturnObject to check against
     * @param returnValueExpected the Object value expected
     * @param hasErrorExpected boolean for the expected error
     * @param errorMessageExpected the expected error message
     */
	private static void testReturnObject(String testName, ReturnObject ro, Object returnValueExpected, boolean hasErrorExpected, ErrorMessage errorMessageExpected) {
		Object returnValueFound = ro.getReturnValue();
		boolean hasErrorFound   = ro.hasError();
		ErrorMessage errorMessageFound = ro.getError();

		if ( returnValueFound == null && returnValueExpected == null ) {
			// Do nothing
		}
		else if ( returnValueFound == null && returnValueExpected != null ) {
			System.out.println("[" + testName + "]\t - Returned values differ. Found: " + returnValueFound + " and expected " + returnValueExpected);
		}
		else if ( returnValueFound != null && returnValueExpected == null ) {
			System.out.println("[" + testName + "]\t - Returned values differ. Found: " + returnValueFound + " and expected " + returnValueExpected);
		}
		else if ( returnValueFound != null && returnValueExpected != null && !returnValueFound.equals(returnValueExpected) ) {
			System.out.println("[" + testName + "]\t - Returned values differ. Found: " + returnValueFound + " and expected " + returnValueExpected);
		}

		if ( hasErrorFound != hasErrorExpected ) {
			System.out.println("[" + testName + "]\t - HasError values differ. Found: " + hasErrorFound + " and expected " + hasErrorExpected);
		}

		if ( !errorMessageFound.equals(errorMessageExpected) ){
			System.out.println("[" + testName + "]\t - ErrorMessage values differ. Found: " + errorMessageFound + " and expected " + errorMessageExpected);
		}
	}
}