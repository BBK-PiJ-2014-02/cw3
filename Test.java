public class Test {
    public static void main( String[] args ) {
		// Testing the FunctionalArrayList Implementation
		FunctionalList functionalList = new FunctionalArrayList();
		testLists("FunctionalArrayList", functionalList);

		// Testing the LinkedList Implementation
		testLists("LinkedList", new LinkedList());

        // Testing the ArrayList Implementation of List
        testLists("ArrayList", new ArrayList());

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
	}

    /**
     * Test run multiple the FunctionalArrayList calls
     *
     * @param testName string for the test name
     * @param functionalList the FunctionalList type implementation chosen
     */
    private static void testLists(String testName, FunctionalList functionalList) {
		// Test empty list
        testReturnObject(testName + " 00", functionalList.head(), null,  true, ErrorMessage.EMPTY_STRUCTURE);

        // Test new additions to the list
        testReturnObject(testName + " 01", functionalList.add("First element"), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 01b",functionalList,false,1);

        // Test head
        testReturnObject(testName + " 02", functionalList.head(), "First element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 03", functionalList.add("Second element"),"Second element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 04", functionalList.add("Third element"),"Third element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 05", functionalList.head(), "First element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 06", functionalList.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 07", functionalList,false,2);
        testReturnObject(testName + " 08", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);

        // Test rest
        FunctionalList shortList = functionalList.rest();
        testArrayList   (testName + " 07", shortList,false,1);
        testReturnObject(testName + " 08", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 09", shortList.head(), "Third element",  false, ErrorMessage.NO_ERROR);

        // Test adding elements to short list and checking FunctionalArrayList does not change
        testReturnObject(testName + " 10", shortList.add("ShortList element"), "ShortList element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 11", shortList,false,2);
        testReturnObject(testName + " 12", shortList.remove(0), "Third element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 13", shortList,false,1);
        testReturnObject(testName + " 14", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 15", shortList.head(), "ShortList element",  false, ErrorMessage.NO_ERROR);
	}

    /**
     * Test run the ArrayList or LinkedList
     *
     * @param testName string for the test name
     * @param list the list type implementation chosen
     */
    private static void testLists(String testName, List list) {
        // testArrayList(testName, list, isEmptyExpected, sizeExpected)
        testArrayList   (testName + " 00b",list,true,0);
        testReturnObject(testName + " 01",list.add("First element"), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 01b",list,false,1);
        testReturnObject(testName + " 02",list.add("Second element"),"Second element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 02b",list,false,2);
        testReturnObject(testName + " 03",list.add(0,"Insert new at zero"), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 03b",list,false,3);
        testReturnObject(testName + " 04",list.add(1000000000,"Invalid position"), null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 04b",list,false,3);
        testReturnObject(testName + " 05",list.add("Element to remove"),"Element to remove", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 05b",list,false,4);
        testReturnObject(testName + " 06",list.add("Last element"),"Last element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 06b",list,false,5);
        testReturnObject(testName + " 07",list.add(null), null,  true, ErrorMessage.INVALID_ARGUMENT);
        testArrayList   (testName + " 07b",list,false,5);

        testReturnObject(testName + " 08",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 08b",list,false,5);
        testReturnObject(testName + " 09",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 09b",list,false,5);
        testReturnObject(testName + " 10",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 10b",list,false,5);
        testReturnObject(testName + " 11",list.get(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 11b",list,false,5);
        testReturnObject(testName + " 12",list.get(4), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 12b",list,false,5);

        testReturnObject(testName + " 13",list.get(5), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testArrayList   (testName + " 13b",list,false,5);

        testReturnObject(testName + " 14",list.remove(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 14b",list,false,4);

        testReturnObject(testName + " 15",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 15b",list,false,4);
        testReturnObject(testName + " 16",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 16b",list,false,4);
        testReturnObject(testName + " 17",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 17b",list,false,4);
        testReturnObject(testName + " 18",list.get(3), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 18b",list,false,4);
        testReturnObject(testName + " 19",list.get(4), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testArrayList   (testName + " 19b",list,false,4);

        testReturnObject(testName + " 20",list.remove(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 20b",list,false,3);
        testReturnObject(testName + " 21",list.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 21b",list,false,2);
        testReturnObject(testName + " 22",list.remove(0), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 22b",list,false,1);
        testReturnObject(testName + " 23",list.remove(0), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 23b",list,true,0);

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
		testName = "RO-"+testName;

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