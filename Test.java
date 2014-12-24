public class Test {
    public static void main( String[] args ) {
		List list = new ArrayList();
		list.add(new Integer(14));
		list.add(32.3);
		list.add("This is a test");
		list.add(0,"This is a test");
		list.add(list);
		list.remove(0);
        ReturnObject ro = list.get(2);

		if ( ro.hasError() ) {
		    System.out.println("hasError(): "+ro.hasError());
    		System.out.println("getError(): "+ro.getError());
		    System.out.println("getReturnValue: "+ro.getReturnValue());
		}
		else {
		    System.out.println("hasError(): "+ro.hasError());
    		System.out.println("getError(): "+ro.getError());
		    System.out.println("\tsize(): "+list.size());
		    System.out.println("\tget(): "+ro.getReturnValue());
		}

	}
}