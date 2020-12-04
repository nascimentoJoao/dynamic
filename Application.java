public class Application {

	public static void main(String[] args) {
		
		Dynamic dy = new Dynamic();
				
		// dy.countdown(Integer.parseInt(args[0]), 0);
		
		dy.countdown_memory(Integer.parseInt(args[0]), Integer.MAX_VALUE);

		// dy.countdown_non_recursive(Integer.parseInt(args[0]));
		
		// System.out.println("Minimum operations to reach 1: " + dy.minimum() + ". (recursive)");
		System.out.println("Minimum operations to reach 1: " + dy.getMovements().size() + ". (memory)");
		// System.out.println("Minimum operations to reach 1: " + dy.minimum_non_recursive() + ". (non-recursive)\n\n");
		
		System.out.println("Movements: " + dy.getMovements());
		
	}
	
}