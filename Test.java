public class Test {
    public static void main( String[] args ) {
        // Test ImprovedStack efficiency
        ImprovedStack improvedArrayList  = new ImprovedStackImpl(new ArrayList());
        ImprovedStack improvedLinkedList = new ImprovedStackImpl(new LinkedList());

        System.out.print("\nTesting ArrayList() efficiency:");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) improvedArrayList.push(i);
        for(int i = 0; i < 10000; i++) improvedArrayList.pop();
        long end = System.currentTimeMillis();
        System.out.println(" "+(end-start)+"ms");

        System.out.print("Testing LinkedList() efficiency:");
        start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++) improvedLinkedList.push(i);
        for(int i = 0; i < 10000; i++) improvedLinkedList.pop();
        end = System.currentTimeMillis();
        System.out.println(" "+(end-start)+"ms");

        // Testing ImprovedStack with the ImprovedStackImpl implementation
        testLists("ImprovedStack", new ImprovedStackImpl(new LinkedList()));
        testLists("ImprovedStack", new ImprovedStackImpl(new ArrayList()));

        // Testing AbstractStack with StackImpl on a LinkedList and ArrayList
        testLists("AbstractStack", new StackImpl(new LinkedList()));
        testLists("AbstractStack", new StackImpl(new ArrayList()));

        // Testing SampleableList
        testLists("SampleableList", new SampleableListImpl());

        // Testing the FunctionalLinkedList Implementation
        testLists("FunctionalLinkedList", new FunctionalLinkedList());

        // Testing the FunctionalArrayList Implementation
        testLists("FunctionalArrayList", new FunctionalArrayList());

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
     * Test run multiple ImprovedStack ImprovedStackImpl calls
     *
     * @param testName string for the test name
     * @param stack the ImprovedStack type for ImprovedStackImpl implementation chosen
     */
    private static void testLists(String testName, ImprovedStack stack) {
        // Test basic list methods
        testReturnObject(testName + " 00", stack.top(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 01", stack,true,0);
        testReturnObject(testName + " 02", stack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 03", stack,true,0);
        stack.push      ("First element");
        testStack       (testName + " 04", stack,false,1);
        testReturnObject(testName + " 05", stack.top(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 06", stack,false,1);
        testReturnObject(testName + " 07", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 08", stack,true,0);
        testReturnObject(testName + " 09", stack.pop(), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 10", stack,true,0);
        stack.push      ("First element");
        testStack       (testName + " 11", stack,false,1);
        stack.push      ("Second element");
        testStack       (testName + " 12", stack,false,2);
        stack.push      ("Third element");
        testStack       (testName + " 13", stack,false,3);
        stack.push      ("Fourth element");
        testStack       (testName + " 14", stack,false,4);
        testReturnObject(testName + " 15", stack.pop(), "Fourth element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 16", stack,false,3);
        testReturnObject(testName + " 17", stack.pop(), "Third element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 18", stack,false,2);
        testReturnObject(testName + " 19", stack.pop(), "Second element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 20", stack,false,1);
        testReturnObject(testName + " 21", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 22", stack,true,0);
        testReturnObject(testName + " 23", stack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 24", stack,true,0);

        // Test the new ImprovedStack methods
        stack.push      ("First element");
        testStack       (testName + " 25", stack,false,1);
        stack.push      (1234.54);
        stack.push      ("Third element");
        stack.push      (1234.54);
        stack.push      (null);
        stack.push      (1234.54);
        stack.push      (1234.54);
        stack.push      (1234.54);
        stack.push      (1234.54);
        stack.push      (1234.54);
        stack.push      ("Tenth element");
        testStack       (testName + " 26", stack,false,10);
        stack.remove    (1234.54);
        testStack       (testName + " 27", stack,false,3);
        stack.push      ("Another element");
        testStack       (testName + " 28", stack,false,4);
        stack.remove    ("Another element");
        testStack       (testName + " 29", stack,false,3);

        // Check the remainding items in stack are popped correctly
        testReturnObject(testName + " 31", stack.pop(), "Tenth element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 32", stack,false,2);
        testReturnObject(testName + " 33", stack.pop(), "Third element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 34", stack,false,1);
        testReturnObject(testName + " 35", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 36", stack,true,0);

        // Test the reversed stack
        stack.push      ("First element");
        stack.push      ("Second element");
        stack.push      ("Third element");
        stack.push      ("Fourth element");
        testStack       (testName + " 37", stack,false,4);
        ImprovedStack reversedStack = stack.reverse();
        testReturnObject(testName + " 38", reversedStack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 39", reversedStack.pop(), "Second element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 40", reversedStack.pop(), "Third element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 41", reversedStack.pop(), "Fourth element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 42", reversedStack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 43", reversedStack,true,0);
        // Verify original stack was not altered
        testReturnObject(testName + " 44", stack.pop(), "Fourth element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 45", stack.pop(), "Third element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 46", stack.pop(), "Second element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 47", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 48", stack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 49", stack,true,0);

    }


    /**
     * Test run multiple StackImpl calls
     *
     * @param testName string for the test name
     * @param stack the AbstractStack type for StackImpl implementation chosen
     */
    private static void testLists(String testName, AbstractStack stack) {
        // Test empty list
        testReturnObject(testName + " 00", stack.top(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 01", stack,true,0);
        testReturnObject(testName + " 02", stack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 03", stack,true,0);
        stack.push      ("First element");
        testStack       (testName + " 04", stack,false,1);
        testReturnObject(testName + " 05", stack.top(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 06", stack,false,1);
        testReturnObject(testName + " 07", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 08", stack,true,0);
        testReturnObject(testName + " 09", stack.pop(), null,  true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 10", stack,true,0);
        stack.push      ("First element");
        testStack       (testName + " 11", stack,false,1);
        stack.push      ("Second element");
        testStack       (testName + " 12", stack,false,2);
        stack.push      ("Third element");
        testStack       (testName + " 13", stack,false,3);
        stack.push      ("Fourth element");
        stack.push      (null);
        testStack       (testName + " 14", stack,false,4);
        testReturnObject(testName + " 15", stack.pop(), "Fourth element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 16", stack,false,3);
        testReturnObject(testName + " 17", stack.pop(), "Third element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 18", stack,false,2);
        testReturnObject(testName + " 19", stack.pop(), "Second element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 20", stack,false,1);
        testReturnObject(testName + " 21", stack.pop(), "First element", false, ErrorMessage.NO_ERROR);
        testStack       (testName + " 22", stack,true,0);
        testReturnObject(testName + " 23", stack.pop(), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testStack       (testName + " 24", stack,true,0);
    }


    /**
     * Test run multiple SampleableList calls
     *
     * @param testName string for the test name
     * @param sampleList the SampleableList type implementation chosen
     */
    private static void testLists(String testName, SampleableList sampleList) {
        // Add several items to the list
        testReturnObject(testName + " 01", sampleList.add("First element"), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 02", sampleList,false,1);
        testReturnObject(testName + " 03", sampleList.add("Second element"), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 04", sampleList,false,2);
        testReturnObject(testName + " 05", sampleList.add("Third element"), "Third element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 06", sampleList,false,3);
        testReturnObject(testName + " 07", sampleList.add("Fourth element"), "Fourth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 08", sampleList,false,4);
        testReturnObject(testName + " 09", sampleList.add("Fifth element"), "Fifth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 10", sampleList,false,5);
        testReturnObject(testName + " 11", sampleList.add("Sixth element"), "Sixth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 12", sampleList,false,6);
        testReturnObject(testName + " 12", sampleList.add("Seventh element"), "Seventh element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 13", sampleList,false,7);
        testReturnObject(testName + " 14", sampleList.add("Eighth element"), "Eighth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 15", sampleList,false,8);
        testReturnObject(testName + " 16", sampleList.add("Nineth element"), "Nineth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 17", sampleList,false,9);
        testReturnObject(testName + " 18", sampleList.add("Tenth element"), "Tenth element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 19", sampleList,false,10);
        testReturnObject(testName + " 20", sampleList.add(null), null,  true, ErrorMessage.INVALID_ARGUMENT);

        // Check sampled list
        SampleableList sl = sampleList.sample();
        testArrayList   (testName + " 21", sl,false,5);
        testReturnObject(testName + " 22", sl.get(0), "First element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 23", sl.get(1), "Third element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 24", sl.get(2), "Fifth element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 25", sl.get(3), "Seventh element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 26", sl.get(4), "Nineth element",  false, ErrorMessage.NO_ERROR);
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
        testReturnObject(testName + " 04", functionalList.add(null),null, true, ErrorMessage.INVALID_ARGUMENT);
        testReturnObject(testName + " 05", functionalList.add("Third element"),"Third element", false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 06", functionalList.head(), "First element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 07", functionalList.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 08", functionalList,false,2);
        testReturnObject(testName + " 09", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);

        // Test rest
        FunctionalList shortList = functionalList.rest();
        testArrayList   (testName + " 10", shortList,false,1);
        testReturnObject(testName + " 11", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 12", shortList.head(), "Third element",  false, ErrorMessage.NO_ERROR);

        // Test adding elements to short list and checking FunctionalArrayList does not change
        testReturnObject(testName + " 13", shortList.add("ShortList element"), "ShortList element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 14", shortList.add(null), null, true, ErrorMessage.INVALID_ARGUMENT);
        testArrayList   (testName + " 15", shortList,false,2);
        testReturnObject(testName + " 16", shortList.remove(0), "Third element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 17", shortList,false,1);
        testReturnObject(testName + " 18", functionalList.head(), "Second element",  false, ErrorMessage.NO_ERROR);
        testReturnObject(testName + " 19", shortList.head(), "ShortList element",  false, ErrorMessage.NO_ERROR);
    }

    /**
     * Test run the ArrayList or LinkedList
     *
     * @param testName string for the test name
     * @param list the list type implementation chosen
     */
    private static void testLists(String testName, List list) {
        // testArrayList(testName, list, isEmptyExpected, sizeExpected)
        testArrayList   (testName + " 00",list,true,0);
        testReturnObject(testName + " 01",list.add("First element"), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 02",list,false,1);
        testReturnObject(testName + " 03",list.add("Second element"),"Second element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 04",list,false,2);
        testReturnObject(testName + " 05",list.add(0,"Insert new at zero"), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 06",list,false,3);
        testReturnObject(testName + " 07",list.add(3,"Invalid position"), null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 08",list,false,3);
        testReturnObject(testName + " 09",list.add("Element to remove"),"Element to remove", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 10",list,false,4);
        testReturnObject(testName + " 11",list.add("Last element"),"Last element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 12",list,false,5);
        testReturnObject(testName + " 13",list.add(null), null,  true, ErrorMessage.INVALID_ARGUMENT);
        testArrayList   (testName + " 14",list,false,5);

        testReturnObject(testName + " 15",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 16",list,false,5);
        testReturnObject(testName + " 17",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 18",list,false,5);
        testReturnObject(testName + " 19",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 20",list,false,5);
        testReturnObject(testName + " 21",list.get(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 22",list,false,5);
        testReturnObject(testName + " 23",list.get(4), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 24",list,false,5);

        testReturnObject(testName + " 25",list.get(5), null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 26",list,false,5);

        testReturnObject(testName + " 27",list.remove(3), "Element to remove",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 28",list,false,4);

        testReturnObject(testName + " 29",list.get(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 30",list,false,4);
        testReturnObject(testName + " 31",list.get(1), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 32",list,false,4);
        testReturnObject(testName + " 33",list.get(2), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 34",list,false,4);
        testReturnObject(testName + " 35",list.get(3), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 36",list,false,4);
        testReturnObject(testName + " 37",list.get(4), null,  true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 38",list,false,4);

        testReturnObject(testName + " 39",list.remove(0), "Insert new at zero",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 40",list,false,3);
        testReturnObject(testName + " 41",list.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 42",list,false,2);
        testReturnObject(testName + " 43",list.remove(0), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 44",list,false,1);
        testReturnObject(testName + " 45",list.remove(0), "Last element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 46",list,true,0);

        // Testing with add(index,item)
        testArrayList   (testName + " 47",list,true,0);
        testReturnObject(testName + " 48",list.add(0,"First element"), null, true, ErrorMessage.EMPTY_STRUCTURE);
        testArrayList   (testName + " 49",list,true,0);
        testReturnObject(testName + " 50",list.add("First element"), "First element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 51",list,false,1);
        testReturnObject(testName + " 52",list.add("Second element"), "Second element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 53",list,false,2);
        testReturnObject(testName + " 54",list.add(0,"Insert a new at zero"), "Insert a new at zero", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 55",list,false,3);
        testReturnObject(testName + " 56",list.add(3,"Adding at last index"), null, true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 57",list,false,3);

        testReturnObject(testName + " 58",list.get(0), "Insert a new at zero", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 59",list,false,3);
        testReturnObject(testName + " 60",list.remove(0), "Insert a new at zero", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 61",list,false,2);
        testReturnObject(testName + " 62",list.remove(2), null, true, ErrorMessage.INDEX_OUT_OF_BOUNDS);
        testArrayList   (testName + " 63",list,false,2);
        testReturnObject(testName + " 64",list.remove(1), "Second element", false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 65",list,false,1);
        testReturnObject(testName + " 66",list.remove(0), "First element",  false, ErrorMessage.NO_ERROR);
        testArrayList   (testName + " 67",list,true,0);

        testReturnObject(testName + " 64",list.remove(0), null, true, ErrorMessage.EMPTY_STRUCTURE);
    }

    /**
     * Unit Testing ImprovedStack Implementation.
     * Outputs results only for isEmpty() and size()
     *
     * @param testName the test name
     * @param stack the ImprovedStack
     * @param isEmptyExpected boolean expected stack value for isEmpty()
     * @param sizeExpected int for the element amount expected in Stack
     */
    private static void testStack(String testName, ImprovedStack stack, boolean isEmptyExpected, int sizeExpected) {
        boolean isEmptyFound = stack.isEmpty();
        int     sizeFound    = stack.size();
        testList(testName, isEmptyFound, sizeFound, isEmptyExpected, sizeExpected);
    }

    /**
     * Unit Testing AbstractStack Implementation.
     * Outputs results only for isEmpty() and size()
     *
     * @param testName the test name
     * @param stack the AbstractStack
     * @param isEmptyExpected boolean expected stack value for isEmpty()
     * @param sizeExpected int for the element amount expected in Stack
     */
    private static void testStack(String testName, AbstractStack stack, boolean isEmptyExpected, int sizeExpected) {
        boolean isEmptyFound = stack.isEmpty();
        int     sizeFound    = stack.size();
        testList(testName, isEmptyFound, sizeFound, isEmptyExpected, sizeExpected);
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
        testList(testName, isEmptyFound, sizeFound, isEmptyExpected, sizeExpected);
    }

    /**
     * Gemeric Results
     *
     * @param testName the test name
     * @param isEmptyExpected boolean expected list value for isEmpty()
     * @param sizeExpected int for the element amount expected in List
     * @param isEmptyFound boolean found list value for esEmpty()
     * @param isSizeFound int for the element amount found in list
     */
    private static void testList(String testName, boolean isEmptyFound, int sizeFound, boolean isEmptyExpected, int sizeExpected) {
        if ( isEmptyFound != isEmptyExpected ) {
            System.out.println("[" + testName + "]\t - List.isEmpty(): " + isEmptyFound + " expected " + isEmptyExpected);
        }

        if ( sizeFound != sizeExpected ) {
            System.out.println("[" + testName + "]\t - List.size(): " + sizeFound + " expected " + sizeExpected);
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
            System.out.println("[" + testName + "]\t - Returned: " + returnValueFound + " expected " + returnValueExpected);
        }
        else if ( returnValueFound != null && returnValueExpected == null ) {
            System.out.println("[" + testName + "]\t - Returned: " + returnValueFound + " expected " + returnValueExpected);
        }
        else if ( returnValueFound != null && returnValueExpected != null && !returnValueFound.equals(returnValueExpected) ) {
            System.out.println("[" + testName + "]\t - Returned: " + returnValueFound + " expected " + returnValueExpected);
        }

        if ( hasErrorFound != hasErrorExpected ) {
            System.out.println("[" + testName + "]\t - HasError: " + hasErrorFound + " expected " + hasErrorExpected);
        }

        if ( !errorMessageFound.equals(errorMessageExpected) ){
            System.out.println("[" + testName + "]\t - ErrorMessage: " + errorMessageFound + " expected: " + errorMessageExpected);
        }
    }
}