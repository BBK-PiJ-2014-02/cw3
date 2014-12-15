public class Test {
    public static void main( String[] args ) {
		List list = new ArrayList();

		if ( ro.hasError() ) {
		    System.out.println("hasError(): "+ro.hasError());
    		System.out.println("getError(): "+ro.getError());
		    System.out.println("getReturnValue: "+ro.getReturnValue());
		}
		else {
		    System.out.println("hasError(): "+ro.hasError());
    		System.out.println("getError(): "+ro.getError());
//		    System.out.println("\tsize(): "+list.size());
		    System.out.println("\tget(1): "+((List)ro.getReturnValue()).get(1));
		}

	}
}